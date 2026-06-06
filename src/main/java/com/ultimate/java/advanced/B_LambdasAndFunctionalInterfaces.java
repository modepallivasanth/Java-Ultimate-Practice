package com.ultimate.java.advanced;

import java.util.*;
import java.util.function.*;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║              LAMBDAS & FUNCTIONAL INTERFACES IN JAVA                       ║
 * ║          Anonymous Functions — Write Less, Do More                         ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS A LAMBDA?                                                         │
 * │                                                                           │
 * │  A lambda is a SHORT, ANONYMOUS FUNCTION — a function without a name.    │
 * │                                                                           │
 * │  Before lambdas (verbose!):                                               │
 * │    Comparator<String> comp = new Comparator<String>() {                  │
 * │        @Override                                                          │
 * │        public int compare(String a, String b) {                          │
 * │            return a.length() - b.length();                                │
 * │        }                                                                  │
 * │    };                                                                     │
 * │                                                                           │
 * │  With lambdas (concise!):                                                 │
 * │    Comparator<String> comp = (a, b) -> a.length() - b.length();          │
 * │                                                                           │
 * │  Syntax:  (parameters) -> { body }                                       │
 * │    or:    (parameters) -> expression  (when body is one line)            │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS A FUNCTIONAL INTERFACE?                                           │
 * │                                                                           │
 * │  A functional interface has exactly ONE abstract method.                  │
 * │  Lambdas are implementations OF functional interfaces.                    │
 * │                                                                           │
 * │  Java provides many built-in ones in java.util.function:                 │
 * │    Predicate<T>    → T → boolean    "test a condition"                   │
 * │    Function<T,R>   → T → R          "transform a value"                  │
 * │    Consumer<T>     → T → void       "use a value"                        │
 * │    Supplier<T>     → () → T         "produce a value"                    │
 * │    UnaryOperator<T> → T → T         "transform same type"               │
 * │    BinaryOperator<T> → (T,T) → T   "combine two values"                │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class B_LambdasAndFunctionalInterfaces {

    // Custom functional interface
    @FunctionalInterface
    interface MathOperation {
        double operate(double a, double b);
    }

    @FunctionalInterface
    interface Greeting {
        String greet(String name);
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 26: Lambdas & Functional Interfaces        ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: LAMBDA SYNTAX VARIATIONS
        // =====================================================================
        System.out.println("━━━ PART 1: Lambda Syntax ━━━");
        System.out.println();

        // Full syntax:
        MathOperation add = (double a, double b) -> { return a + b; };

        // Types inferred:
        MathOperation subtract = (a, b) -> { return a - b; };

        // Single expression (no braces, no return):
        MathOperation multiply = (a, b) -> a * b;

        // Single parameter (no parentheses needed):
        Greeting hello = name -> "Hello, " + name + "!";

        // No parameters:
        Supplier<String> randomGreeting = () -> "Hi there! " + Math.random();

        System.out.println("  add(5, 3)      = " + add.operate(5, 3));
        System.out.println("  subtract(5, 3) = " + subtract.operate(5, 3));
        System.out.println("  multiply(5, 3) = " + multiply.operate(5, 3));
        System.out.println("  hello(\"Java\")  = " + hello.greet("Java"));
        System.out.println("  random()       = " + randomGreeting.get());
        System.out.println();

        // =====================================================================
        // PART 2: BUILT-IN FUNCTIONAL INTERFACES
        // =====================================================================
        System.out.println("━━━ PART 2: Built-in Functional Interfaces ━━━");
        System.out.println();

        // ── Predicate<T>: T → boolean ────────────────────────────────────
        // "Does this thing pass a test?"
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<String> isLong = s -> s.length() > 5;

        System.out.println("  Predicate<Integer> isEven:");
        System.out.println("    isEven.test(4) = " + isEven.test(4));
        System.out.println("    isEven.test(7) = " + isEven.test(7));

        // Predicate composition!
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isPositiveAndEven = isPositive.and(isEven);
        Predicate<Integer> isNegativeOrEven = isPositive.negate().or(isEven);
        System.out.println("    isPositive.and(isEven).test(4) = " + isPositiveAndEven.test(4));
        System.out.println("    isPositive.and(isEven).test(-4) = " + isPositiveAndEven.test(-4));
        System.out.println();

        // ── Function<T,R>: T → R ─────────────────────────────────────────
        // "Transform this thing into something else"
        Function<String, Integer> stringLength = String::length;  // Method reference!
        Function<Integer, String> intToEmoji = n -> n > 5 ? "😊" : "😐";

        System.out.println("  Function<String, Integer> stringLength:");
        System.out.println("    stringLength(\"Hello\") = " + stringLength.apply("Hello"));
        System.out.println("    intToEmoji(8) = " + intToEmoji.apply(8));

        // Function composition: andThen, compose
        Function<String, String> toUpper = String::toUpperCase;
        Function<String, String> addExcl = s -> s + "!!!";
        Function<String, String> shout = toUpper.andThen(addExcl);
        System.out.println("    shout(\"hello\") = " + shout.apply("hello"));
        System.out.println();

        // ── Consumer<T>: T → void ─────────────────────────────────────────
        // "Do something with this thing (no return)"
        Consumer<String> printer = s -> System.out.println("    📢 " + s);
        Consumer<String> logger = s -> System.out.println("    📝 LOG: " + s);

        System.out.println("  Consumer<String>:");
        printer.accept("Hello from Consumer!");
        printer.andThen(logger).accept("Both print and log!");
        System.out.println();

        // ── Supplier<T>: () → T ──────────────────────────────────────────
        // "Give me something (no input)"
        Supplier<Double> randomNum = Math::random;
        Supplier<List<String>> emptyList = ArrayList::new;  // Method reference to constructor!

        System.out.println("  Supplier<Double>:");
        System.out.println("    randomNum.get() = " + randomNum.get());
        System.out.println("    emptyList.get() = " + emptyList.get());
        System.out.println();

        // ── UnaryOperator<T>: T → T ──────────────────────────────────────
        UnaryOperator<String> exclaim = s -> s + "!";
        System.out.println("  UnaryOperator: exclaim(\"Hi\") = " + exclaim.apply("Hi"));

        // ── BinaryOperator<T>: (T, T) → T ───────────────────────────────
        BinaryOperator<Integer> max = Integer::max;
        System.out.println("  BinaryOperator: max(10, 20) = " + max.apply(10, 20));
        System.out.println();

        // =====================================================================
        // PART 3: METHOD REFERENCES
        // =====================================================================
        System.out.println("━━━ PART 3: Method References ━━━");
        System.out.println();

        // Method references are even shorter lambdas!
        // When a lambda just calls an existing method, use :: instead.

        List<String> names = List.of("Charlie", "Alice", "Bob", "Diana");
        List<String> mutableNames = new ArrayList<>(names);

        // Static method reference: ClassName::method
        System.out.println("  Integer::parseInt:  " + Function.identity().getClass().getSimpleName()); // just demo
        Function<String, Integer> parser = Integer::parseInt;
        System.out.println("    parseInt(\"123\") = " + parser.apply("123"));

        // Instance method reference on parameter: ClassName::instanceMethod
        mutableNames.sort(String::compareToIgnoreCase);
        System.out.println("  Sorted (String::compareToIgnoreCase): " + mutableNames);

        // Instance method reference on specific object: object::method
        System.out.print("  ");
        mutableNames.forEach(System.out::print);  // Same as: s -> System.out.print(s)
        System.out.println();

        // Constructor reference: ClassName::new
        Function<String, StringBuilder> sbCreator = StringBuilder::new;
        System.out.println("  StringBuilder::new(\"Hi\") = " + sbCreator.apply("Hi"));
        System.out.println();

        System.out.println("  ┌───────────────────────────────┬────────────────────────┐");
        System.out.println("  │ Method Reference              │ Equivalent Lambda      │");
        System.out.println("  ├───────────────────────────────┼────────────────────────┤");
        System.out.println("  │ String::length                │ s -> s.length()        │");
        System.out.println("  │ Integer::parseInt             │ s -> Integer.parseInt(s)│");
        System.out.println("  │ System.out::println           │ s -> System.out.println(s)│");
        System.out.println("  │ ArrayList::new                │ () -> new ArrayList<>()│");
        System.out.println("  └───────────────────────────────┴────────────────────────┘");
        System.out.println();

        // =====================================================================
        // PART 4: PRACTICAL EXAMPLES
        // =====================================================================
        System.out.println("━━━ PART 4: Practical Examples ━━━");
        System.out.println();

        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        // removeIf with Predicate
        numbers.removeIf(n -> n % 2 == 0);
        System.out.println("  After removeIf(even): " + numbers);

        // replaceAll with UnaryOperator
        numbers.replaceAll(n -> n * n);
        System.out.println("  After replaceAll(n²): " + numbers);

        // sort with Comparator (lambda)
        List<String> words = new ArrayList<>(List.of("cherry", "apple", "banana", "date"));
        words.sort((a, b) -> a.length() - b.length());
        System.out.println("  Sorted by length: " + words);

        // Custom filter method using Predicate
        List<Integer> allNums = List.of(1, -2, 3, -4, 5, -6, 7, 8);
        System.out.println("  Positive only: " + filter(allNums, n -> n > 0));
        System.out.println("  Even only:     " + filter(allNums, n -> n % 2 == 0));
        System.out.println("  Greater than 3: " + filter(allNums, n -> n > 3));
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Modifying external variables in lambdas:");
        System.out.println("   Variables used in lambdas must be effectively final!");
        System.out.println("   int count = 0;");
        System.out.println("   list.forEach(s -> count++);  ← ❌ Compile error!");
        System.out.println();
        System.out.println("2. Overusing lambdas — keep them short!");
        System.out.println("   If a lambda is more than 2-3 lines, extract to a method.");
        System.out.println();
        System.out.println("3. Confusing Predicate, Function, Consumer:");
        System.out.println("   Predicate → returns boolean");
        System.out.println("   Function  → returns something");
        System.out.println("   Consumer  → returns nothing (void)");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: C_StreamsAPI.java");
    }

    // Helper: generic filter using Predicate
    static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) result.add(item);
        }
        return result;
    }
}
