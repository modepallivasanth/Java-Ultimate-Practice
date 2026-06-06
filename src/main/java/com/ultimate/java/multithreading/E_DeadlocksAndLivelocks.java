package com.ultimate.java.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                  DEADLOCKS & LIVELOCKS (INTERVIEW)                         ║
 * ║                 How Threads Get Stuck and How to Fix It                    ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ INTERVIEW QUESTION: "What are the 4 conditions required for a Deadlock?"  │
 * │                                                                           │
 * │  (The Coffman Conditions - ALL 4 MUST BE TRUE):                           │
 * │  1. Mutual Exclusion: Resources can't be shared (locks are exclusive).    │
 * │  2. Hold and Wait: A thread holds Lock A and waits for Lock B.            │
 * │  3. No Preemption: You can't forcefully take a lock away from a thread.   │
 * │  4. Circular Wait: T1 waits for T2, T2 waits for T3, T3 waits for T1.     │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "How do you PREVENT Deadlocks?"                       │
 * │                                                                           │
 * │  Break one of the 4 conditions! Usually #4 (Circular Wait).               │
 * │  Fix: LOCK ORDERING. Always acquire locks in the exact same order         │
 * │  across all threads. (e.g. Always lock A then B, never B then A).         │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "Deadlock vs Livelock vs Starvation?"                 │
 * │                                                                           │
 * │  • Deadlock: Two threads blocked forever, waiting on each other.          │
 * │  • Livelock: Two threads constantly yielding to each other, so neither    │
 * │    ever makes progress. (Like two people trying to pass in a hallway).    │
 * │  • Starvation: A low-priority thread never gets CPU time because high-    │
 * │    priority threads hog the CPU, or because of an Unfair Lock.            │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class E_DeadlocksAndLivelocks {

    // =========================================================================
    // THE DEADLOCK DEMO
    // =========================================================================
    static class DeadlockDemo {
        private final Object lockA = new Object();
        private final Object lockB = new Object();

        public void method1() {
            synchronized (lockA) {
                System.out.println("    [Thread 1] Acquired Lock A");
                try { Thread.sleep(100); } catch (Exception e) {}
                
                System.out.println("    [Thread 1] Waiting for Lock B...");
                synchronized (lockB) {
                    System.out.println("    [Thread 1] Acquired Lock B");
                }
            }
        }

        public void method2() {
            // Notice the lock order is REVERSED! This causes Deadlock.
            synchronized (lockB) {
                System.out.println("    [Thread 2] Acquired Lock B");
                try { Thread.sleep(100); } catch (Exception e) {}
                
                System.out.println("    [Thread 2] Waiting for Lock A...");
                synchronized (lockA) {
                    System.out.println("    [Thread 2] Acquired Lock A");
                }
            }
        }
    }

    // =========================================================================
    // THE TRY-LOCK FIX (Timeout)
    // =========================================================================
    static class TryLockFix {
        private final Lock lockA = new ReentrantLock();
        private final Lock lockB = new ReentrantLock();

        public void safeMethod() {
            try {
                // Instead of blocking forever, try for 1 second!
                if (lockA.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("    [SafeThread] Acquired Lock A");
                        
                        if (lockB.tryLock(1, TimeUnit.SECONDS)) {
                            try {
                                System.out.println("    [SafeThread] Acquired Lock B. Success!");
                            } finally {
                                lockB.unlock();
                            }
                        } else {
                            System.out.println("    [SafeThread] Could not get Lock B, giving up Lock A to prevent deadlock!");
                        }
                    } finally {
                        lockA.unlock();
                    }
                }
            } catch (InterruptedException e) {}
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 34: Deadlocks & Livelocks (Interview)      ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ PART 1: The Deadlock Prevention ━━━");
        System.out.println("  (We won't actually run the deadlock, or this program will freeze forever!)");
        System.out.println("  To fix DeadlockDemo, Method 2 should lock A then B, just like Method 1.");
        System.out.println();

        System.out.println("━━━ PART 2: Try-Lock Fix ━━━");
        TryLockFix fix = new TryLockFix();
        Thread safe = new Thread(() -> fix.safeMethod());
        safe.start();
        safe.join();
        System.out.println();

        System.out.println("━━━ ⚠️ COMMON INTERVIEW TRAPS ━━━");
        System.out.println("1. Q: How do you detect a deadlock in production?");
        System.out.println("   A: Take a thread dump! Use tools like `jstack <pid>`, VisualVM, or JConsole.");
        System.out.println("      The JVM will literally print 'Found one Java-level deadlock' in the dump.");
        System.out.println();
        System.out.println("2. Q: Can the JVM automatically resolve deadlocks?");
        System.out.println("   A: No. Databases can (by rolling back a transaction), but the JVM");
        System.out.println("      has no safe way to force a thread to release an intrinsic lock.");
        System.out.println();

        System.out.println("✅ Lesson complete! Next up: F_AtomicsAndCAS.java");
    }
}
