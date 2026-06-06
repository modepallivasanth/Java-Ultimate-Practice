package com.ultimate.java.multithreading;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║            ATOMIC VARIABLES & CONCURRENT COLLECTIONS                       ║
 * ║               Lock-Free Magic & Thread-Safe Data                           ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT ARE ATOMIC VARIABLES?                                                │
 * │                                                                           │
 * │  Locks (synchronized) are SLOW because they block threads.                │
 * │  Atomic variables (like AtomicInteger) use low-level CPU instructions     │
 * │  (CAS - Compare And Swap) to update values SAFELY without locking!        │
 * │                                                                           │
 * │  Think: Instead of locking the whole room to increment a counter,         │
 * │  you have a magical counter that instantly updates perfectly every time.  │
 * │                                                                           │
 * │ WHAT ARE CONCURRENT COLLECTIONS?                                          │
 * │                                                                           │
 * │  Normal collections (ArrayList, HashMap) crash if multiple threads        │
 * │  modify them at the same time (ConcurrentModificationException).          │
 * │                                                                           │
 * │  java.util.concurrent provides thread-safe versions designed for HIGH     │
 * │  PERFORMANCE (they don't just put a single lock on the whole collection). │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class C_AtomicAndConcurrentCollections {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 32: Atomics & Concurrent Collections       ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: ATOMIC VARIABLES (Lock-Free Thread Safety)
        // =====================================================================
        System.out.println("━━━ PART 1: Atomic Variables ━━━");
        
        // Remember the BadCounter from the last lesson? Here is the PERFECT counter.
        AtomicInteger atomicCount = new AtomicInteger(0);

        Thread t1 = new Thread(() -> { for (int i = 0; i < 10000; i++) atomicCount.incrementAndGet(); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < 10000; i++) atomicCount.incrementAndGet(); });

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("  Expected count: 20000");
        System.out.println("  Atomic count:   " + atomicCount.get() + "  ← Perfect, and NO LOCKS used! ⚡");
        System.out.println();
        System.out.println("  Other atomics: AtomicLong, AtomicBoolean, AtomicReference");
        System.out.println();

        // =====================================================================
        // PART 2: CONCURRENT HASH MAP
        // =====================================================================
        System.out.println("━━━ PART 2: ConcurrentHashMap ━━━");
        
        // Why not Hashtable or Collections.synchronizedMap()?
        // Because they lock the ENTIRE MAP for every read/write. Slow!
        // ConcurrentHashMap only locks the SPECIFIC BUCKET being modified.
        // Multiple threads can read/write at the same time if they touch different buckets!

        Map<String, Integer> map = new ConcurrentHashMap<>();
        
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                // compute is atomic in ConcurrentHashMap!
                map.compute("Clicks", (k, v) -> (v == null) ? 1 : v + 1);
            }
        });
        
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.compute("Clicks", (k, v) -> (v == null) ? 1 : v + 1);
            }
        });

        t3.start(); t4.start();
        t3.join();  t4.join();

        System.out.println("  ConcurrentHashMap 'Clicks': " + map.get("Clicks") + " (Expected: 2000)");
        System.out.println();

        // =====================================================================
        // PART 3: COPY-ON-WRITE ARRAY LIST
        // =====================================================================
        System.out.println("━━━ PART 3: CopyOnWriteArrayList ━━━");
        
        // Problem: Iterating over an ArrayList while another thread adds to it causes a crash.
        // Solution: CopyOnWriteArrayList!
        // How it works: Every time you ADD/REMOVE, it creates a BRAND NEW COPY of the underlying array.
        // Readers read the old array safely without locks!

        CopyOnWriteArrayList<String> safeList = new CopyOnWriteArrayList<>();
        safeList.add("Alice");
        safeList.add("Bob");

        // Thread 1: Iterating
        Thread reader = new Thread(() -> {
            System.out.println("    [Reader] Started iterating...");
            for (String name : safeList) {
                System.out.println("    [Reader] Found: " + name);
                try { Thread.sleep(100); } catch (Exception e) {} // Simulate slow reading
            }
            System.out.println("    [Reader] Done! Notice it didn't see Charlie!");
        });

        // Thread 2: Modifying while Thread 1 is reading!
        Thread writer = new Thread(() -> {
            try { Thread.sleep(50); } catch (Exception e) {}
            System.out.println("    [Writer] Adding Charlie...");
            safeList.add("Charlie"); // This creates a NEW array copy internally
            System.out.println("    [Writer] Added Charlie.");
        });

        reader.start(); writer.start();
        reader.join();  writer.join();

        System.out.println("  Final List: " + safeList);
        System.out.println();

        // =====================================================================
        // COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Using CopyOnWriteArrayList for frequent writes:");
        System.out.println("   It copies the ENTIRE array on every write! It's O(n) and eats RAM.");
        System.out.println("   ONLY use it when Reads heavily outnumber Writes (like a list of event listeners).");
        System.out.println();
        System.out.println("2. Two atomic operations are NOT atomic together:");
        System.out.println("   if (map.containsKey(\"A\")) { map.put(\"A\", 1); }  ← BAD! Thread can interrupt between lines!");
        System.out.println("   map.putIfAbsent(\"A\", 1);  ← GOOD! Single atomic operation.");
        System.out.println();

        System.out.println("✅ Lesson complete! Next up: D_ExecutorFramework.java");
    }
}
