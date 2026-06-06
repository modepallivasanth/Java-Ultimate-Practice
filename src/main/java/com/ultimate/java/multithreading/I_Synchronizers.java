package com.ultimate.java.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                      SYNCHRONIZERS (INTERVIEW)                             ║
 * ║                 CountDownLatch, CyclicBarrier, Semaphore                   ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ INTERVIEW QUESTION: "CountDownLatch vs CyclicBarrier?"                    │
 * │                                                                           │
 * │  • CountDownLatch: A one-time switch. It starts at a count (e.g. 3).      │
 * │    Threads call `countDown()`. When it hits 0, any thread waiting on      │
 * │    `await()` is released. It CANNOT be reset.                             │
 * │    (Analogy: A rocket launch. Must wait for Fuel, Engine, and Comms to    │
 * │     be ready before the main thread can launch).                          │
 * │                                                                           │
 * │  • CyclicBarrier: A reusable waiting room. Threads call `await()`.        │
 * │    When N threads arrive at the barrier, the barrier opens, all threads   │
 * │    proceed, and the barrier resets for the next round.                    │
 * │    (Analogy: Friends hiking. They all agree to meet at checkpoint 1       │
 * │     before continuing to checkpoint 2 together).                          │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "When do you use a Semaphore?"                        │
 * │                                                                           │
 * │  It controls ACCESS to a resource with a limited number of permits.       │
 * │  Like a parking lot with 5 spaces. Thread calls `acquire()` to enter.     │
 * │  If 0 spaces left, thread blocks. Calls `release()` when leaving.         │
 * │  Unlike Locks, Thread B can release a permit acquired by Thread A!        │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class I_Synchronizers {

    // =========================================================================
    // COUNT DOWN LATCH
    // =========================================================================
    static void demonstrateLatch() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 1; i <= 3; i++) {
            final int id = i;
            new Thread(() -> {
                try { Thread.sleep((long) (Math.random() * 500)); } catch (Exception e) {}
                System.out.println("    [Service " + id + "] Initialized!");
                latch.countDown(); // Decrement the count
            }).start();
        }

        System.out.println("  [Main] Waiting for all 3 services to initialize...");
        latch.await(); // Main thread blocks until count reaches 0
        System.out.println("  [Main] All services ready! App is fully started!");
    }

    // =========================================================================
    // CYCLIC BARRIER
    // =========================================================================
    static void demonstrateBarrier() {
        // Needs 3 threads to arrive. Once they do, it runs the Runnable action!
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("    [Barrier] All 3 hikers arrived! Barrier is open! (Resetting for next round)");
        });

        for (int i = 1; i <= 3; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    System.out.println("    [Hiker " + id + "] Reached checkpoint 1. Waiting for others...");
                    barrier.await(); // Wait for the other 2 hikers
                    
                    System.out.println("    [Hiker " + id + "] Resuming hike!");
                } catch (Exception e) {}
            }).start();
        }
    }

    // =========================================================================
    // SEMAPHORE
    // =========================================================================
    static void demonstrateSemaphore() throws InterruptedException {
        Semaphore parkingLot = new Semaphore(2); // Only 2 parking spaces!

        for (int i = 1; i <= 4; i++) {
            final int carId = i;
            new Thread(() -> {
                try {
                    System.out.println("    [Car " + carId + "] Waiting for a parking space...");
                    parkingLot.acquire(); // Request a permit
                    
                    System.out.println("    [Car " + carId + "] PARKED!");
                    Thread.sleep(800); // Parked for a while
                    
                    System.out.println("    [Car " + carId + "] Leaving...");
                    parkingLot.release(); // Return the permit
                } catch (InterruptedException e) {}
            }).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 38: Synchronizers (Interview)              ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ PART 1: CountDownLatch ━━━");
        demonstrateLatch();
        System.out.println();

        System.out.println("━━━ PART 2: CyclicBarrier ━━━");
        demonstrateBarrier();
        Thread.sleep(1000); // Wait for hikers to finish
        System.out.println();

        System.out.println("━━━ PART 3: Semaphore ━━━");
        demonstrateSemaphore();
        Thread.sleep(2500); // Wait for all cars
        System.out.println();

        System.out.println("━━━ ⚠️ COMMON INTERVIEW TRAPS ━━━");
        System.out.println("1. Q: What is an Exchanger?");
        System.out.println("   A: It's a synchronization point for exactly TWO threads to swap data.");
        System.out.println("      Thread A arrives with Object 1. Thread B arrives with Object 2.");
        System.out.println("      They swap, so Thread A leaves with Object 2, and B leaves with 1.");
        System.out.println();
        System.out.println("2. Q: Can a Semaphore be used as a Lock (Mutex)?");
        System.out.println("   A: Yes! A Semaphore initialized with 1 permit (a binary semaphore)");
        System.out.println("      acts exactly like a lock, BUT there is no concept of 'Lock Ownership'.");
        System.out.println("      Thread A can acquire the permit, and Thread B can release it! (Not true for ReentrantLock)");
        System.out.println();

        System.out.println("✅ Lesson complete! Next up: J_CompletableFutureAndThreadLocal.java");
    }
}
