package com.ultimate.java.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                      ARRAYLIST DEEP DIVE                                   ║
 * ║           The Dynamic Array — Java's Most Used Collection                  ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │  • F_Arrays.java (arrays are fixed-size, indexed)                        │
 * │  • OOP (classes, generics preview)                                       │
 * │  • B_WrapperClasses.java (ArrayList uses objects, not primitives!)       │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS AN ARRAYLIST?                                                     │
 * │                                                                           │
 * │  ArrayList = A RESIZABLE ARRAY.                                           │
 * │                                                                           │
 * │  Regular array: Fixed-size box → buy a 6-egg carton, that's it.         │
 * │  ArrayList:     Expandable bag → keeps growing as you add more eggs!     │
 * │                                                                           │
 * │  Under the hood, ArrayList uses a regular array internally.              │
 * │  When it fills up, it creates a BIGGER array (~1.5x) and copies          │
 * │  everything over. You don't see this — it handles it automatically!      │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY USE ARRAYLIST OVER ARRAYS?                                           │
 * │                                                                           │
 * │  Array: int[] scores = new int[5];  ← Can NEVER grow past 5!            │
 * │  ArrayList: List<Integer> scores = new ArrayList<>(); ← Grows forever!  │
 * │                                                                           │
 * │  ArrayList advantages:                                                    │
 * │    ✅ Dynamic size (grows and shrinks automatically)                     │
 * │    ✅ Built-in methods (add, remove, search, sort)                       │
 * │    ✅ Works with generics (type-safe)                                    │
 * │    ✅ Implements List interface (polymorphism!)                           │
 * │                                                                           │
 * │  Array advantages (when to prefer arrays):                                │
 * │    ✅ Slightly faster (no overhead)                                       │
 * │    ✅ Works with primitives (no boxing)                                   │
 * │    ✅ Multi-dimensional (2D arrays are cleaner)                           │
 * │    ✅ Fixed-size is a FEATURE sometimes (safety)                         │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class A_ArrayListDeepDive {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 20: ArrayList Deep Dive                    ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: CREATING ARRAYLISTS
        // =====================================================================
        System.out.println("━━━ PART 1: Creating ArrayLists ━━━");
        System.out.println();

        // Way 1: Empty list
        List<String> fruits = new ArrayList<>();  // Diamond operator <> — Java infers type
        System.out.println("  Empty list: " + fruits + "  (size=" + fruits.size() + ")");

        // Way 2: With initial capacity (optimization — avoids resizing)
        List<Integer> numbers = new ArrayList<>(100);  // Room for 100 elements (not size!)
        System.out.println("  With capacity 100: " + numbers + "  (size=" + numbers.size() + ", NOT 100!)");

        // Way 3: From existing collection
        List<String> copy = new ArrayList<>(List.of("A", "B", "C"));
        System.out.println("  From List.of(): " + copy);

        // Way 4: List.of() — creates an IMMUTABLE list (Java 9+)
        List<String> immutable = List.of("X", "Y", "Z");
        System.out.println("  List.of(): " + immutable + " (immutable! can't add/remove)");
        System.out.println();

        // 💡 Best practice: Declare as List<>, not ArrayList<>
        // This is "programming to the interface" — makes it easy to swap implementations.
        System.out.println("  💡 Declare as List<String> (interface), not ArrayList<String> (class)");
        System.out.println("     List<String> names = new ArrayList<>();  ✅ (flexible)");
        System.out.println("     ArrayList<String> names = new ArrayList<>();  ⚠️ (less flexible)");
        System.out.println();

        // =====================================================================
        // PART 2: ADDING ELEMENTS
        // =====================================================================
        System.out.println("━━━ PART 2: Adding Elements ━━━");
        System.out.println();

        fruits.add("Apple");       // Add to end
        fruits.add("Banana");
        fruits.add("Cherry");
        System.out.println("  After add(): " + fruits);

        fruits.add(1, "Blueberry"); // Add at specific index (shifts others right)
        System.out.println("  After add(1, \"Blueberry\"): " + fruits);

        fruits.addAll(List.of("Date", "Elderberry")); // Add multiple
        System.out.println("  After addAll(): " + fruits);
        System.out.println();

        // =====================================================================
        // PART 3: ACCESSING ELEMENTS
        // =====================================================================
        System.out.println("━━━ PART 3: Accessing Elements ━━━");
        System.out.println();

        System.out.println("  fruits.get(0) = " + fruits.get(0) + "  (first element)");
        System.out.println("  fruits.get(2) = " + fruits.get(2));
        System.out.println("  fruits.size() = " + fruits.size());
        System.out.println("  fruits.isEmpty() = " + fruits.isEmpty());
        System.out.println("  fruits.contains(\"Cherry\") = " + fruits.contains("Cherry"));
        System.out.println("  fruits.indexOf(\"Cherry\") = " + fruits.indexOf("Cherry"));
        System.out.println();

        // =====================================================================
        // PART 4: MODIFYING & REMOVING
        // =====================================================================
        System.out.println("━━━ PART 4: Modifying & Removing ━━━");
        System.out.println();

        System.out.println("  Before: " + fruits);

        fruits.set(0, "Avocado");  // Replace element at index
        System.out.println("  set(0, \"Avocado\"): " + fruits);

        fruits.remove("Banana");   // Remove by value (first occurrence)
        System.out.println("  remove(\"Banana\"): " + fruits);

        String removed = fruits.remove(0);  // Remove by index, returns the removed element
        System.out.println("  remove(0) returned: \"" + removed + "\" → " + fruits);

        // Clear all
        List<String> temp = new ArrayList<>(List.of("X", "Y", "Z"));
        temp.clear();
        System.out.println("  After clear(): " + temp + "  (size=" + temp.size() + ")");
        System.out.println();

        // =====================================================================
        // PART 5: ITERATING
        // =====================================================================
        System.out.println("━━━ PART 5: Iterating ━━━");
        System.out.println();

        List<String> colors = new ArrayList<>(List.of("Red", "Green", "Blue", "Yellow"));

        // Method 1: for-each (PREFERRED for simple iteration)
        System.out.println("  for-each:");
        for (String color : colors) {
            System.out.println("    🎨 " + color);
        }
        System.out.println();

        // Method 2: Classic for (when you need the index)
        System.out.println("  Classic for (with index):");
        for (int i = 0; i < colors.size(); i++) {
            System.out.println("    [" + i + "] " + colors.get(i));
        }
        System.out.println();

        // Method 3: Iterator (when you need to remove during iteration)
        System.out.println("  Iterator (safe removal during iteration):");
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8));
        Iterator<Integer> it = nums.iterator();
        while (it.hasNext()) {
            int n = it.next();
            if (n % 2 == 0) {
                it.remove();  // Safe removal! Unlike list.remove() inside for-each.
            }
        }
        System.out.println("    After removing evens: " + nums);
        System.out.println();

        // Method 4: forEach with lambda (Java 8+)
        System.out.print("  forEach lambda: ");
        colors.forEach(c -> System.out.print(c + " "));
        System.out.println();
        System.out.println();

        // =====================================================================
        // PART 6: SORTING
        // =====================================================================
        System.out.println("━━━ PART 6: Sorting ━━━");
        System.out.println();

        List<Integer> unsorted = new ArrayList<>(List.of(34, 12, 78, 56, 23, 91, 45));
        System.out.println("  Before sort: " + unsorted);

        Collections.sort(unsorted);  // Natural order (ascending)
        System.out.println("  After sort:  " + unsorted);

        Collections.sort(unsorted, Comparator.reverseOrder());  // Descending
        System.out.println("  Descending:  " + unsorted);

        // Sorting strings
        List<String> names = new ArrayList<>(List.of("Charlie", "Alice", "Bob", "David"));
        Collections.sort(names);
        System.out.println("  Sorted names: " + names);

        // Custom sort with Comparator (sort by string length)
        names.sort(Comparator.comparingInt(String::length));
        System.out.println("  By length:    " + names);
        System.out.println();

        // =====================================================================
        // PART 7: USEFUL OPERATIONS
        // =====================================================================
        System.out.println("━━━ PART 7: Useful Operations ━━━");
        System.out.println();

        List<Integer> data = new ArrayList<>(List.of(3, 1, 4, 1, 5, 9, 2, 6, 5));
        System.out.println("  data = " + data);
        System.out.println("  Collections.min() = " + Collections.min(data));
        System.out.println("  Collections.max() = " + Collections.max(data));
        System.out.println("  Collections.frequency(data, 1) = " + Collections.frequency(data, 1));
        System.out.println("  data.subList(2, 5) = " + data.subList(2, 5) + "  (index 2 to 4)");

        Collections.reverse(data);
        System.out.println("  reversed = " + data);

        Collections.shuffle(data);
        System.out.println("  shuffled = " + data);
        System.out.println();

        // Convert array ↔ ArrayList
        System.out.println("  Array → ArrayList: new ArrayList<>(List.of(...))");
        System.out.println("  ArrayList → Array: list.toArray(new String[0])");
        String[] arr = colors.toArray(new String[0]);
        System.out.println("  colors as array: " + java.util.Arrays.toString(arr));
        System.out.println();

        // =====================================================================
        // INTERNAL WORKING
        // =====================================================================
        System.out.println("━━━ How ArrayList Works Internally ━━━");
        System.out.println();
        System.out.println("  ArrayList uses a REGULAR ARRAY internally!");
        System.out.println("  Default initial capacity: 10");
        System.out.println("  When full → creates new array of ~1.5x size → copies elements");
        System.out.println();
        System.out.println("  Performance (Big-O):");
        System.out.println("  ┌────────────────────┬──────────┬──────────────────────────┐");
        System.out.println("  │ Operation          │ Time     │ Why                      │");
        System.out.println("  ├────────────────────┼──────────┼──────────────────────────┤");
        System.out.println("  │ get(index)         │ O(1) ⚡  │ Direct array access      │");
        System.out.println("  │ add(element) end   │ O(1)* ⚡ │ Append to end            │");
        System.out.println("  │ add(index, elem)   │ O(n) 🐌 │ Must shift elements right │");
        System.out.println("  │ remove(index)      │ O(n) 🐌 │ Must shift elements left  │");
        System.out.println("  │ contains(elem)     │ O(n) 🐌 │ Must scan entire list     │");
        System.out.println("  │ size()             │ O(1) ⚡  │ Stored as a field         │");
        System.out.println("  └────────────────────┴──────────┴──────────────────────────┘");
        System.out.println("  * Amortized O(1) — usually O(1), occasionally O(n) for resizing");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. ConcurrentModificationException:");
        System.out.println("   for (String s : list) { list.remove(s); }  ← 💥 CRASH!");
        System.out.println("   Use Iterator.remove() or removeIf() instead.");
        System.out.println();
        System.out.println("2. Using ArrayList<int> instead of ArrayList<Integer>:");
        System.out.println("   Generics only work with OBJECTS, not primitives!");
        System.out.println();
        System.out.println("3. Confusing remove(int index) with remove(Object):");
        System.out.println("   List<Integer> list = ...;");
        System.out.println("   list.remove(3);  ← Removes at INDEX 3, not the value 3!");
        System.out.println("   list.remove(Integer.valueOf(3));  ← Removes the VALUE 3.");
        System.out.println();

        System.out.println("✅ Lesson complete! Next up: B_LinkedListDeepDive.java");
    }
}
