package com.ultimate.java.collections;

import java.util.*;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                     QUEUE AND DEQUE DEEP DIVE                              ║
 * ║           First In First Out, Priority Queues & Double-Ended Queues        ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS A QUEUE?                                                          │
 * │                                                                           │
 * │  Queue = FIFO (First In, First Out) — like a line at a coffee shop ☕    │
 * │                                                                           │
 * │  IN → [Person1] [Person2] [Person3] [Person4] → OUT                     │
 * │        (back)                         (front)                            │
 * │                                                                           │
 * │  The first person to join the line is the first to be served.            │
 * │                                                                           │
 * │ WHAT IS A DEQUE?                                                          │
 * │                                                                           │
 * │  Deque = "Double-Ended Queue" — can add/remove from BOTH ends!           │
 * │                                                                           │
 * │  ←→ [A] [B] [C] [D] ←→                                                  │
 * │  Can work as both a Queue AND a Stack!                                    │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class E_QueueAndDeque {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 24: Queue and Deque                        ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: QUEUE (FIFO)
        // =====================================================================
        System.out.println("━━━ PART 1: Queue (FIFO) ━━━");
        System.out.println();

        // Queue interface — use LinkedList or ArrayDeque as implementation
        Queue<String> queue = new LinkedList<>();

        // offer() = add to back, poll() = remove from front, peek() = view front
        queue.offer("Alice");    // Alice joins the line
        queue.offer("Bob");      // Bob joins behind Alice
        queue.offer("Charlie");  // Charlie joins behind Bob
        System.out.println("  Queue: " + queue);

        System.out.println("  peek() = " + queue.peek() + "  (view front, don't remove)");
        System.out.println("  poll() = " + queue.poll() + "  (remove front)");
        System.out.println("  poll() = " + queue.poll());
        System.out.println("  Queue now: " + queue);
        System.out.println("  poll() on empty: " + new LinkedList<>().poll() + "  (returns null, no crash)");
        System.out.println();

        // Two API styles:
        System.out.println("  ┌──────────────┬─────────────────┬─────────────────────┐");
        System.out.println("  │ Operation    │ Throws Exception│ Returns null/false   │");
        System.out.println("  ├──────────────┼─────────────────┼─────────────────────┤");
        System.out.println("  │ Add          │ add()           │ offer() ★ safer     │");
        System.out.println("  │ Remove       │ remove()        │ poll()  ★ safer     │");
        System.out.println("  │ Examine      │ element()       │ peek()  ★ safer     │");
        System.out.println("  └──────────────┴─────────────────┴─────────────────────┘");
        System.out.println("  ★ Use offer/poll/peek — they return null instead of crashing.");
        System.out.println();

        // =====================================================================
        // PART 2: PRIORITY QUEUE (Elements sorted by priority!)
        // =====================================================================
        System.out.println("━━━ PART 2: PriorityQueue ━━━");
        System.out.println();

        // PriorityQueue: elements come out in SORTED order (not insertion order!)
        // Think: Hospital ER — most critical patient goes first, not first arrival.

        // Min-heap by default (smallest first)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(50);
        minHeap.offer(10);
        minHeap.offer(30);
        minHeap.offer(20);
        minHeap.offer(40);
        System.out.println("  PriorityQueue (added 50,10,30,20,40):");
        System.out.print("    Polling order: ");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");  // 10, 20, 30, 40, 50 (sorted!)
        }
        System.out.println("  ← smallest first (min-heap)");

        // Max-heap (largest first) — use Comparator.reverseOrder()
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(List.of(50, 10, 30, 20, 40));
        System.out.print("    Max-heap: ");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");  // 50, 40, 30, 20, 10
        }
        System.out.println(" ← largest first");
        System.out.println();

        // Custom priority: Tasks with priority levels
        record Task(String name, int priority) implements Comparable<Task> {
            @Override
            public int compareTo(Task other) {
                return Integer.compare(this.priority, other.priority);
            }
            @Override
            public String toString() { return name + "(p" + priority + ")"; }
        }

        PriorityQueue<Task> taskQueue = new PriorityQueue<>();
        taskQueue.offer(new Task("Write report", 3));
        taskQueue.offer(new Task("Fix critical bug", 1));
        taskQueue.offer(new Task("Update docs", 5));
        taskQueue.offer(new Task("Code review", 2));

        System.out.println("  Task queue (sorted by priority):");
        while (!taskQueue.isEmpty()) {
            Task t = taskQueue.poll();
            System.out.println("    → " + t);
        }
        System.out.println();

        // =====================================================================
        // PART 3: DEQUE (Double-Ended Queue)
        // =====================================================================
        System.out.println("━━━ PART 3: Deque (Double-Ended Queue) ━━━");
        System.out.println();

        // ArrayDeque — faster than LinkedList for both Stack and Queue!
        Deque<String> deque = new ArrayDeque<>();

        // Add to both ends
        deque.offerFirst("B");   // Add to front
        deque.offerFirst("A");   // Add to front
        deque.offerLast("C");    // Add to back
        deque.offerLast("D");    // Add to back
        System.out.println("  Deque: " + deque);
        System.out.println("  peekFirst() = " + deque.peekFirst());
        System.out.println("  peekLast()  = " + deque.peekLast());

        // Remove from both ends
        System.out.println("  pollFirst() = " + deque.pollFirst());
        System.out.println("  pollLast()  = " + deque.pollLast());
        System.out.println("  Remaining: " + deque);
        System.out.println();

        // ── Deque as STACK (LIFO) ────────────────────────────────────────
        System.out.println("  Deque as Stack (LIFO):");
        Deque<String> stack = new ArrayDeque<>();  // ★ Prefer over Stack class!
        stack.push("First");     // Same as offerFirst
        stack.push("Second");
        stack.push("Third");
        System.out.println("    Stack: " + stack);
        System.out.println("    pop():  " + stack.pop());   // Same as pollFirst
        System.out.println("    pop():  " + stack.pop());
        System.out.println("    Stack: " + stack);
        System.out.println();

        // ── Deque as QUEUE (FIFO) ────────────────────────────────────────
        System.out.println("  Deque as Queue (FIFO):");
        Deque<String> fifo = new ArrayDeque<>();  // ★ Faster than LinkedList for Queue!
        fifo.offer("First");     // Add to back
        fifo.offer("Second");
        fifo.offer("Third");
        System.out.println("    Queue: " + fifo);
        System.out.println("    poll(): " + fifo.poll());   // Remove from front
        System.out.println("    poll(): " + fifo.poll());
        System.out.println("    Queue: " + fifo);
        System.out.println();

        // =====================================================================
        // WHICH TO USE?
        // =====================================================================
        System.out.println("━━━ Which Queue Implementation to Use? ━━━");
        System.out.println();
        System.out.println("  ┌──────────────────────┬────────────────────────────────┐");
        System.out.println("  │ Need                 │ Use                            │");
        System.out.println("  ├──────────────────────┼────────────────────────────────┤");
        System.out.println("  │ Simple Queue (FIFO)  │ ArrayDeque ★ (fastest)         │");
        System.out.println("  │ Simple Stack (LIFO)  │ ArrayDeque ★ (not java.util.Stack!)│");
        System.out.println("  │ Priority ordering    │ PriorityQueue                  │");
        System.out.println("  │ Double-ended ops     │ ArrayDeque                     │");
        System.out.println("  │ Thread-safe queue    │ ConcurrentLinkedQueue          │");
        System.out.println("  │ Blocking queue       │ ArrayBlockingQueue             │");
        System.out.println("  └──────────────────────┴────────────────────────────────┘");
        System.out.println();
        System.out.println("  💡 ArrayDeque > LinkedList for Queue & Stack in most cases!");
        System.out.println("  💡 java.util.Stack is legacy — use Deque instead!");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Using java.util.Stack (legacy!):");
        System.out.println("   Stack<E> is synchronized and slow. Use ArrayDeque instead.");
        System.out.println();
        System.out.println("2. Calling remove()/element() on empty queue → Exception!");
        System.out.println("   Use poll()/peek() instead — they return null.");
        System.out.println();
        System.out.println("3. Thinking PriorityQueue is sorted internally:");
        System.out.println("   It's a HEAP structure. toString() won't show sorted order.");
        System.out.println("   Elements come out sorted ONLY through poll().");
        System.out.println();
        System.out.println("4. Null elements in PriorityQueue/ArrayDeque:");
        System.out.println("   Both reject null! Use LinkedList if you need null support.");
        System.out.println();
        System.out.println("✅ COLLECTIONS MODULE COMPLETE! 🎉");
        System.out.println("   Next module: Advanced Core Java!");
        System.out.println("   Start with: com.ultimate.java.advanced.A_Generics");
    }
}
