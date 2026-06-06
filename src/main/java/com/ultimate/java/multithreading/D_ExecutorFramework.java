package com.ultimate.java.multithreading;

import java.util.concurrent.*;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                      EXECUTOR FRAMEWORK IN JAVA                            ║
 * ║                 Thread Pools & Managing Work Smartly                       ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ THE PROBLEM WITH new Thread()                                             │
 * │                                                                           │
 * │  Creating an OS thread is EXPENSIVE. It takes time and uses ~1MB RAM.     │
 * │  If you get 10,000 web requests and do `new Thread()` for each, your      │
 * │  server will crash (OutOfMemoryError).                                    │
 * │                                                                           │
 * │ THE SOLUTION: THREAD POOLS (Executor Framework)                           │
 * │                                                                           │
 * │  Think: A TAXI STAND 🚖                                                  │
 * │    Instead of building a brand new taxi every time a passenger arrives,   │
 * │    you have a POOL of 10 taxis.                                           │
 * │    Passenger arrives → gets in a taxi.                                    │
 * │    Taxi finishes → goes BACK to the pool to wait for the next passenger.  │
 * │    If 15 passengers arrive? 10 get taxis, 5 wait in a Queue.              │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class D_ExecutorFramework {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 33: Executor Framework                     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: THE FIXED THREAD POOL (Standard)
        // =====================================================================
        System.out.println("━━━ PART 1: Fixed Thread Pool ━━━");
        
        // Create a pool of exactly 3 threads
        ExecutorService fixedPool = Executors.newFixedThreadPool(3);

        // Submit 5 tasks to the pool of 3 threads
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            fixedPool.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("    🚖 [" + threadName + "] Executing Task " + taskId);
                try { Thread.sleep(500); } catch (Exception e) {}
            });
        }
        
        // ⚠️ CRITICAL: You MUST shut down the executor, or your app will never exit!
        // shutdown() means "Stop accepting new tasks, finish the ones in the queue, then die."
        fixedPool.shutdown(); 
        
        try {
            // Wait for all tasks to finish (up to 5 seconds)
            fixedPool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();

        // =====================================================================
        // PART 2: RUNNABLE vs CALLABLE (Getting a result back!)
        // =====================================================================
        System.out.println("━━━ PART 2: Callable & Future ━━━");
        System.out.println("  Runnable: Does work, returns nothing (void).");
        System.out.println("  Callable: Does work, RETURNS a value, CAN throw exceptions!");
        System.out.println();

        ExecutorService pool = Executors.newSingleThreadExecutor();

        // Submitting a Callable (returns a String)
        Callable<String> weatherTask = () -> {
            System.out.println("    ☁️ Checking weather API...");
            Thread.sleep(1000); // Simulate network call
            return "Sunny ☀️";
        };

        // We get a 'Future' back immediately. It's a PROMISE of a future result.
        Future<String> futureResult = pool.submit(weatherTask);
        
        System.out.println("    [Main] Submitted task! Main thread can do other things now...");
        
        try {
            // .get() will BLOCK the main thread until the result is ready!
            String result = futureResult.get(); 
            System.out.println("    [Main] Result received: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        
        pool.shutdown();
        System.out.println();

        // =====================================================================
        // PART 3: SCHEDULED THREAD POOL
        // =====================================================================
        System.out.println("━━━ PART 3: Scheduled Thread Pool ━━━");
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        System.out.println("    ⏰ Scheduling a delayed task in 2 seconds...");
        
        // Run ONCE after 2 seconds
        scheduler.schedule(() -> {
            System.out.println("    🔔 DING! 2 seconds have passed!");
        }, 2, TimeUnit.SECONDS);

        // (We won't demo repeating tasks here so the program can exit, 
        // but you would use scheduleAtFixedRate for recurring tasks!)
        
        scheduler.shutdown();
        try { scheduler.awaitTermination(3, TimeUnit.SECONDS); } catch (Exception e) {}
        System.out.println();

        // =====================================================================
        // OTHER POOL TYPES
        // =====================================================================
        System.out.println("━━━ Types of Executors ━━━");
        System.out.println("  1. newFixedThreadPool(n)   - Fixed number of threads. Queue if busy.");
        System.out.println("  2. newCachedThreadPool()   - Creates new threads as needed, kills idle ones after 60s.");
        System.out.println("                               (Good for many short-lived tasks).");
        System.out.println("  3. newSingleThreadExecutor - 1 thread, guarantees sequential execution.");
        System.out.println("  4. newScheduledThreadPool  - For delayed or repeating tasks.");
        System.out.println();

        System.out.println("✅ Lesson complete! Next up: E_CompletableFuture.java");
    }
}
