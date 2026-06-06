package com.ultimate.java.multithreading;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                  THREAD LIFECYCLE & BASICS (INTERVIEW DEEP DIVE)           ║
 * ║                 Mastering the 6 Thread States and Core Methods             ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ INTERVIEW QUESTION: "Can you explain the exact Thread Lifecycle in Java?" │
 * │                                                                           │
 * │ 1. NEW           : Thread object created, but start() not called yet.     │
 * │ 2. RUNNABLE      : start() called. Waiting for CPU time, or executing.    │
 * │ 3. BLOCKED       : Trying to acquire a monitor lock (synchronized).       │
 * │ 4. WAITING       : Waiting indefinitely for another thread (wait, join).  │
 * │ 5. TIMED_WAITING : Waiting for a specified time (sleep, wait(ms)).        │
 * │ 6. TERMINATED    : run() method completed normally or via exception.      │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "wait() vs sleep()?"                                  │
 * │  • sleep() belongs to Thread. It DOES NOT release any locks.              │
 * │  • wait() belongs to Object. It RELEASES the lock so others can enter.    │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "yield() vs join()?"                                  │
 * │  • yield() hints the scheduler "I can pause, let other threads run."      │
 * │  • join() says "Pause current thread UNTIL the joined thread finishes."   │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "Daemon vs User threads?"                             │
 * │  • JVM exits when ALL User threads terminate.                             │
 * │  • Daemon threads (e.g. Garbage Collector) run in background. JVM does    │
 * │    NOT wait for Daemon threads to finish before exiting.                  │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class A_ThreadLifecycleAndBasics {

    // =========================================================================
    // DAEMON THREAD DEMO
    // =========================================================================
    static void startDaemonThread() {
        Thread daemon = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("    [Daemon] Cleaning up memory in background...");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        
        // MUST set daemon status BEFORE starting!
        daemon.setDaemon(true); 
        daemon.start();
        System.out.println("  [Main] Daemon thread started.");
    }

    // =========================================================================
    // YIELD DEMO
    // =========================================================================
    static void demonstrateYield() {
        Runnable task = () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("    [" + Thread.currentThread().getName() + "] executing.");
                // Hint to the OS scheduler to give CPU time to other threads of same priority
                Thread.yield(); 
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        t1.start();
        t2.start();
        
        try { t1.join(); t2.join(); } catch (InterruptedException e) {}
    }

    // =========================================================================
    // INTERRUPTING THREADS
    // =========================================================================
    static void demonstrateInterrupt() throws InterruptedException {
        Thread worker = new Thread(() -> {
            try {
                System.out.println("    [Worker] Going to sleep for 10 seconds...");
                Thread.sleep(10000); 
            } catch (InterruptedException e) {
                // When sleep/wait is interrupted, InterruptedException is thrown 
                // AND the interrupt flag is cleared!
                System.out.println("    [Worker] I was violently woken up! Interrupt flag: " + Thread.currentThread().isInterrupted());
                
                // Best practice: Restore the interrupt flag if you catch the exception
                // so callers higher in the stack know it was interrupted.
                Thread.currentThread().interrupt();
                System.out.println("    [Worker] Flag restored: " + Thread.currentThread().isInterrupted());
            }
        });

        worker.start();
        Thread.sleep(1000); // Let worker start sleeping
        
        System.out.println("  [Main] Interrupting the worker!");
        worker.interrupt(); // Sends the signal
        
        worker.join();
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 30: Thread Lifecycle & Basics (Interview)  ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // ── 1. Daemon Threads ──────────────────────────────────────────
        System.out.println("━━━ PART 1: Daemon vs User Threads ━━━");
        startDaemonThread();
        Thread.sleep(1200); // Let daemon print a few times
        System.out.println("  [Main] Main thread moving on. Notice how the Daemon stops when Main finishes later!");
        System.out.println();

        // ── 2. Yielding ──────────────────────────────────────────────────
        System.out.println("━━━ PART 2: Thread.yield() ━━━");
        System.out.println("  (Output might vary based on OS scheduler)");
        demonstrateYield();
        System.out.println();

        // ── 3. Interruption ──────────────────────────────────────────────
        System.out.println("━━━ PART 3: Thread.interrupt() ━━━");
        demonstrateInterrupt();
        System.out.println();

        // ── 4. Lifecycle Demo ────────────────────────────────────────────
        System.out.println("━━━ PART 4: The 6 States Demo ━━━");
        
        final Object lock = new Object();
        
        Thread stateThread = new Thread(() -> {
            try {
                // Go into TIMED_WAITING
                Thread.sleep(500); 
                
                synchronized (lock) {
                    // Go into WAITING
                    lock.wait(); 
                }
            } catch (InterruptedException e) {}
        });

        System.out.println("  1. Just created:    " + stateThread.getState() + " (NEW)");
        
        stateThread.start();
        System.out.println("  2. Just started:    " + stateThread.getState() + " (RUNNABLE)");
        
        Thread.sleep(100);
        System.out.println("  3. While sleeping:  " + stateThread.getState() + " (TIMED_WAITING)");
        
        Thread.sleep(500);
        System.out.println("  4. While wait()ing: " + stateThread.getState() + " (WAITING)");
        
        // Wake it up
        synchronized (lock) {
            lock.notify();
        }
        
        stateThread.join();
        System.out.println("  5. Finished:        " + stateThread.getState() + " (TERMINATED)");
        System.out.println();

        System.out.println("━━━ ⚠️ COMMON INTERVIEW TRAPS ━━━");
        System.out.println("1. Q: Does Thread.sleep(0) do anything?");
        System.out.println("   A: Yes, it triggers a thread context switch (yields the CPU).");
        System.out.println();
        System.out.println("2. Q: What happens if an exception escapes the run() method?");
        System.out.println("   A: The thread terminates. The JVM prints the stack trace, but other threads continue.");
        System.out.println("      You can catch this globally using Thread.setDefaultUncaughtExceptionHandler().");
        System.out.println();

        System.out.println("✅ Lesson complete! Next up: B_JavaMemoryModelAndVolatile.java");
    }
}
