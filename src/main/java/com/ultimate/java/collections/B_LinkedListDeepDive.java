package com.ultimate.java.collections;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                     LINKEDLIST DEEP DIVE                                   ║
 * ║          Nodes and Pointers — The Chain of Data                            ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS A LINKEDLIST?                                                     │
 * │                                                                           │
 * │  A LinkedList stores elements as NODES connected by POINTERS.            │
 * │                                                                           │
 * │  ArrayList = eggs in a carton (numbered slots, side by side)              │
 * │  LinkedList = a CHAIN OF BOXES, each box points to the next              │
 * │                                                                           │
 * │    ┌───┬───┐    ┌───┬───┐    ┌───┬───┐    ┌───┬───┐                     │
 * │    │ A │ →─┼───→│ B │ →─┼───→│ C │ →─┼───→│ D │ / │                     │
 * │    └───┴───┘    └───┴───┘    └───┴───┘    └───┴───┘                     │
 * │    head                                    tail                          │
 * │                                                                           │
 * │  Each node holds: [data | pointer to next | pointer to previous]         │
 * │  (Java's LinkedList is DOUBLY-LINKED — each node points both ways)       │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHEN TO USE LINKEDLIST vs ARRAYLIST?                                      │
 * │                                                                           │
 * │  Use LinkedList when:                                                     │
 * │    ✅ Frequent insertions/deletions at the BEGINNING or MIDDLE           │
 * │    ✅ You need a Queue (FIFO) or Deque (double-ended queue)              │
 * │    ✅ You don't need random access by index                              │
 * │                                                                           │
 * │  Use ArrayList when:                                                      │
 * │    ✅ Frequent random access by index (get(i))                           │
 * │    ✅ Most operations are at the END of the list                         │
 * │    ✅ You want better cache performance (elements stored contiguously)   │
 * │    ✅ In MOST cases, ArrayList is the better choice! ★                   │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class B_LinkedListDeepDive {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 21: LinkedList Deep Dive                   ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: CREATING & BASIC OPERATIONS
        // =====================================================================
        System.out.println("━━━ PART 1: Basic LinkedList Operations ━━━");
        System.out.println();

        LinkedList<String> list = new LinkedList<>();

        // Add elements
        list.add("Bob");           // Add to end
        list.addFirst("Alice");    // Add to BEGINNING — O(1)! ← LinkedList advantage!
        list.addLast("Charlie");   // Add to END — O(1)!
        list.add(1, "Arnold");     // Add at index

        System.out.println("  List: " + list);
        System.out.println("  getFirst() = " + list.getFirst());
        System.out.println("  getLast()  = " + list.getLast());
        System.out.println("  size()     = " + list.size());
        System.out.println();

        // Remove from both ends
        String first = list.removeFirst();  // O(1)!
        String last = list.removeLast();    // O(1)!
        System.out.println("  removeFirst() = \"" + first + "\"");
        System.out.println("  removeLast()  = \"" + last + "\"");
        System.out.println("  Remaining: " + list);
        System.out.println();

        // =====================================================================
        // PART 2: LINKEDLIST AS A QUEUE (FIFO)
        // =====================================================================
        System.out.println("━━━ PART 2: LinkedList as a Queue (FIFO) ━━━");
        System.out.println();

        // Queue = First In, First Out (like a line at a ticket counter)
        // offer() = add to end, poll() = remove from front
        LinkedList<String> queue = new LinkedList<>();

        queue.offer("Customer 1");  // Join the back of the line
        queue.offer("Customer 2");
        queue.offer("Customer 3");
        System.out.println("  Queue: " + queue);

        System.out.println("  Serving: " + queue.poll());   // Remove from front
        System.out.println("  Serving: " + queue.poll());
        System.out.println("  Remaining: " + queue);
        System.out.println("  Next up (peek): " + queue.peek());  // View front without removing
        System.out.println();

        // =====================================================================
        // PART 3: LINKEDLIST AS A STACK (LIFO)
        // =====================================================================
        System.out.println("━━━ PART 3: LinkedList as a Stack (LIFO) ━━━");
        System.out.println();

        // Stack = Last In, First Out (like a stack of plates)
        // push() = add to top, pop() = remove from top
        LinkedList<String> stack = new LinkedList<>();

        stack.push("Plate 1 (bottom)");
        stack.push("Plate 2");
        stack.push("Plate 3 (top)");
        System.out.println("  Stack: " + stack);

        System.out.println("  Pop: " + stack.pop());  // Removes from top
        System.out.println("  Pop: " + stack.pop());
        System.out.println("  Remaining: " + stack);
        System.out.println();

        // =====================================================================
        // PART 4: ARRAYLIST vs LINKEDLIST PERFORMANCE
        // =====================================================================
        System.out.println("━━━ PART 4: ArrayList vs LinkedList Performance ━━━");
        System.out.println();
        System.out.println("  ┌────────────────────────┬──────────────┬──────────────┐");
        System.out.println("  │ Operation              │  ArrayList   │  LinkedList   │");
        System.out.println("  ├────────────────────────┼──────────────┼──────────────┤");
        System.out.println("  │ get(index)             │  O(1) ⚡ ★   │  O(n) 🐌     │");
        System.out.println("  │ add(end)               │  O(1)* ⚡     │  O(1) ⚡      │");
        System.out.println("  │ add(beginning)         │  O(n) 🐌     │  O(1) ⚡ ★    │");
        System.out.println("  │ add(middle)            │  O(n)        │  O(n)**       │");
        System.out.println("  │ remove(beginning)      │  O(n) 🐌     │  O(1) ⚡ ★    │");
        System.out.println("  │ remove(end)            │  O(1) ⚡      │  O(1) ⚡      │");
        System.out.println("  │ Memory per element     │  Less ★      │  More (pointers)│");
        System.out.println("  │ Cache performance      │  Better ★    │  Worse        │");
        System.out.println("  └────────────────────────┴──────────────┴──────────────┘");
        System.out.println("  * Amortized   ** O(n) to find position + O(1) to insert");
        System.out.println();

        // ── Speed comparison demo ────────────────────────────────────────
        System.out.println("  Speed Demo: Adding 100,000 elements at the BEGINNING:");
        List<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        long start = System.nanoTime();
        for (int i = 0; i < 100_000; i++) linkedList.addFirst(i);
        long linkedTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 100_000; i++) arrayList.add(0, i);
        long arrayTime = System.nanoTime() - start;

        System.out.printf("    LinkedList: %d ms%n", linkedTime / 1_000_000);
        System.out.printf("    ArrayList:  %d ms%n", arrayTime / 1_000_000);
        System.out.println("    → LinkedList wins for beginning insertions!");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Using LinkedList when ArrayList would be better:");
        System.out.println("   In MOST real-world cases, ArrayList is faster due to");
        System.out.println("   CPU cache locality. Use LinkedList only for specific needs.");
        System.out.println();
        System.out.println("2. Using get(i) in a loop on LinkedList:");
        System.out.println("   for (int i=0; i<linkedList.size(); i++) linkedList.get(i);");
        System.out.println("   ↑ O(n²)! Each get() traverses from the head. Use for-each instead.");
        System.out.println();
        System.out.println("3. Choosing based on theory alone:");
        System.out.println("   Always BENCHMARK with your actual data! Theory ≠ practice.");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("1. Implement a simple Queue using LinkedList:");
        System.out.println("   enqueue(item), dequeue(), peek(), isEmpty()");
        System.out.println("2. Reverse a LinkedList without using Collections.reverse()");
        System.out.println("3. Find the middle element of a LinkedList in one pass");
        System.out.println("   (Hint: use two pointers — fast and slow!)");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: C_HashMapDeepDive.java");
    }
}
