package com.ultimate.java.multithreading;

import java.util.concurrent.*;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                  EXECUTOR FRAMEWORK INTERNALS (INTERVIEW)                  ║
 * ║                 ThreadPoolExecutor Tuning & Rejection Policies             ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ INTERVIEW QUESTION: "Explain ThreadPoolExecutor parameters."              │
 * │                                                                           │
 * │  1. corePoolSize: The minimum number of threads kept alive (even if idle).│
 * │  2. maxPoolSize: The absolute maximum number of threads allowed.          │
 * │  3. keepAliveTime: How long excess threads (above corePoolSize) wait for  │
 * │     new tasks before terminating.                                         │
 * │  4. workQueue: A BlockingQueue holding tasks waiting to be executed.      │
 * │                                                                           │
 * │  THE FLOW:                                                                │
 * │  If threads < corePoolSize -> Create new thread.                          │
 * │  If threads = corePoolSize -> Put task in the Queue.                      │
 * │  If Queue is FULL          -> Create new thread (up to maxPoolSize).      │
 * │  If Queue is FULL & threads = maxPoolSize -> REJECT the task!             │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "What are the 4 Rejection Policies?"                  │
 * │                                                                           │
 * │  1. AbortPolicy (Default): Throws RejectedExecutionException.             │
 * │  2. CallerRunsPolicy: The thread that submitted the task executes it!     │
 * │     (Great for throttling/slowing down the producer).                     │
 * │  3. DiscardPolicy: Silently drops the task.                               │
 * │  4. DiscardOldestPolicy: Evicts the oldest unhandled task in the queue    │
 * │     and tries to submit the new one again.                                │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "ForkJoinPool vs ThreadPoolExecutor?"                 │
 * │                                                                           │
 * │  ForkJoinPool is designed for divide-and-conquer algorithms (like sorting)│
 * │  It uses "Work Stealing": Every thread has its own Deque. If a thread     │
 * │  finishes all its tasks, it "steals" tasks from the tail of another       │
 * │  busy thread's Deque! (Used heavily by Java 8 Parallel Streams).          │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class H_ExecutorFrameworkInternals {

    // =========================================================================
    // CUSTOM THREAD POOL & REJECTION DEMO
    // =========================================================================
    static void demonstrateRejection() throws InterruptedException {
        
        // A tiny pool: 1 core thread, 2 max threads, queue size of 1.
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            1, // corePoolSize
            2, // maximumPoolSize
            5, TimeUnit.SECONDS, // keepAliveTime
            new ArrayBlockingQueue<>(1), // Queue holds max 1 task
            new ThreadPoolExecutor.CallerRunsPolicy() // Our rejection policy
        );

        System.out.println("  Submitting 4 tasks to a highly constrained pool...");
        
        for (int i = 1; i <= 4; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("    [Task " + taskId + "] Running on " + Thread.currentThread().getName());
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            });
            System.out.println("  Submitted Task " + taskId);
        }

        /* What happens here?
           Task 1: Goes to core thread 1.
           Task 2: Queue is empty, so it goes to Queue. (Queue is now full).
           Task 3: Queue is full, pool size (1) < max (2), so creates new thread! Runs on thread 2.
           Task 4: Queue is full, pool is at max (2). REJECTED! 
                   Because we use CallerRunsPolicy, the MAIN thread executes Task 4!
        */
        
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 37: Executor Internals (Interview)         ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ PART 1: The CallerRunsPolicy Demo ━━━");
        demonstrateRejection();
        System.out.println();

        System.out.println("━━━ ⚠️ COMMON INTERVIEW TRAPS ━━━");
        System.out.println("1. Q: Is Executors.newCachedThreadPool() safe for production?");
        System.out.println("   A: NO! Its maxPoolSize is Integer.MAX_VALUE. If you get a spike in");
        System.out.println("      traffic, it will create 10,000 threads and crash your server with");
        System.out.println("      an OutOfMemoryError. Always use custom ThreadPoolExecutors with");
        System.out.println("      hard limits in production.");
        System.out.println();
        System.out.println("2. Q: What happens if a Thread inside the pool throws a RuntimeException?");
        System.out.println("   A: If submitted via `execute()`, the thread dies, the exception stack");
        System.out.println("      trace is printed, and the pool creates a NEW thread to replace it.");
        System.out.println("      If submitted via `submit()`, the exception is swallowed! It is stored");
        System.out.println("      inside the returned Future, and is only thrown when you call `future.get()`.");
        System.out.println();

        System.out.println("✅ Lesson complete! Next up: I_Synchronizers.java");
    }
}
