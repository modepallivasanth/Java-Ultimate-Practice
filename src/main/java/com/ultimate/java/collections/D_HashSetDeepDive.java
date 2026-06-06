package com.ultimate.java.collections;

import java.util.*;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                      HASHSET DEEP DIVE                                     ║
 * ║              Unique Elements Only — No Duplicates Allowed!                 ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS A HASHSET?                                                        │
 * │                                                                           │
 * │  A Set is a collection that stores UNIQUE elements — no duplicates!      │
 * │  HashSet is the most common Set implementation.                           │
 * │                                                                           │
 * │  Think: A GUEST LIST for a party 🎉                                      │
 * │    • Each name appears only ONCE                                          │
 * │    • Adding "Alice" twice? She's already on the list — ignored!          │
 * │    • No particular order                                                  │
 * │    • Fast lookup: "Is Bob on the list?" → instant answer!               │
 * │                                                                           │
 * │  SECRET: HashSet is backed by a HashMap internally!                      │
 * │    • Each element is stored as a KEY in the HashMap                      │
 * │    • The value is a dummy constant                                        │
 * │    • That's why it has O(1) operations — it uses hashing!               │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class D_HashSetDeepDive {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 23: HashSet Deep Dive                      ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: CREATING & BASIC OPERATIONS
        // =====================================================================
        System.out.println("━━━ PART 1: Basic Operations ━━━");
        System.out.println();

        Set<String> fruits = new HashSet<>();

        // ADD — returns true if added, false if already exists!
        System.out.println("  add(\"Apple\"):  " + fruits.add("Apple"));   // true
        System.out.println("  add(\"Banana\"): " + fruits.add("Banana"));  // true
        System.out.println("  add(\"Apple\"):  " + fruits.add("Apple"));   // false! Already exists!
        fruits.add("Cherry");
        fruits.add("Date");
        System.out.println("  Set: " + fruits + "  (order may vary!)");
        System.out.println("  Size: " + fruits.size() + "  (Apple counted only once!)");
        System.out.println();

        // CHECK membership — O(1)!
        System.out.println("  contains(\"Apple\"):  " + fruits.contains("Apple"));
        System.out.println("  contains(\"Mango\"):  " + fruits.contains("Mango"));

        // REMOVE
        fruits.remove("Date");
        System.out.println("  After remove(\"Date\"): " + fruits);
        System.out.println();

        // =====================================================================
        // PART 2: REMOVING DUPLICATES FROM A LIST
        // =====================================================================
        System.out.println("━━━ PART 2: Removing Duplicates (Most Common Use!) ━━━");
        System.out.println();

        // THE most common use case for HashSet: deduplication!
        List<Integer> numbers = List.of(1, 3, 5, 3, 7, 1, 9, 5, 3, 7);
        System.out.println("  Original list: " + numbers);

        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        System.out.println("  As HashSet:    " + uniqueNumbers);

        List<Integer> deduped = new ArrayList<>(uniqueNumbers);
        System.out.println("  Back to list:  " + deduped + "  (no duplicates!)");
        System.out.println();

        // Preserve order? Use LinkedHashSet!
        Set<Integer> orderedUnique = new LinkedHashSet<>(numbers);
        System.out.println("  LinkedHashSet: " + orderedUnique + "  (insertion order preserved!)");
        System.out.println();

        // =====================================================================
        // PART 3: SET OPERATIONS (Union, Intersection, Difference)
        // =====================================================================
        System.out.println("━━━ PART 3: Set Operations (Math!) ━━━");
        System.out.println();

        Set<String> setA = new HashSet<>(Set.of("Alice", "Bob", "Charlie", "Diana"));
        Set<String> setB = new HashSet<>(Set.of("Charlie", "Diana", "Eve", "Frank"));

        System.out.println("  Set A: " + setA);
        System.out.println("  Set B: " + setB);
        System.out.println();

        // UNION: All elements from both sets (A ∪ B)
        Set<String> union = new HashSet<>(setA);
        union.addAll(setB);
        System.out.println("  Union (A ∪ B):        " + union);

        // INTERSECTION: Elements in BOTH sets (A ∩ B)
        Set<String> intersection = new HashSet<>(setA);
        intersection.retainAll(setB);
        System.out.println("  Intersection (A ∩ B): " + intersection);

        // DIFFERENCE: Elements in A but NOT in B (A - B)
        Set<String> difference = new HashSet<>(setA);
        difference.removeAll(setB);
        System.out.println("  Difference (A - B):   " + difference);

        // SYMMETRIC DIFFERENCE: Elements in A or B but NOT both
        Set<String> symDiff = new HashSet<>(setA);
        symDiff.addAll(setB);
        Set<String> common = new HashSet<>(setA);
        common.retainAll(setB);
        symDiff.removeAll(common);
        System.out.println("  Symmetric diff:       " + symDiff);
        System.out.println();

        // =====================================================================
        // PART 4: SET VARIANTS
        // =====================================================================
        System.out.println("━━━ PART 4: Set Variants ━━━");
        System.out.println();
        System.out.println("  ┌──────────────────┬──────────────┬────────────────────┐");
        System.out.println("  │ Set Type          │ Order        │ Performance        │");
        System.out.println("  ├──────────────────┼──────────────┼────────────────────┤");
        System.out.println("  │ HashSet           │ None         │ O(1) add/remove ★ │");
        System.out.println("  │ LinkedHashSet      │ Insertion    │ O(1) add/remove   │");
        System.out.println("  │ TreeSet            │ Sorted (asc) │ O(log n)          │");
        System.out.println("  └──────────────────┴──────────────┴────────────────────┘");
        System.out.println();

        // TreeSet — sorted!
        Set<Integer> sorted = new TreeSet<>(List.of(5, 2, 8, 1, 9, 3));
        System.out.println("  TreeSet (auto-sorted): " + sorted);

        // TreeSet with NavigableSet methods
        TreeSet<Integer> navSet = new TreeSet<>(List.of(10, 20, 30, 40, 50));
        System.out.println("  TreeSet: " + navSet);
        System.out.println("  first()   = " + navSet.first());      // 10
        System.out.println("  last()    = " + navSet.last());       // 50
        System.out.println("  lower(30) = " + navSet.lower(30));    // 20 (strictly less)
        System.out.println("  higher(30)= " + navSet.higher(30));   // 40 (strictly greater)
        System.out.println("  headSet(30) = " + navSet.headSet(30)); // [10, 20] (before 30)
        System.out.println("  tailSet(30) = " + navSet.tailSet(30)); // [30, 40, 50] (from 30)
        System.out.println();

        // =====================================================================
        // PART 5: PRACTICAL USE CASES
        // =====================================================================
        System.out.println("━━━ PART 5: Practical Use Cases ━━━");
        System.out.println();

        // Use case 1: Check if all elements are unique
        int[] arr = {1, 2, 3, 4, 5};
        Set<Integer> checkSet = new HashSet<>();
        boolean allUnique = true;
        for (int n : arr) {
            if (!checkSet.add(n)) {
                allUnique = false;
                break;
            }
        }
        System.out.println("  {1,2,3,4,5} all unique? " + allUnique);

        // Use case 2: Find common elements
        List<String> list1 = List.of("apple", "banana", "cherry");
        List<String> list2 = List.of("banana", "date", "cherry");
        Set<String> commonElements = new HashSet<>(list1);
        commonElements.retainAll(list2);
        System.out.println("  Common in lists: " + commonElements);

        // Use case 3: Track visited items
        System.out.println("  Tracking visited pages: use HashSet for O(1) 'already visited?' check");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Expecting order from HashSet:");
        System.out.println("   HashSet has NO guaranteed order. Use LinkedHashSet or TreeSet.");
        System.out.println();
        System.out.println("2. Custom objects without hashCode/equals:");
        System.out.println("   HashSet uses hashCode() and equals() to detect duplicates.");
        System.out.println("   If your class doesn't override both, 'equal' objects may be duplicated!");
        System.out.println();
        System.out.println("3. Modifying objects while in a Set:");
        System.out.println("   If a mutable object's hashCode changes, the Set can't find it anymore!");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: E_QueueAndDeque.java");
    }
}
