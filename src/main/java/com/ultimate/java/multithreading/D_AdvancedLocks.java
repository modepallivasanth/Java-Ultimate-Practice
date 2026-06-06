package com.ultimate.java.multithreading;

import java.util.concurrent.locks.*;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                      ADVANCED LOCKS (INTERVIEW)                            ║
 * ║                 ReentrantLock, ReadWriteLock, StampedLock                  ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ INTERVIEW QUESTION: "Why use ReentrantLock over 'synchronized'?"          │
 * │                                                                           │
 * │  1. tryLock(): You can attempt to get a lock, but if it's taken, you can  │
 * │     do something else instead of waiting forever.                         │
 * │  2. lockInterruptibly(): Thread can be interrupted while waiting for lock.│
 * │  3. Fairness: `new ReentrantLock(true)` guarantees the longest-waiting    │
 * │     thread gets the lock next (FIFO). 'synchronized' is UNFAIR.           │
 * │  4. Multiple Conditions: You can have multiple waitsets (Conditions).     │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "What is a ReadWriteLock?"                            │
 * │                                                                           │
 * │  If 10 threads want to READ data, why lock them out? ReadWriteLock allows │
 * │  MULTIPLE concurrent readers, but only ONE writer (who blocks readers).   │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "What is StampedLock? (Java 8)"                       │
 * │                                                                           │
 * │  Even better than ReadWriteLock! It supports "Optimistic Reading".        │
 * │  It reads data WITHOUT acquiring a lock at all, then checks a "stamp"     │
 * │  after reading to see if a writer interfered. Extremely fast!             │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class D_AdvancedLocks {

    // =========================================================================
    // READ-WRITE LOCK DEMO
    // =========================================================================
    static class Cache {
        private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
        private String data = "Initial";

        public String read() {
            rwLock.readLock().lock(); // Multiple threads can hold this at once!
            try {
                System.out.println("    [Reader " + Thread.currentThread().getName() + "] Read: " + data);
                try { Thread.sleep(200); } catch (Exception e) {} // Simulate slow read
                return data;
            } finally {
                rwLock.readLock().unlock();
            }
        }

        public void write(String newData) {
            rwLock.writeLock().lock(); // Blocks ALL readers and writers
            try {
                System.out.println("    [Writer] WRITING: " + newData);
                this.data = newData;
                try { Thread.sleep(500); } catch (Exception e) {}
            } finally {
                rwLock.writeLock().unlock();
            }
        }
    }

    // =========================================================================
    // STAMPED LOCK DEMO (Optimistic Reading)
    // =========================================================================
    static class Point {
        private double x, y;
        private final StampedLock sl = new StampedLock();

        // Write is normal
        void move(double deltaX, double deltaY) {
            long stamp = sl.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                sl.unlockWrite(stamp);
            }
        }

        // OPTIMISTIC READ!
        double distanceFromOrigin() {
            // 1. Try an optimistic read (no lock actually acquired!)
            long stamp = sl.tryOptimisticRead();
            
            // 2. Read fields into local variables
            double currentX = x, currentY = y;
            
            // 3. Validate! Did a writer come in while we were copying the fields?
            if (!sl.validate(stamp)) {
                // Yes! A writer interfered. Fall back to a traditional blocking read lock.
                stamp = sl.readLock();
                try {
                    currentX = x;
                    currentY = y;
                } finally {
                    sl.unlockRead(stamp);
                }
            }
            return Math.sqrt(currentX * currentX + currentY * currentY);
        }
    }

    // =========================================================================
    // CONDITION VARIABLES (Replacement for wait/notify)
    // =========================================================================
    static class ConditionQueue {
        private final Lock lock = new ReentrantLock();
        // A Condition is bound to a Lock. It replaces Object.wait()
        private final Condition notFull  = lock.newCondition();
        private final Condition notEmpty = lock.newCondition();
        
        // This allows Producers to wait on 'notFull', and Consumers to wait on 'notEmpty'
        // 'notifyAll' would wake up EVERYONE. This allows us to target exactly who to wake!
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 33: Advanced Locks (Interview)             ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ PART 1: ReadWriteLock ━━━");
        Cache cache = new Cache();
        
        // Start 3 readers. Notice they read SIMULTANEOUSLY!
        Thread r1 = new Thread(() -> cache.read(), "1");
        Thread r2 = new Thread(() -> cache.read(), "2");
        Thread r3 = new Thread(() -> cache.read(), "3");
        
        Thread w1 = new Thread(() -> cache.write("Updated Data"));

        r1.start(); r2.start(); r3.start();
        Thread.sleep(50); // Let readers start
        
        w1.start(); // Writer tries to write, must wait for readers to finish
        
        r1.join(); r2.join(); r3.join(); w1.join();
        System.out.println();

        System.out.println("━━━ ⚠️ COMMON INTERVIEW TRAPS ━━━");
        System.out.println("1. Q: Does Fair Lock mean perfect performance?");
        System.out.println("   A: No! Fair locks are severely slower than unfair locks due to context");
        System.out.println("      switching overhead. Always default to Unfair unless you absolutely");
        System.out.println("      need FIFO ordering to prevent starvation.");
        System.out.println();
        System.out.println("2. Q: Can ReadWriteLock be upgraded?");
        System.out.println("   A: NO. You cannot hold a ReadLock and ask for a WriteLock. (Deadlock).");
        System.out.println("      You CAN hold a WriteLock and ask for a ReadLock (Downgrading).");
        System.out.println();

        System.out.println("✅ Lesson complete! Next up: E_DeadlocksAndLivelocks.java");
    }
}
