package com.ultimate.java.multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                  CONCURRENT COLLECTIONS (INTERVIEW)                        ║
 * ║                 Internal Mechanics of ConcurrentHashMap                    ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ INTERVIEW QUESTION: "How exactly does ConcurrentHashMap work?"            │
 * │                                                                           │
 * │  Java 7: It used "Segment Locking" (Lock Stripping). The map was split    │
 * │  into 16 segments. To write, you locked 1 segment. Max 16 concurrent      │
 * │  writers. (Legacy, bad performance under heavy load).                     │
 * │                                                                           │
 * │  Java 8+: NO MORE SEGMENTS! It uses an array of Nodes.                    │
 * │  - If adding to an EMPTY bucket, it uses CAS (Lock-Free!).                │
 * │  - If there is a collision, it locks ONLY the head Node of that bucket    │
 * │    using `synchronized(node)`.                                            │
 * │  - Like HashMap, buckets turn into Red-Black Trees if there are >8 items. │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "When to use CopyOnWriteArrayList?"                   │
 * │                                                                           │
 * │  A List where EVERY mutation (add/set/remove) creates a fresh copy of     │
 * │  the entire underlying array!                                             │
 * │  Use case: Very rare writes, but extremely frequent iterations (like      │
 * │  a list of Event Listeners). Reads are 100% lock-free!                    │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "BlockingQueue mechanics?"                            │
 * │                                                                           │
 * │  Used heavily in Producer-Consumer patterns (e.g. Thread Pools).          │
 * │  - ArrayBlockingQueue: Bounded (fixed size). Uses a single ReentrantLock. │
 * │  - LinkedBlockingQueue: Unbounded (optional). Uses TWO locks (one for     │
 * │    put, one for take). Higher throughput than ArrayBlockingQueue!         │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class G_ConcurrentCollectionsInternals {

    // =========================================================================
    // BLOCKING QUEUE DEMO
    // =========================================================================
    static class ProducerConsumer {
        // A queue with a strict limit of 3 items
        static BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        static void start() throws InterruptedException {
            Thread producer = new Thread(() -> {
                try {
                    for (int i = 1; i <= 5; i++) {
                        System.out.println("    [Producer] Putting item " + i);
                        // put() BLOCKS if the queue is full! No wait/notify needed!
                        queue.put("Item " + i);
                    }
                } catch (InterruptedException e) {}
            });

            Thread consumer = new Thread(() -> {
                try {
                    Thread.sleep(1000); // Let producer fill the queue
                    while (true) {
                        // take() BLOCKS if the queue is empty!
                        String item = queue.take();
                        System.out.println("    [Consumer] Took " + item);
                        Thread.sleep(500); // Slow consumer
                    }
                } catch (InterruptedException e) {}
            });

            producer.start();
            consumer.start();

            producer.join();
            consumer.interrupt(); // Kill the infinite consumer
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 36: Concurrent Collections (Interview)     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ PART 1: ConcurrentHashMap ━━━");
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        // Java 8 introduced these awesome compute methods!
        // compute() guarantees the update is atomic for that specific key.
        map.compute("Key", (k, v) -> (v == null) ? 1 : v + 1);
        System.out.println("  ConcurrentHashMap value: " + map.get("Key"));
        System.out.println();

        System.out.println("━━━ PART 2: ArrayBlockingQueue ━━━");
        System.out.println("  (Watch how the Producer gets BLOCKED at Item 4 because the queue size is 3)");
        ProducerConsumer.start();
        System.out.println();

        System.out.println("━━━ ⚠️ COMMON INTERVIEW TRAPS ━━━");
        System.out.println("1. Q: Does ConcurrentHashMap.size() lock the whole map?");
        System.out.println("   A: No! Java 7 used to try to count segments. Java 8 uses a LongAdder");
        System.out.println("      (a baseCount + an array of CounterCells) to track size concurrently.");
        System.out.println("      The returned size is an ESTIMATE if the map is being actively modified.");
        System.out.println("      Always use mappingCount() instead of size() because count might exceed Integer.MAX_VALUE!");
        System.out.println();
        System.out.println("2. Q: Why doesn't ConcurrentHashMap allow null keys or values?");
        System.out.println("   A: If map.get(key) returns null, you don't know if the key is MISSING");
        System.out.println("      or if the value is explicitly NULL. In a single-threaded HashMap, you");
        System.out.println("      can call containsKey() to check. In ConcurrentHashMap, another thread");
        System.out.println("      might insert/delete between the get() and containsKey() calls!");
        System.out.println();

        System.out.println("✅ Lesson complete! Next up: H_ExecutorFrameworkInternals.java");
    }
}
