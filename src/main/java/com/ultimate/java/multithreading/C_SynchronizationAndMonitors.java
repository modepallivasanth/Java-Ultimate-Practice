package com.ultimate.java.multithreading;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                  SYNCHRONIZATION & MONITORS (INTERVIEW)                    ║
 * ║                 Intrinsic Locks, wait/notify, Spurious Wakeups             ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ INTERVIEW QUESTION: "How does 'synchronized' work under the hood?"        │
 * │                                                                           │
 * │  Every Object in Java has an "Intrinsic Lock" (or Monitor).               │
 * │  It is stored in the Object Header (Mark Word) in memory.                 │
 * │  When a thread enters a synchronized block, it executes a `monitorenter`  │
 * │  JVM instruction. When it leaves, it executes `monitorexit`.              │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "Why must wait() and notify() be called inside a      │
 * │                      synchronized block?"                                 │
 * │                                                                           │
 * │  Because they operate on the MONITOR of that object! If you don't own     │
 * │  the monitor (via synchronized), the JVM throws IllegalMonitorStateException.
 * │                                                                           │
 * │ INTERVIEW QUESTION: "Why must wait() always be in a while loop?"          │
 * │                                                                           │
 * │  SPURIOUS WAKEUPS! The OS can randomly wake up a waiting thread even if   │
 * │  notify() was never called! If you use an `if`, the thread wakes up,      │
 * │  assumes it's safe, and crashes. A `while` loop forces it to re-check     │
 * │  the condition before proceeding.                                         │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class C_SynchronizationAndMonitors {

    // =========================================================================
    // PRODUCER-CONSUMER USING WAIT / NOTIFY
    // =========================================================================
    static class DataBuffer {
        private String packet;
        // True if receiver should wait, False if sender should wait
        private boolean isTransferring = true;

        // Producer calls this
        public synchronized void send(String packet) {
            // MUST be a while loop (to prevent Spurious Wakeups)
            while (!isTransferring) {
                try { 
                    wait(); // Releases lock, goes to WAITING state
                } catch (InterruptedException e)  {
                    Thread.currentThread().interrupt(); 
                }
            }
            isTransferring = false;
            this.packet = packet;
            System.out.println("    [Sender] Sent: " + packet);
            
            notifyAll(); // Wakes up the receiver
        }

        // Consumer calls this
        public synchronized String receive() {
            while (isTransferring) {
                try {
                    wait(); // Releases lock, goes to WAITING state
                } catch (InterruptedException e)  {
                    Thread.currentThread().interrupt(); 
                }
            }
            isTransferring = true;
            String received = this.packet;
            System.out.println("    [Receiver] Received: " + received);
            
            notifyAll(); // Wakes up the sender
            return received;
        }
    }

    // =========================================================================
    // SYNCHRONIZED METHOD vs SYNCHRONIZED BLOCK
    // =========================================================================
    static class SyncDemo {
        
        // This locks 'this' object
        public synchronized void methodLock() {
            System.out.println("    Lock acquired on THIS object.");
        }

        // This is exactly equivalent to the above
        public void blockLockThis() {
            synchronized (this) {
                System.out.println("    Lock acquired on THIS object inside block.");
            }
        }

        // This locks the CLASS object (for static methods)
        public static synchronized void staticLock() {
            System.out.println("    Lock acquired on SyncDemo.class");
        }

        // Fine-grained locking: Better performance!
        private final Object customLock1 = new Object();
        private final Object customLock2 = new Object();

        public void fineGrainedLocking() {
            synchronized (customLock1) {
                // Do task 1
            }
            synchronized (customLock2) {
                // Do task 2 (Thread B can do task 2 while Thread A does task 1!)
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 32: Monitors & wait/notify (Interview)     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ PART 1: Producer-Consumer (wait/notifyAll) ━━━");
        DataBuffer buffer = new DataBuffer();

        Thread sender = new Thread(() -> {
            String[] packets = { "Packet 1", "Packet 2", "Packet 3", "End" };
            for (String p : packets) {
                buffer.send(p);
                try { Thread.sleep(200); } catch (Exception e) {}
            }
        });

        Thread receiver = new Thread(() -> {
            for (String received = buffer.receive(); !"End".equals(received); received = buffer.receive()) {
                try { Thread.sleep(300); } catch (Exception e) {}
            }
        });

        sender.start();
        receiver.start();
        
        sender.join();
        receiver.join();
        System.out.println();

        System.out.println("━━━ ⚠️ COMMON INTERVIEW TRAPS ━━━");
        System.out.println("1. Q: notify() vs notifyAll()?");
        System.out.println("   A: notify() wakes up ONE random waiting thread. notifyAll() wakes up ALL.");
        System.out.println("      Always use notifyAll() unless you are 100% sure only 1 thread is waiting");
        System.out.println("      and they all wait for the exact same condition. Otherwise: Deadlock.");
        System.out.println();
        System.out.println("2. Q: Can I synchronize on a String or Integer?");
        System.out.println("   A: NO! String literals and Boxed Integers (-128 to 127) are cached in pools.");
        System.out.println("      If you sync on \"Lock\", someone else in another class might also sync on \"Lock\".");
        System.out.println("      You will deadlock the entire JVM. Always use: `private final Object lock = new Object();`");
        System.out.println();

        System.out.println("✅ Lesson complete! Next up: D_AdvancedLocks.java");
    }
}
