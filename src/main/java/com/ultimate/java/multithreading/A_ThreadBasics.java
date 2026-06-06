package com.ultimate.java.multithreading;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                        THREAD BASICS IN JAVA                               ║
 * ║             Doing Multiple Things at the Same Time                         ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS A THREAD?                                                         │
 * │                                                                           │
 * │  A Thread is a single sequence of execution within a program.             │
 * │  "Multithreading" is having multiple threads running concurrently.        │
 * │                                                                           │
 * │  Think: A RESTAURANT KITCHEN 🍳                                          │
 * │    Single-threaded: ONE chef cooks the soup, then bakes the bread,        │
 * │                     then grills the steak. (Very slow!)                   │
 * │    Multi-threaded:  Chef 1 cooks soup, Chef 2 bakes bread, Chef 3 grills  │
 * │                     steak — ALL AT THE SAME TIME! (Fast & efficient!)     │
 * │                                                                           │
 * │ WHY DO WE NEED IT?                                                        │
 * │  • To keep the UI responsive while doing heavy background work.           │
 * │  • To utilize multi-core processors (do actual parallel work).            │
 * │  • To handle multiple clients at once (like a web server).                │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class A_ThreadBasics {

    // =========================================================================
    // PART 1: TWO WAYS TO CREATE A THREAD
    // =========================================================================

    // WAY 1: Extend the Thread class (Less flexible, you can't extend anything else)
    static class MyThread extends Thread {
        @Override
        public void run() {
            // The code that runs IN THE BACKGROUND
            for (int i = 1; i <= 3; i++) {
                System.out.println("    🧵 [MyThread] Cooking soup... step " + i);
                try {
                    Thread.sleep(500); // Pause for 500ms (simulate work)
                } catch (InterruptedException e) {
                    System.out.println("    🧵 [MyThread] I was interrupted!");
                }
            }
            System.out.println("    🧵 [MyThread] Soup is ready!");
        }
    }

    // WAY 2: Implement Runnable interface (BEST PRACTICE! You can still extend other classes)
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println("    🏃 [MyRunnable] Baking bread... step " + i);
                try {
                    Thread.sleep(700); 
                } catch (InterruptedException e) {
                    System.out.println("    🏃 [MyRunnable] I was interrupted!");
                }
            }
            System.out.println("    🏃 [MyRunnable] Bread is ready!");
        }
    }

    // =========================================================================
    // MAIN THREAD
    // =========================================================================
    public static void main(String[] args) {
        
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 30: Thread Basics                          ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("  (Main thread is starting the other threads...)");
        System.out.println();

        // ── 1. Starting Threads ──────────────────────────────────────────
        
        // Create instances
        MyThread thread1 = new MyThread();
        
        // For Runnable, you MUST pass it to a new Thread object!
        Thread thread2 = new Thread(new MyRunnable());
        
        // Way 3: Inline using Lambda (super common for short tasks!)
        Thread thread3 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("    🚀 [LambdaThread] Grilling steak... step " + i);
                try { Thread.sleep(600); } catch (InterruptedException e) {}
            }
            System.out.println("    🚀 [LambdaThread] Steak is ready!");
        });

        // ⚠️ CRITICAL RULE: Call .start(), NEVER call .run() directly!
        // Calling .run() just executes the method in the MAIN thread (no concurrency).
        // Calling .start() asks the OS to spawn a NEW thread.
        thread1.start();
        thread2.start();
        thread3.start();

        // ── 2. The Main Thread keeps going! ─────────────────────────────
        System.out.println("  👨‍🍳 [Main] All chefs are working! Main thread is chilling.");
        
        // ── 3. Joining Threads (Waiting for them to finish) ─────────────
        try {
            // .join() means "Main thread stops here and WAITS for thread1 to finish"
            thread1.join();
            System.out.println("  👨‍🍳 [Main] Soup is done, main thread noticed.");
            
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("  👨‍🍳 [Main] EVERYONE IS DONE! Dinner is served!");
        System.out.println();

        // ── 4. Thread States ─────────────────────────────────────────────
        System.out.println("━━━ Thread States ━━━");
        System.out.println("  1. NEW           - Created but not started");
        System.out.println("  2. RUNNABLE      - Executing in the JVM (or waiting for CPU)");
        System.out.println("  3. BLOCKED       - Waiting for a lock (Synchronization)");
        System.out.println("  4. WAITING       - Waiting indefinitely for another thread (join/wait)");
        System.out.println("  5. TIMED_WAITING - Waiting for a specific time (sleep)");
        System.out.println("  6. TERMINATED    - Completed execution");
        System.out.println();
        
        System.out.println("  Current state of thread1: " + thread1.getState()); // TERMINATED
        System.out.println();

        // ── 5. Common Mistakes ───────────────────────────────────────────
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Calling run() instead of start():");
        System.out.println("   If you call run(), it runs SYNCHRONOUSLY on the current thread.");
        System.out.println("   Always call start() to spawn a new thread!");
        System.out.println();
        System.out.println("2. Not handling InterruptedException:");
        System.out.println("   When a thread is sleeping/waiting, another thread can interrupt it.");
        System.out.println("   You must catch this exception and clean up!");
        System.out.println();
        System.out.println("3. Creating too many threads:");
        System.out.println("   Threads are expensive! OS threads consume RAM (usually 1MB each).");
        System.out.println("   Creating 10,000 threads will crash your app. (Use ThreadPools!)");
        System.out.println();
        
        System.out.println("✅ Lesson complete! Next up: B_SynchronizationAndLocks.java");
    }
}
