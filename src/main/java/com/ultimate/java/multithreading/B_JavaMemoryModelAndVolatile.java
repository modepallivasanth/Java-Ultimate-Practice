package com.ultimate.java.multithreading;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                  JAVA MEMORY MODEL & VOLATILE (INTERVIEW)                  ║
 * ║                 Happens-Before, CPU Caches, & Visibility                   ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ INTERVIEW QUESTION: "What is the Java Memory Model (JMM)?"                │
 * │                                                                           │
 * │  The JMM specifies how threads interact through memory. It defines WHEN   │
 * │  changes to a variable made by Thread A become VISIBLE to Thread B.       │
 * │                                                                           │
 * │  Modern CPUs have L1, L2, and L3 caches. Threads usually read/write to    │
 * │  these caches instead of Main Memory (RAM) for performance.               │
 * │  Because of this, Thread A might update 'x = 5' in Core 1's L1 cache,     │
 * │  but Thread B on Core 2 still reads 'x = 0' from its own cache!           │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "What does the 'volatile' keyword do?"                │
 * │                                                                           │
 * │  1. Visibility Guarantee: Forces read/writes directly to MAIN MEMORY,     │
 * │     bypassing the CPU cache. (Guarantees all threads see the latest value)│
 * │  2. Prevents Instruction Reordering: The JVM/CPU tries to reorder your    │
 * │     code lines for optimization. 'volatile' inserts a "Memory Barrier",   │
 * │     preventing reordering across that variable.                           │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "What is the Happens-Before relationship?"            │
 * │                                                                           │
 * │  A set of rules guaranteeing memory visibility. E.g., A write to a        │
 * │  volatile variable 'happens-before' any subsequent read of that variable. │
 * │  Releasing a lock 'happens-before' another thread acquires that lock.     │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class B_JavaMemoryModelAndVolatile {

    // =========================================================================
    // THE VISIBILITY PROBLEM
    // =========================================================================
    static class VisibilityProblem {
        // Without 'volatile', the loop thread might cache this as 'true' forever!
        // Adding 'volatile' fixes the infinite loop.
        volatile boolean running = true;

        void start() {
            new Thread(() -> {
                long count = 0;
                // If running is not volatile, CPU optimizes this to: `while(true)`
                while (running) {
                    count++;
                }
                System.out.println("    [Visibility] Thread stopped! Count: " + count);
            }).start();
        }

        void stop() {
            running = false;
            System.out.println("    [Visibility] Stop signal set to false.");
        }
    }

    // =========================================================================
    // THE HAPPENS-BEFORE GUARANTEE (Piggybacking)
    // =========================================================================
    static class HappensBefore {
        int a = 0;
        volatile boolean flag = false;

        void writer() {
            a = 1;         // Action 1
            flag = true;   // Action 2 (volatile write)
            // Rule: Action 1 happens-before Action 2
        }

        void reader() {
            if (flag) {    // Action 3 (volatile read)
                // Rule: Action 2 happens-before Action 3.
                // THEREFORE: Action 1 happens-before Action 4!
                // 'a' is GUARANTEED to be exactly 1 here. (It "piggybacked" on the volatile barrier).
                System.out.println("    [Happens-Before] Read a = " + a); 
            }
        }
    }

    // =========================================================================
    // FALSE SHARING (Advanced Performance Issue)
    // =========================================================================
    // CPU caches load data in "Cache Lines" (usually 64 bytes).
    // If two threads modify two INDEPENDENT variables that happen to sit next 
    // to each other in RAM (on the same 64-byte cache line), they will constantly
    // invalidate each other's cache! This destroys performance.
    // Solution: Padding (e.g. @Contended annotation in Java 8+ or manually adding dummy vars)
    static class FalseSharing {
        volatile long var1 = 0;
        
        // Manual padding to push var2 to the next 64-byte cache line!
        long p1, p2, p3, p4, p5, p6, p7; 
        
        volatile long var2 = 0;
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 31: JMM & Volatile (Interview)             ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ PART 1: The Visibility Problem ━━━");
        VisibilityProblem demo = new VisibilityProblem();
        demo.start();
        
        Thread.sleep(100); // Let it spin
        demo.stop();
        Thread.sleep(100); // Wait for print
        System.out.println();

        System.out.println("━━━ PART 2: Happens-Before Guarantee ━━━");
        HappensBefore hb = new HappensBefore();
        // Simulating the thread timing
        hb.writer();
        hb.reader();
        System.out.println();

        System.out.println("━━━ ⚠️ COMMON INTERVIEW TRAPS ━━━");
        System.out.println("1. Q: Does volatile guarantee atomicity?");
        System.out.println("   A: NO! `count++` with a volatile variable is still NOT thread-safe.");
        System.out.println("      `count++` is read-modify-write. Volatile only fixes VISIBILITY.");
        System.out.println("      Use AtomicInteger for atomicity.");
        System.out.println();
        System.out.println("2. Q: What is Instruction Reordering?");
        System.out.println("   A: To optimize speed, compilers/CPUs rearrange code execution order");
        System.out.println("      if it doesn't change single-thread logic. But in multi-threading,");
        System.out.println("      this can break logic. Volatile inserts a Memory Barrier to stop it.");
        System.out.println();

        System.out.println("✅ Lesson complete! Next up: C_SynchronizationAndMonitors.java");
    }
}
