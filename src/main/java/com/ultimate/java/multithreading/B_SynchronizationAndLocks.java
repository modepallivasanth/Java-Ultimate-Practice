package com.ultimate.java.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                  SYNCHRONIZATION & LOCKS IN JAVA                           ║
 * ║               Preventing Chaos When Threads Collide                        ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ THE PROBLEM: RACE CONDITIONS                                              │
 * │                                                                           │
 * │  What happens when TWO threads try to modify the SAME data at the         │
 * │  SAME time? Chaos!                                                        │
 * │                                                                           │
 * │  Think: A JOINT BANK ACCOUNT 🏦                                           │
 * │    Balance = $100                                                         │
 * │    Husband withdraws $100. Wife withdraws $100.                           │
 * │    If they do it at the EXACT SAME MILLISECOND, the system might          │
 * │    check the balance for both, see $100, and give them both $100!         │
 * │    Bank loses $100! (This is a "Race Condition").                         │
 * │                                                                           │
 * │ THE SOLUTION: SYNCHRONIZATION                                             │
 * │                                                                           │
 * │  We need a LOCK 🔒 (a Monitor).                                           │
 * │  "Only ONE thread can enter this room at a time. The door locks           │
 * │  behind them. Others must wait in line."                                  │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class B_SynchronizationAndLocks {

    // =========================================================================
    // PART 1: THE BAD WAY (No Synchronization)
    // =========================================================================
    static class BadCounter {
        int count = 0;

        // count++ is NOT atomic! It's actually 3 steps:
        // 1. Read count
        // 2. Add 1
        // 3. Write count back
        // Threads can overlap during these 3 steps!
        void increment() {
            count++; 
        }
    }

    // =========================================================================
    // PART 2: THE GOOD WAY (synchronized keyword)
    // =========================================================================
    static class GoodCounter {
        int count = 0;

        // synchronized = "Lock this object while this method runs"
        // Only one thread can be inside ANY synchronized method of this object at a time.
        synchronized void increment() {
            count++;
        }
        
        // Alternatively, use a synchronized block for finer control
        void incrementBlock() {
            // Do non-critical stuff here (multiple threads allowed)
            
            synchronized (this) { // Lock specifically on the current object
                // Critical section (only one thread allowed)
                count++;
            }
        }
    }

    // =========================================================================
    // PART 3: REENTRANT LOCKS (Modern, flexible locks)
    // =========================================================================
    static class LockCounter {
        int count = 0;
        // ReentrantLock provides more features than 'synchronized'
        // (like tryLock, timed locking, fairness policies)
        private final Lock lock = new ReentrantLock();

        void increment() {
            lock.lock(); // Acquire the lock
            try {
                // Critical section MUST be inside a try-finally block!
                count++;
            } finally {
                // ALWAYS release the lock in a finally block!
                // Otherwise, if an exception is thrown, the lock is NEVER released (Deadlock!)
                lock.unlock(); 
            }
        }
    }

    // =========================================================================
    // PART 4: VOLATILE KEYWORD (Visibility)
    // =========================================================================
    static class FlagRunner {
        // Without 'volatile', thread 1 might cache this variable in its CPU core
        // and NEVER see the change made by thread 2 in another CPU core!
        // 'volatile' means: "Always read/write this directly to Main Memory."
        volatile boolean keepRunning = true;

        void startRunning() {
            new Thread(() -> {
                long counter = 0;
                while (keepRunning) {
                    counter++; // Spin!
                }
                System.out.println("    🏁 Runner stopped! Final count: " + counter);
            }).start();
        }

        void stop() {
            keepRunning = false;
            System.out.println("    🛑 Stop signal sent!");
        }
    }

    // =========================================================================
    // MAIN THREAD
    // =========================================================================
    public static void main(String[] args) throws InterruptedException {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 31: Synchronization & Locks                ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // ── 1. The Race Condition Demo ───────────────────────────────────
        System.out.println("━━━ PART 1: The Race Condition (No Sync) ━━━");
        BadCounter bad = new BadCounter();
        
        // Create 2 threads, each adding 10,000
        Thread t1 = new Thread(() -> { for (int i = 0; i < 10000; i++) bad.increment(); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < 10000; i++) bad.increment(); });
        
        t1.start(); t2.start();
        t1.join();  t2.join(); // Wait for both to finish

        System.out.println("  Expected count: 20000");
        System.out.println("  Actual count:   " + bad.count + "  ← Notice it's WRONG! Data corruption!");
        System.out.println();

        // ── 2. The Synchronized Fix ──────────────────────────────────────
        System.out.println("━━━ PART 2: The Synchronized Fix ━━━");
        GoodCounter good = new GoodCounter();
        
        Thread t3 = new Thread(() -> { for (int i = 0; i < 10000; i++) good.increment(); });
        Thread t4 = new Thread(() -> { for (int i = 0; i < 10000; i++) good.increment(); });
        
        t3.start(); t4.start();
        t3.join();  t4.join();

        System.out.println("  Expected count: 20000");
        System.out.println("  Actual count:   " + good.count + "  ← Perfect! synchronized fixed it.");
        System.out.println();

        // ── 3. The ReentrantLock Fix ─────────────────────────────────────
        System.out.println("━━━ PART 3: The ReentrantLock Fix ━━━");
        LockCounter lockCounter = new LockCounter();
        
        Thread t5 = new Thread(() -> { for (int i = 0; i < 10000; i++) lockCounter.increment(); });
        Thread t6 = new Thread(() -> { for (int i = 0; i < 10000; i++) lockCounter.increment(); });
        
        t5.start(); t6.start();
        t5.join();  t6.join();

        System.out.println("  Expected count: 20000");
        System.out.println("  Actual count:   " + lockCounter.count + "  ← Perfect! ReentrantLock fixed it.");
        System.out.println();

        // ── 4. Volatile Demo ─────────────────────────────────────────────
        System.out.println("━━━ PART 4: Volatile Keyword ━━━");
        FlagRunner runner = new FlagRunner();
        runner.startRunning();
        
        Thread.sleep(100); // Let it run for 100ms
        runner.stop();     // Send the stop signal
        
        Thread.sleep(100); // Give it time to print
        System.out.println();

        // ── 5. Common Mistakes ───────────────────────────────────────────
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Thinking volatile replaces synchronized:");
        System.out.println("   volatile ONLY ensures VISIBILITY across CPU cores.");
        System.out.println("   It does NOT make operations atomic (count++ still fails with volatile!).");
        System.out.println();
        System.out.println("2. Deadlocks:");
        System.out.println("   Thread A holds Lock 1 and waits for Lock 2.");
        System.out.println("   Thread B holds Lock 2 and waits for Lock 1.");
        System.out.println("   Result: Both wait FOREVER. Always acquire locks in the same order!");
        System.out.println();
        System.out.println("3. Forgetting finally with ReentrantLock:");
        System.out.println("   If code throws an exception before unlock(), the lock is stuck forever.");
        System.out.println("   ALWAYS do: lock.lock(); try { ... } finally { lock.unlock(); }");
        System.out.println();

        System.out.println("✅ Lesson complete! Next up: C_AtomicAndConcurrentCollections.java");
    }
}
