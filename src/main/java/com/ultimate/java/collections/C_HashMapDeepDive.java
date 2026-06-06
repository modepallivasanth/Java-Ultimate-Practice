package com.ultimate.java.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                       HASHMAP DEEP DIVE                                    ║
 * ║           Key-Value Pairs — The Swiss Army Knife of Data Storage           ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS A HASHMAP?                                                        │
 * │                                                                           │
 * │  A HashMap stores data as KEY-VALUE pairs.                                │
 * │                                                                           │
 * │  Think: A DICTIONARY / PHONE BOOK                                         │
 * │    Key: Person's name → Value: Their phone number                        │
 * │    "Alice" → "555-0101"                                                   │
 * │    "Bob"   → "555-0202"                                                   │
 * │                                                                           │
 * │  You look up a VALUE by its KEY. Super fast! O(1) on average.            │
 * │                                                                           │
 * │  KEY RULES:                                                               │
 * │    • Keys are UNIQUE (no two entries with the same key)                  │
 * │    • Values can be duplicated                                             │
 * │    • One null key allowed, multiple null values                           │
 * │    • NO guaranteed order of entries!                                      │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ HOW DOES HASHING WORK? (Simplified)                                      │
 * │                                                                           │
 * │  1. HashMap has an array of "buckets" (slots)                            │
 * │  2. When you put("Alice", "555"):                                         │
 * │     a. Java calculates: hashCode("Alice") = 63476538                     │
 * │     b. Maps to bucket index: 63476538 % arraySize = 6                   │
 * │     c. Stores the entry in bucket 6                                      │
 * │  3. When you get("Alice"):                                                │
 * │     a. Same hash → same bucket → found in O(1)!                         │
 * │                                                                           │
 * │  Collision: If two keys hash to the same bucket → stored as a linked    │
 * │  list (or tree if too many) in that bucket.                              │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class C_HashMapDeepDive {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 22: HashMap Deep Dive                      ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: CREATING & BASIC OPERATIONS
        // =====================================================================
        System.out.println("━━━ PART 1: Basic HashMap Operations ━━━");
        System.out.println();

        // Syntax: Map<KeyType, ValueType> map = new HashMap<>();
        Map<String, Integer> ages = new HashMap<>();

        // PUT — add key-value pairs
        ages.put("Alice", 25);
        ages.put("Bob", 30);
        ages.put("Charlie", 28);
        ages.put("Diana", 22);
        System.out.println("  Map: " + ages);

        // GET — retrieve value by key
        System.out.println("  ages.get(\"Alice\") = " + ages.get("Alice"));
        System.out.println("  ages.get(\"Unknown\") = " + ages.get("Unknown") + "  (null if not found)");

        // getOrDefault — safer get
        int age = ages.getOrDefault("Unknown", -1);
        System.out.println("  getOrDefault(\"Unknown\", -1) = " + age);

        // Size and checks
        System.out.println("  size() = " + ages.size());
        System.out.println("  containsKey(\"Bob\") = " + ages.containsKey("Bob"));
        System.out.println("  containsValue(30) = " + ages.containsValue(30));
        System.out.println("  isEmpty() = " + ages.isEmpty());
        System.out.println();

        // =====================================================================
        // PART 2: OVERWRITING & REMOVING
        // =====================================================================
        System.out.println("━━━ PART 2: Overwriting & Removing ━━━");
        System.out.println();

        // PUT with existing key → OVERWRITES the value!
        Integer oldValue = ages.put("Alice", 26);  // Returns old value
        System.out.println("  put(\"Alice\", 26) → old value was: " + oldValue);
        System.out.println("  Now Alice's age: " + ages.get("Alice"));

        // putIfAbsent — only puts if key doesn't exist
        ages.putIfAbsent("Alice", 99);  // Won't overwrite!
        ages.putIfAbsent("Eve", 35);    // Will add!
        System.out.println("  putIfAbsent(\"Alice\", 99): Alice = " + ages.get("Alice") + " (unchanged!)");
        System.out.println("  putIfAbsent(\"Eve\", 35):   Eve = " + ages.get("Eve") + " (added!)");

        // REMOVE
        ages.remove("Eve");
        System.out.println("  After remove(\"Eve\"): " + ages);
        System.out.println();

        // =====================================================================
        // PART 3: ITERATING THROUGH A MAP
        // =====================================================================
        System.out.println("━━━ PART 3: Iterating ━━━");
        System.out.println();

        Map<String, String> capitals = new HashMap<>();
        capitals.put("India", "New Delhi");
        capitals.put("USA", "Washington D.C.");
        capitals.put("Japan", "Tokyo");
        capitals.put("France", "Paris");

        // Method 1: Iterate over entries (MOST COMMON)
        System.out.println("  Method 1: entrySet() — key AND value:");
        for (Map.Entry<String, String> entry : capitals.entrySet()) {
            System.out.println("    " + entry.getKey() + " → " + entry.getValue());
        }
        System.out.println();

        // Method 2: Iterate over keys only
        System.out.println("  Method 2: keySet() — keys only:");
        for (String key : capitals.keySet()) {
            System.out.println("    Key: " + key);
        }
        System.out.println();

        // Method 3: Iterate over values only
        System.out.println("  Method 3: values() — values only:");
        for (String value : capitals.values()) {
            System.out.println("    Capital: " + value);
        }
        System.out.println();

        // Method 4: forEach with lambda (Java 8+)
        System.out.println("  Method 4: forEach lambda:");
        capitals.forEach((country, capital) ->
            System.out.println("    " + country + " → " + capital)
        );
        System.out.println();

        // =====================================================================
        // PART 4: PRACTICAL USE CASES
        // =====================================================================
        System.out.println("━━━ PART 4: Practical Use Cases ━━━");
        System.out.println();

        // Use case 1: Word frequency counter
        String sentence = "the cat sat on the mat the cat likes the mat";
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : sentence.split(" ")) {
            wordCount.merge(word, 1, Integer::sum);  // merge is super clean!
            // Equivalent to:
            // wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        System.out.println("  Word frequency:");
        wordCount.forEach((word, count) ->
            System.out.println("    \"" + word + "\" → " + count)
        );
        System.out.println();

        // Use case 2: Grouping / Index
        String[] students = {"Alice:A", "Bob:B", "Charlie:A", "Diana:B", "Eve:A"};
        Map<String, java.util.List<String>> gradeGroups = new HashMap<>();
        for (String s : students) {
            String[] parts = s.split(":");
            gradeGroups.computeIfAbsent(parts[1], k -> new java.util.ArrayList<>()).add(parts[0]);
        }
        System.out.println("  Students by grade:");
        gradeGroups.forEach((grade, names) ->
            System.out.println("    Grade " + grade + ": " + names)
        );
        System.out.println();

        // =====================================================================
        // PART 5: MAP VARIANTS
        // =====================================================================
        System.out.println("━━━ PART 5: Map Variants ━━━");
        System.out.println();

        // LinkedHashMap — maintains INSERTION order
        Map<String, Integer> linked = new LinkedHashMap<>();
        linked.put("First", 1);
        linked.put("Second", 2);
        linked.put("Third", 3);
        System.out.println("  LinkedHashMap (insertion order): " + linked);

        // TreeMap — sorted by KEY (natural order)
        Map<String, Integer> tree = new TreeMap<>();
        tree.put("Charlie", 3);
        tree.put("Alice", 1);
        tree.put("Bob", 2);
        System.out.println("  TreeMap (sorted keys):          " + tree);

        // Comparison
        System.out.println();
        System.out.println("  ┌──────────────────┬──────────┬─────────────────────┐");
        System.out.println("  │ Map Type          │ Order    │ Performance         │");
        System.out.println("  ├──────────────────┼──────────┼─────────────────────┤");
        System.out.println("  │ HashMap           │ None     │ O(1) get/put ★     │");
        System.out.println("  │ LinkedHashMap      │ Insertion│ O(1) get/put       │");
        System.out.println("  │ TreeMap            │ Sorted   │ O(log n) get/put   │");
        System.out.println("  └──────────────────┴──────────┴─────────────────────┘");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Assuming order in HashMap:");
        System.out.println("   HashMap has NO guaranteed order! Use LinkedHashMap/TreeMap.");
        System.out.println();
        System.out.println("2. Using mutable objects as keys:");
        System.out.println("   If the key's hashCode changes after insertion, you can't find it!");
        System.out.println("   Use immutable keys (String, Integer, etc.).");
        System.out.println();
        System.out.println("3. Not overriding hashCode() with equals():");
        System.out.println("   If you override equals(), MUST also override hashCode()!");
        System.out.println("   Otherwise HashMap won't find your keys correctly.");
        System.out.println();
        System.out.println("4. NullPointerException when unboxing null values:");
        System.out.println("   int x = map.get(\"missing\");  ← 💥 NPE! get() returns null!");
        System.out.println("   Use getOrDefault() or check for null first.");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: D_HashSetDeepDive.java");
    }
}
