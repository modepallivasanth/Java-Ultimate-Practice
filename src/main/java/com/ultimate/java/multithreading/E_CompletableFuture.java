package com.ultimate.java.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                      COMPLETABLE FUTURE (Java 8+)                          ║
 * ║                 Modern, Non-Blocking Async Pipelines                       ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ THE PROBLEM WITH STANDARD FUTURES                                         │
 * │                                                                           │
 * │  In the last lesson, we used Future.get().                                │
 * │  Future.get() BLOCKS the thread until the result is ready!                │
 * │  If you are building a high-performance app, blocking is bad.             │
 * │                                                                           │
 * │ THE SOLUTION: COMPLETABLE FUTURE                                          │
 * │                                                                           │
 * │  Instead of waiting, we attach a CALLBACK.                                │
 * │  "Hey, go fetch the user data. WHEN you are done, pass it to this next    │
 * │  function to format it, and WHEN that's done, print it."                  │
 * │                                                                           │
 * │  Think: A RESTAURANT ORDER SYSTEM 🍔                                     │
 * │    Standard Future: Waiter gives order to kitchen, then STANDS THERE      │
 * │                     staring at the chef until the burger is done.         │
 * │    CompletableFuture: Waiter gives order, attaches a buzzer, and GOES     │
 * │                       TO SERVE OTHER TABLES. When the buzzer goes off,    │
 * │                       the waiter takes the food to the customer!          │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class E_CompletableFuture {

    // Simulated slow API call
    static String fetchOrderDetails() {
        System.out.println("    [Thread: " + Thread.currentThread().getName() + "] Fetching order from DB...");
        try { Thread.sleep(1000); } catch (Exception e) {}
        return "Order #1234: Pizza";
    }

    // Simulated processing
    static String enrichOrder(String order) {
        System.out.println("    [Thread: " + Thread.currentThread().getName() + "] Adding price to " + order);
        try { Thread.sleep(500); } catch (Exception e) {}
        return order + " - $15.99";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 34: CompletableFuture                      ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: THE ASYNC PIPELINE (No blocking!)
        // =====================================================================
        System.out.println("━━━ PART 1: The Async Pipeline ━━━");
        
        System.out.println("  [Main] Starting the async chain...");

        // supplyAsync()   = Start a task asynchronously (runs in common ForkJoinPool)
        // thenApply()     = Take the result, transform it (like Stream.map)
        // thenAccept()    = Take the result, consume it (like Stream.forEach)
        
        CompletableFuture<Void> pipeline = CompletableFuture.supplyAsync(() -> fetchOrderDetails())
                .thenApply(order -> enrichOrder(order))
                .thenAccept(finalResult -> {
                    System.out.println("    [Thread: " + Thread.currentThread().getName() + "] DONE! Delivering: " + finalResult);
                });

        System.out.println("  [Main] Pipeline submitted! I am free to do other things!");
        
        // Block main thread so the program doesn't exit before the async tasks finish
        pipeline.join(); 
        System.out.println();

        // =====================================================================
        // PART 2: COMBINING MULTIPLE FUTURES
        // =====================================================================
        System.out.println("━━━ PART 2: Combining Futures ━━━");

        // Start two independent tasks AT THE SAME TIME
        CompletableFuture<Integer> fetchPrice = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(800); } catch (Exception e) {}
            return 100;
        });

        CompletableFuture<Integer> fetchDiscount = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(800); } catch (Exception e) {}
            return 20; // $20 off
        });

        // thenCombine() = wait for BOTH to finish, then combine their results!
        CompletableFuture<Integer> finalPrice = fetchPrice.thenCombine(fetchDiscount, (price, discount) -> {
            System.out.println("    Combined Price (" + price + ") and Discount (" + discount + ")");
            return price - discount;
        });

        System.out.println("  Final combined price: $" + finalPrice.join());
        System.out.println();

        // =====================================================================
        // PART 3: EXCEPTION HANDLING
        // =====================================================================
        System.out.println("━━━ PART 3: Exception Handling ━━━");

        CompletableFuture<String> riskyTask = CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException("API Server Down!");
            return "Success!";
        });

        // exceptionally() = Catch the error and provide a fallback value
        CompletableFuture<String> safeTask = riskyTask.exceptionally(ex -> {
            System.out.println("    Caught error: " + ex.getMessage());
            return "Fallback Default Data";
        });

        System.out.println("  Result from risky task: " + safeTask.join());
        System.out.println();

        // =====================================================================
        // SUMMARY
        // =====================================================================
        System.out.println("━━━ Key Methods ━━━");
        System.out.println("  • supplyAsync() : Start task returning a value");
        System.out.println("  • runAsync()    : Start task returning void");
        System.out.println("  • thenApply()   : Map result to new result");
        System.out.println("  • thenAccept()  : Consume result (void)");
        System.out.println("  • thenCombine() : Wait for 2 futures, combine results");
        System.out.println("  • exceptionally(): Handle errors gracefully");
        System.out.println("  • join()        : Block and wait for final result (throws unchecked exception)");
        System.out.println();

        System.out.println("✅ Lesson complete! Next up: F_VirtualThreads.java");
    }
}
