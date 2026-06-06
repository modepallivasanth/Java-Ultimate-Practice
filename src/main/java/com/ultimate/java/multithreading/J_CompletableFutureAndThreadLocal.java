package com.ultimate.java.multithreading;

import java.util.concurrent.CompletableFuture;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                  COMPLETABLE FUTURE & THREAD LOCAL (INTERVIEW)             ║
 * ║                 Async Thread Affinity and Memory Leaks                     ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ INTERVIEW QUESTION: "How does ThreadLocal work? Can it cause a memory     │
 * │                      leak?"                                               │
 * │                                                                           │
 * │  ThreadLocal gives EVERY thread its own isolated instance of a variable.  │
 * │  It is heavily used in web servers (like Spring Boot) to store the        │
 * │  current User ID or Database Transaction for the current HTTP request.    │
 * │                                                                           │
 * │  MEMORY LEAK RISK: The ThreadLocalMap is attached to the THREAD object.   │
 * │  If you use Thread Pools (where threads are reused and never die), the    │
 * │  variables you stored in ThreadLocal will stay there FOREVER, leaking     │
 * │  memory and bleeding data into the next user's HTTP request!              │
 * │  FIX: ALWAYS call `threadLocal.remove()` in a `finally` block!            │
 * │                                                                           │
 * │ INTERVIEW QUESTION: "thenApply vs thenApplyAsync in CompletableFuture?"   │
 * │                                                                           │
 * │  CompletableFuture lets you chain tasks. But WHICH thread runs the chain? │
 * │  • thenApply: Runs on the SAME thread that completed the previous task.   │
 * │    (Or if the previous task is already done, it runs on the Main thread). │
 * │  • thenApplyAsync: Forces the JVM to submit the task back into the        │
 * │    ForkJoinPool (or a custom executor). Useful if the next step is very   │
 * │    CPU-heavy and you want to free up the current thread.                  │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class J_CompletableFutureAndThreadLocal {

    // =========================================================================
    // THREAD LOCAL MEMORY LEAK DEMO
    // =========================================================================
    static class RequestContext {
        // Holds the User ID for the current thread
        public static final ThreadLocal<String> USER_ID = new ThreadLocal<>();
        
        static void processRequest(String user) {
            USER_ID.set(user);
            System.out.println("    [Thread " + Thread.currentThread().getName() + "] Processing for " + USER_ID.get());
            
            // IF WE DO NOT CALL USER_ID.remove() HERE:
            // When this thread is returned to the ThreadPool, it still holds "user"!
            // The next time this thread is used for a different request, if they call USER_ID.get()
            // before setting it, they will see the OLD user's data! Security nightmare!
            
            USER_ID.remove(); // The fix!
        }
    }

    // =========================================================================
    // ASYNC THREAD AFFINITY
    // =========================================================================
    static void demonstrateAsync() {
        System.out.println("    [Main] Starts on: " + Thread.currentThread().getName());

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("    [Task 1] Running on: " + Thread.currentThread().getName());
            return "Data";
        });

        // thenApply -> Usually runs on the SAME ForkJoinPool worker as Task 1
        future.thenApply(data -> {
            System.out.println("    [Task 2 (thenApply)] Running on: " + Thread.currentThread().getName());
            return data + " Processed";
        }).join();

        // thenApplyAsync -> Forces the task into the ForkJoinPool queue again
        future.thenApplyAsync(data -> {
            System.out.println("    [Task 3 (thenApplyAsync)] Running on: " + Thread.currentThread().getName());
            return data + " Processed Async";
        }).join();
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 39: CompletableFuture & ThreadLocal        ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ PART 1: Thread Local ━━━");
        Thread t1 = new Thread(() -> RequestContext.processRequest("Alice_123"));
        Thread t2 = new Thread(() -> RequestContext.processRequest("Bob_456"));
        t1.start(); t2.start();
        try { t1.join(); t2.join(); } catch (Exception e) {}
        System.out.println();

        System.out.println("━━━ PART 2: Async Chaining ━━━");
        demonstrateAsync();
        System.out.println();

        System.out.println("━━━ ⚠️ COMMON INTERVIEW TRAPS ━━━");
        System.out.println("1. Q: CompletableFuture 'handle' vs 'exceptionally'?");
        System.out.println("   A: `exceptionally(ex -> ...)` ONLY runs if there was an exception.");
        System.out.println("      `handle((result, ex) -> ...)` ALWAYS runs, whether it succeeded or failed.");
        System.out.println("      It's like a finally block. You must check `if (ex != null)` inside it.");
        System.out.println();

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║  🎉🎉🎉 MODULE 6 COMPLETE! 🎉🎉🎉                    ║");
        System.out.println("║                                                      ║");
        System.out.println("║  You have mastered the hardest Java concurrency      ║");
        System.out.println("║  topics. You are ready for ANY senior interview!     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
}
