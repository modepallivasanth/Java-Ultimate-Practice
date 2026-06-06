package com.ultimate.java.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                         GENERICS IN JAVA                                   ║
 * ║            Type Safety Without Sacrifice — Write Once, Use for Any Type    ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT ARE GENERICS?                                                        │
 * │                                                                           │
 * │  Generics let you write code that works with ANY type, while keeping     │
 * │  type safety at compile time.                                             │
 * │                                                                           │
 * │  Think: A VENDING MACHINE template                                        │
 * │    VendingMachine<Snack>  → dispenses snacks                             │
 * │    VendingMachine<Drink>  → dispenses drinks                             │
 * │    VendingMachine<Toy>    → dispenses toys                               │
 * │    SAME machine design, different contents!                               │
 * │                                                                           │
 * │  Without generics (Java 1.0):                                            │
 * │    List list = new ArrayList();                                           │
 * │    list.add("Hello");                                                     │
 * │    list.add(123);        ← Compiles! But is it intentional?              │
 * │    String s = (String) list.get(1);  ← 💥 ClassCastException at runtime!│
 * │                                                                           │
 * │  With generics (Java 5+):                                                │
 * │    List<String> list = new ArrayList<>();                                │
 * │    list.add("Hello");                                                     │
 * │    list.add(123);        ← ❌ COMPILE ERROR! Caught immediately!         │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class A_Generics {

    // =========================================================================
    // PART 1: GENERIC CLASS
    // =========================================================================
    // T = "Type parameter" — a placeholder for any type.
    // Convention: T=Type, E=Element, K=Key, V=Value, N=Number

    static class Box<T> {
        private T content;

        Box(T content) {
            this.content = content;
        }

        T get() { return content; }
        void set(T content) { this.content = content; }

        @Override
        public String toString() {
            return "Box[" + content + "]";
        }
    }

    // Generic class with TWO type parameters
    static class Pair<K, V> {
        private K key;
        private V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() { return key; }
        V getValue() { return value; }

        @Override
        public String toString() {
            return key + " → " + value;
        }
    }

    // =========================================================================
    // PART 2: GENERIC METHOD
    // =========================================================================
    static <T> void printArray(T[] array) {
        System.out.print("    [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    static <T> int countOccurrences(T[] array, T target) {
        int count = 0;
        for (T element : array) {
            if (element.equals(target)) count++;
        }
        return count;
    }

    // =========================================================================
    // PART 3: BOUNDED TYPE PARAMETERS
    // =========================================================================
    // <T extends Number> → T must be Number or a subclass (Integer, Double, etc.)

    static <T extends Number> double sum(List<T> numbers) {
        double total = 0;
        for (T n : numbers) {
            total += n.doubleValue();  // Can call Number methods because T extends Number!
        }
        return total;
    }

    // Multiple bounds: T must extend Comparable AND be Serializable
    static <T extends Comparable<T>> T findMax(List<T> list) {
        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) max = item;
        }
        return max;
    }

    // =========================================================================
    // PART 4: WILDCARDS
    // =========================================================================
    // ? = "unknown type" — used in method PARAMETERS for flexibility

    // Upper bounded: ? extends Number → accepts Number or any subclass
    static double sumWildcard(List<? extends Number> numbers) {
        double total = 0;
        for (Number n : numbers) {
            total += n.doubleValue();
        }
        return total;
    }

    // Lower bounded: ? super Integer → accepts Integer or any superclass
    static void addNumbers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    // Unbounded: ? → accepts any type (read-only usage)
    static void printList(List<?> list) {
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 25: Generics                               ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // ── Generic class ────────────────────────────────────────────────
        System.out.println("━━━ PART 1: Generic Classes ━━━");
        System.out.println();

        Box<String> stringBox = new Box<>("Hello Generics!");
        Box<Integer> intBox = new Box<>(42);
        Box<List<String>> listBox = new Box<>(List.of("A", "B"));

        System.out.println("  " + stringBox + "  (type: String)");
        System.out.println("  " + intBox + "  (type: Integer)");
        System.out.println("  " + listBox + "  (type: List<String>)");
        System.out.println();

        // Pair
        Pair<String, Integer> nameAge = new Pair<>("Alice", 25);
        Pair<Integer, String> idName = new Pair<>(101, "Bob");
        System.out.println("  Pair: " + nameAge);
        System.out.println("  Pair: " + idName);
        System.out.println();

        // ── Generic methods ──────────────────────────────────────────────
        System.out.println("━━━ PART 2: Generic Methods ━━━");
        System.out.println();

        Integer[] intArr = {1, 2, 3, 4, 5};
        String[] strArr = {"Hello", "World", "Java"};

        System.out.println("  printArray(intArr):");
        printArray(intArr);    // T inferred as Integer
        System.out.println("  printArray(strArr):");
        printArray(strArr);    // T inferred as String

        System.out.println("  count of 3 in {1,2,3,3,4,3}: " +
                countOccurrences(new Integer[]{1,2,3,3,4,3}, 3));
        System.out.println();

        // ── Bounded type parameters ──────────────────────────────────────
        System.out.println("━━━ PART 3: Bounded Types ━━━");
        System.out.println();

        List<Integer> ints = List.of(1, 2, 3, 4, 5);
        List<Double> doubles = List.of(1.5, 2.5, 3.5);

        System.out.println("  sum(ints) = " + sum(ints));
        System.out.println("  sum(doubles) = " + sum(doubles));
        // sum(List.of("a","b")); ← ❌ Compile error! String doesn't extend Number

        System.out.println("  max(ints) = " + findMax(ints));
        System.out.println("  max({\"cherry\",\"apple\",\"banana\"}) = " +
                findMax(List.of("cherry", "apple", "banana")));
        System.out.println();

        // ── Wildcards ────────────────────────────────────────────────────
        System.out.println("━━━ PART 4: Wildcards ━━━");
        System.out.println();

        System.out.println("  sumWildcard(ints) = " + sumWildcard(ints));
        System.out.println("  sumWildcard(doubles) = " + sumWildcard(doubles));

        System.out.print("  printList(ints): ");
        printList(ints);
        System.out.print("  printList(strings): ");
        printList(List.of("A", "B", "C"));
        System.out.println();

        // PECS: Producer Extends, Consumer Super
        System.out.println("  💡 PECS Rule (Producer Extends, Consumer Super):");
        System.out.println("     ? extends T → READ from (produces values)");
        System.out.println("     ? super T   → WRITE to (consumes values)");
        System.out.println("     ? (unbounded) → READ as Object only");
        System.out.println();

        // ── Type Erasure ─────────────────────────────────────────────────
        System.out.println("━━━ PART 5: Type Erasure ━━━");
        System.out.println();
        System.out.println("  Generics are a COMPILE-TIME feature only!");
        System.out.println("  At runtime, Box<String> and Box<Integer> are BOTH just Box.");
        System.out.println("  This is called TYPE ERASURE — the type info is erased.");
        System.out.println();
        System.out.println("  Consequences:");
        System.out.println("    • Cannot do: new T()          (type unknown at runtime)");
        System.out.println("    • Cannot do: instanceof T     (erased at runtime)");
        System.out.println("    • Cannot do: new T[10]        (can't create generic arrays)");
        System.out.println("    • Cannot use primitives: Box<int> ← use Box<Integer>");
        System.out.println();

        // ── Common mistakes ──────────────────────────────────────────────
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Using raw types (no generics):");
        System.out.println("   List list = new ArrayList();  ← UNSAFE! No type checking.");
        System.out.println("   List<String> list = new ArrayList<>();  ← ✅ Type-safe!");
        System.out.println();
        System.out.println("2. Thinking List<Integer> is a subtype of List<Number>:");
        System.out.println("   It's NOT! Use List<? extends Number> for flexibility.");
        System.out.println();
        System.out.println("3. Confusing <T extends X> with <? extends X>:");
        System.out.println("   <T extends X> → type parameter (used in class/method declaration)");
        System.out.println("   <? extends X> → wildcard (used in method parameters)");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: B_LambdasAndFunctionalInterfaces.java");
    }
}
