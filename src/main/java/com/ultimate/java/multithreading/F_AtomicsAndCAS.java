package com.ultimate.java.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                  ATOMICS & CAS INTERNALS (INTERVIEW)                       ║
 * ║                 Hardware-Level Concurrency & The ABA Problem               ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ INTERVIEW QUESTION: "How do Atomic classes work without locks?"           │
 * │                                                                           │
 * │  They use CAS (Compare-And-Swap), a low-level CPU instruction.            │
 * │  It takes 3 arguments: (Memory Location, Expected Value, New Value).      │
 * │                                                                           │
 * │  Logic: "Look at RAM. If the value is STILL my Expected Value, swap it    │
 * │          with the New Value. If someone else changed it, do nothing."     │
 * │  Because it's a hardware instruction, it is 100% atomic!                  │
 * │  If it fails, Java just loops and tries again (Spin-Wait).                │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "What is the ABA Problem?"                            │
 * │                                                                           │
 * │  CAS only checks if the value is the same. It DOES NOT check if the value │
 * │  changed to something else, and then changed BACK to the original value!  │
 * │                                                                           │
 * │  Thread 1 reads A. Goes to sleep.                                         │
 * │  Thread 2 changes A to B.                                                 │
 * │  Thread 3 changes B back to A!                                            │
 * │  Thread 1 wakes up, sees A, thinks "Oh, nothing changed!" and does the    │
 * │  CAS successfully. But things DID change! This corrupts linked data       │
 * │  structures.                                                              │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "LongAdder vs AtomicLong?"                            │
 * │                                                                           │
 * │  Under HIGH contention (100 threads hitting one AtomicLong), the CAS loop │
 * │  will constantly fail for 99 threads, burning CPU cycles (Spinning).      │
 * │  LongAdder (Java 8) fixes this by giving threads their own internal       │
 * │  counters in an array. When you call .sum(), it adds them all up.         │
 * │  Result: WAY faster under high contention!                                │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class F_AtomicsAndCAS {

    // =========================================================================
    // THE ABA PROBLEM FIX (AtomicStampedReference)
    // =========================================================================
    static class ABADemo {
        // We use a "Stamp" (version number) along with the value!
        static AtomicStampedReference<String> atomicStr = new AtomicStampedReference<>("A", 0);

        static void demonstrateABA() throws InterruptedException {
            int[] initialStamp = new int[1];
            String initialRef = atomicStr.get(initialStamp); // Value: "A", Stamp: 0

            // Thread 2 comes in and does A -> B -> A
            Thread t2 = new Thread(() -> {
                int[] currentStamp = new int[1];
                
                // A -> B (Stamp goes 0 -> 1)
                atomicStr.get(currentStamp);
                atomicStr.compareAndSet("A", "B", currentStamp[0], currentStamp[0] + 1);
                
                // B -> A (Stamp goes 1 -> 2)
                atomicStr.get(currentStamp);
                atomicStr.compareAndSet("B", "A", currentStamp[0], currentStamp[0] + 1);
            });
            t2.start();
            t2.join();

            // Thread 1 wakes up. The value is "A" again. Let's try to CAS using our OLD stamp (0)
            boolean success = atomicStr.compareAndSet(
                initialRef, "NewValue",  // Expected "A", New "NewValue"
                initialStamp[0], initialStamp[0] + 1 // Expected Stamp 0, New Stamp 1
            );

            System.out.println("    CAS Success: " + success); 
            // Result is FALSE! The ABA problem was prevented because the Stamp is now 2!
        }
    }

    // =========================================================================
    // LONGADDER (Java 8)
    // =========================================================================
    static class AdderDemo {
        static LongAdder adder = new LongAdder();

        static void demonstrateAdder() throws InterruptedException {
            Thread[] threads = new Thread[10];
            for (int i = 0; i < 10; i++) {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < 1000; j++) {
                        adder.increment(); // Threads update their own isolated cells!
                    }
                });
                threads[i].start();
            }

            for (Thread t : threads) t.join();

            System.out.println("    Total Sum: " + adder.sum()); // Gathers all cells
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 35: Atomics & CAS Internals (Interview)    ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ PART 1: The ABA Problem ━━━");
        ABADemo.demonstrateABA();
        System.out.println("  (CAS failed as expected, protecting us from ABA!)");
        System.out.println();

        System.out.println("━━━ PART 2: LongAdder vs AtomicLong ━━━");
        AdderDemo.demonstrateAdder();
        System.out.println();

        System.out.println("━━━ ⚠️ COMMON INTERVIEW TRAPS ━━━");
        System.out.println("1. Q: When should I use AtomicReference vs AtomicStampedReference?");
        System.out.println("   A: If you are building Lock-Free linked structures (like a concurrent stack),");
        System.out.println("      you MUST use StampedReference to avoid ABA. If it's just a simple config");
        System.out.println("      value being updated, AtomicReference is fine.");
        System.out.println();
        System.out.println("2. Q: Does LongAdder replace AtomicLong?");
        System.out.println("   A: Not always. LongAdder takes more memory. If contention is low,");
        System.out.println("      AtomicLong is fine. If you need strict atomic read-updates (compareAndSet),");
        System.out.println("      you MUST use AtomicLong. LongAdder is only for fast counting/stats.");
        System.out.println();

        System.out.println("✅ Lesson complete! Next up: G_ConcurrentCollectionsInternals.java");
    }
}
