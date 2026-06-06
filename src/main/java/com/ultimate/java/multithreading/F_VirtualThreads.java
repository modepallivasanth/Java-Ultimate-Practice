package com.ultimate.java.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                      VIRTUAL THREADS (Java 21+)                            ║
 * ║                 Project Loom — The Future of Concurrency                   ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ THE OLD WAY (Platform Threads)                                            │
 * │                                                                           │
 * │  Java Threads were wrapped around OS Threads (1:1 mapping).               │
 * │  OS Threads are heavy (~1MB memory each).                                 │
 * │  Max threads on a laptop: ~4,000 before crashing.                         │
 * │  If a thread blocks (Thread.sleep or DB call), the OS thread is wasted!   │
 * │                                                                           │
 * │ THE NEW WAY (Virtual Threads - Java 21)                                   │
 * │                                                                           │
 * │  Java now manages threads internally (M:N mapping).                       │
 * │  Virtual Threads are CHEAP and LIGHTWEIGHT (bytes, not megabytes).        │
 * │  Max threads on a laptop: MILLIONS! 🚀                                   │
 * │                                                                           │
 * │  How it works:                                                            │
 * │    When a Virtual Thread blocks (e.g. waiting for a database), the JVM    │
 * │    "unmounts" it from the Carrier OS Thread, and puts another Virtual     │
 * │    Thread on that OS thread. The OS thread NEVER blocks!                  │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class F_VirtualThreads {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 35: Virtual Threads (Java 21+)             ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: CREATING A VIRTUAL THREAD
        // =====================================================================
        System.out.println("━━━ PART 1: Creating a Virtual Thread ━━━");

        // Old way (Platform thread):
        Thread platformThread = Thread.ofPlatform().unstarted(() -> {
            System.out.println("    [Platform] Running on: " + Thread.currentThread());
        });

        // New way (Virtual thread):
        Thread virtualThread = Thread.ofVirtual().unstarted(() -> {
            System.out.println("    [Virtual] Running on: " + Thread.currentThread());
        });

        platformThread.start();
        virtualThread.start();
        
        try {
            platformThread.join();
            virtualThread.join();
        } catch (InterruptedException e) {}
        System.out.println();

        // =====================================================================
        // PART 2: THE 100,000 THREAD TEST
        // =====================================================================
        System.out.println("━━━ PART 2: The 100,000 Thread Test ━━━");
        System.out.println("  Attempting to run 100,000 virtual threads... 🏎️");

        long startTime = System.currentTimeMillis();

        // We use a special ExecutorService that creates a NEW virtual thread for EVERY task!
        // No thread pooling needed anymore!
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            
            for (int i = 0; i < 100_000; i++) {
                executor.submit(() -> {
                    try {
                        // Simulate blocking I/O (like an API call).
                        // In a normal thread pool, 100k threads sleeping would crash the JVM.
                        // Here, the JVM just swaps them out and reuses a handful of OS threads!
                        Thread.sleep(1000); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
            
        } // The try-with-resources block automatically calls executor.shutdown() and awaitTermination()

        long endTime = System.currentTimeMillis();
        System.out.println("  Done! Started and finished 100,000 threads in " + (endTime - startTime) + "ms.");
        System.out.println("  (Notice it took only slightly over 1000ms! All 100k tasks ran concurrently!)");
        System.out.println();

        // =====================================================================
        // SUMMARY & BEST PRACTICES
        // =====================================================================
        System.out.println("━━━ Virtual Thread Best Practices ━━━");
        System.out.println();
        System.out.println("  ✅ DO:");
        System.out.println("     • Create a new virtual thread for every task.");
        System.out.println("     • Write simple, synchronous, blocking code (it's safe now!).");
        System.out.println("     • Use Executors.newVirtualThreadPerTaskExecutor().");
        System.out.println();
        System.out.println("  ❌ DON'T:");
        System.out.println("     • DON'T pool virtual threads! They are cheap to create.");
        System.out.println("     • DON'T use synchronized blocks if they contain long-running blocking calls.");
        System.out.println("       (This causes 'pinning', preventing the OS thread from being reused).");
        System.out.println("       Use ReentrantLock instead for long blocking operations.");
        System.out.println();

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║  🎉🎉🎉 MODULE 6 COMPLETE! 🎉🎉🎉                    ║");
        System.out.println("║                                                      ║");
        System.out.println("║  You have mastered Java Multithreading, from basic   ║");
        System.out.println("║  synchronized blocks to modern Virtual Threads!      ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
}
