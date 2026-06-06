package com.ultimate.java.core;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                     EXCEPTION HANDLING IN JAVA                             ║
 * ║           Gracefully Dealing With Things That Go Wrong                      ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │  • Basics module (variables, methods, control flow)                       │
 * │  • OOP module (classes, inheritance, polymorphism)                        │
 * │  • Understanding that programs can CRASH (NullPointer, ArrayIndexOut...)  │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT ARE EXCEPTIONS?                                                      │
 * │                                                                           │
 * │  An exception is an UNEXPECTED EVENT that disrupts normal program flow.   │
 * │                                                                           │
 * │  Think: You're cooking dinner (program running). Suddenly:                │
 * │    • The gas runs out (resource unavailable)                              │
 * │    • You burn your hand (unexpected error)                               │
 * │    • The recipe says "add 5 eggs" but you only have 3 (invalid input)    │
 * │                                                                           │
 * │  WITHOUT exception handling: The kitchen catches fire. 🔥 Program CRASHES│
 * │  WITH exception handling: You calmly handle the situation. Program LIVES  │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ THE EXCEPTION HIERARCHY:                                                  │
 * │                                                                           │
 * │  Throwable (root of all exceptions)                                       │
 * │    ├── Error (serious, DON'T catch these!)                               │
 * │    │     ├── OutOfMemoryError                                             │
 * │    │     ├── StackOverflowError                                           │
 * │    │     └── (JVM-level problems — you can't fix these)                  │
 * │    │                                                                      │
 * │    └── Exception (you SHOULD handle these)                               │
 * │          ├── RuntimeException (UNCHECKED — compiler won't force you)     │
 * │          │     ├── NullPointerException                                   │
 * │          │     ├── ArrayIndexOutOfBoundsException                         │
 * │          │     ├── ArithmeticException (divide by zero)                   │
 * │          │     ├── ClassCastException                                     │
 * │          │     └── IllegalArgumentException                               │
 * │          │                                                                │
 * │          └── (Other Exceptions = CHECKED — compiler FORCES handling)     │
 * │                ├── IOException                                            │
 * │                ├── FileNotFoundException                                  │
 * │                ├── SQLException                                           │
 * │                └── ClassNotFoundException                                 │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class A_ExceptionHandling {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 15: Exception Handling                     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: TRY-CATCH (The Safety Net)
        // =====================================================================
        System.out.println("━━━ PART 1: try-catch ━━━");
        System.out.println();

        // try { risky code } catch (ExceptionType e) { handle it }
        //
        // Think: try = "TRY to do this dangerous thing..."
        //        catch = "If it goes wrong, CATCH the problem and handle it."

        // Example 1: Division by zero
        try {
            int result = 10 / 0;  // 💥 ArithmeticException!
            System.out.println("This never prints: " + result);
        } catch (ArithmeticException e) {
            System.out.println("  Caught ArithmeticException: " + e.getMessage());
            System.out.println("  Program continues running! No crash. ✅");
        }
        System.out.println();

        // Example 2: Array out of bounds
        try {
            int[] numbers = {1, 2, 3};
            System.out.println("  Accessing index 10...");
            int val = numbers[10];  // 💥 ArrayIndexOutOfBoundsException!
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("  Caught: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        System.out.println();

        // Example 3: NullPointerException
        try {
            String text = null;
            int length = text.length();  // 💥 NullPointerException!
        } catch (NullPointerException e) {
            System.out.println("  Caught NullPointerException! Don't call methods on null.");
        }
        System.out.println();

        // =====================================================================
        // PART 2: MULTIPLE CATCH BLOCKS
        // =====================================================================
        System.out.println("━━━ PART 2: Multiple catch Blocks ━━━");
        System.out.println();

        // Different exceptions can be caught separately
        try {
            String[] names = {"Alice", "Bob"};
            // Potential exceptions: ArrayIndex, NumberFormat, NullPointer
            String input = "abc";
            int index = Integer.parseInt(input);  // 💥 NumberFormatException!
            System.out.println(names[index]);
        } catch (NumberFormatException e) {
            System.out.println("  Caught NumberFormatException: '" + e.getMessage() + "' is not a number!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("  Caught ArrayIndexOutOfBounds: " + e.getMessage());
        } catch (Exception e) {
            // Catch-all for any other exception (always put LAST — most general)
            System.out.println("  Caught general Exception: " + e.getMessage());
        }
        System.out.println();

        // Multi-catch (Java 7+) — one block for multiple exceptions
        try {
            int[] arr = {1, 2};
            arr[5] = 10;
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            // Handles EITHER exception with the same code
            System.out.println("  Multi-catch: " + e.getClass().getSimpleName());
        }
        System.out.println();

        // =====================================================================
        // PART 3: FINALLY BLOCK (Always runs!)
        // =====================================================================
        System.out.println("━━━ PART 3: finally Block ━━━");
        System.out.println();

        // 'finally' ALWAYS runs — whether exception occurred or not.
        // Perfect for CLEANUP: closing files, releasing connections, etc.

        System.out.println("  Scenario 1: No exception");
        try {
            System.out.println("    try: Everything is fine!");
        } catch (Exception e) {
            System.out.println("    catch: Won't run (no exception)");
        } finally {
            System.out.println("    finally: I ALWAYS run! ✅");
        }
        System.out.println();

        System.out.println("  Scenario 2: Exception occurs");
        try {
            System.out.println("    try: About to crash...");
            int x = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("    catch: Caught the exception!");
        } finally {
            System.out.println("    finally: I STILL run! ✅");
        }
        System.out.println();

        // =====================================================================
        // PART 4: TRY-WITH-RESOURCES (Java 7+)
        // =====================================================================
        System.out.println("━━━ PART 4: try-with-resources ━━━");
        System.out.println();

        // Resources (files, connections) should be CLOSED after use.
        // try-with-resources closes them AUTOMATICALLY!
        //
        // Old way:
        //   FileReader fr = null;
        //   try { fr = new FileReader("file.txt"); ... }
        //   finally { if (fr != null) fr.close(); }  // Tedious!
        //
        // New way:
        //   try (FileReader fr = new FileReader("file.txt")) { ... }
        //   // Automatically closed! Even if exception occurs!

        System.out.println("  try-with-resources automatically closes resources:");
        System.out.println("    try (Scanner sc = new Scanner(System.in)) {");
        System.out.println("        // use scanner...");
        System.out.println("    }  // scanner.close() called automatically!");
        System.out.println("  Works with any class that implements AutoCloseable.");
        System.out.println();

        // =====================================================================
        // PART 5: THROW AND THROWS
        // =====================================================================
        System.out.println("━━━ PART 5: throw and throws ━━━");
        System.out.println();

        // throw → CREATES and throws an exception manually
        // throws → DECLARES that a method might throw an exception

        // Using throw:
        try {
            validateAge(15);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught: " + e.getMessage());
        }

        try {
            validateAge(25);
            System.out.println("  Age 25 is valid! ✅");
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught: " + e.getMessage());
        }
        System.out.println();

        // =====================================================================
        // PART 6: CUSTOM EXCEPTIONS
        // =====================================================================
        System.out.println("━━━ PART 6: Custom Exceptions ━━━");
        System.out.println();

        // Creating your OWN exception class for domain-specific errors!
        try {
            processWithdrawal(100, 50);    // OK
            processWithdrawal(100, 200);   // Should fail!
        } catch (InsufficientFundsException e) {
            System.out.println("  Custom exception caught!");
            System.out.println("  Message: " + e.getMessage());
            System.out.println("  Deficit: $" + e.getDeficit());
        }
        System.out.println();

        // =====================================================================
        // PART 7: CHECKED VS UNCHECKED
        // =====================================================================
        System.out.println("━━━ PART 7: Checked vs Unchecked Exceptions ━━━");
        System.out.println();
        System.out.println("  ┌──────────────────────┬──────────────────────────────┐");
        System.out.println("  │  CHECKED              │  UNCHECKED                   │");
        System.out.println("  ├──────────────────────┼──────────────────────────────┤");
        System.out.println("  │ Compiler FORCES       │ Compiler doesn't force       │");
        System.out.println("  │ you to handle         │ you to handle                │");
        System.out.println("  │ Extends Exception     │ Extends RuntimeException     │");
        System.out.println("  │ External problems     │ Programming bugs             │");
        System.out.println("  │ (file not found, etc) │ (null, array bounds, etc)    │");
        System.out.println("  │ Must: try-catch OR    │ Optional: handle if you      │");
        System.out.println("  │       throws          │ want to                      │");
        System.out.println("  └──────────────────────┴──────────────────────────────┘");
        System.out.println();

        // =====================================================================
        // PART 8: BEST PRACTICES
        // =====================================================================
        System.out.println("━━━ PART 8: Best Practices ━━━");
        System.out.println();
        System.out.println("  ✅ DO:");
        System.out.println("     • Catch specific exceptions (not just 'Exception')");
        System.out.println("     • Use try-with-resources for closeable resources");
        System.out.println("     • Include meaningful messages in custom exceptions");
        System.out.println("     • Log exceptions (don't silently swallow them!)");
        System.out.println("     • Throw early, catch late");
        System.out.println();
        System.out.println("  ❌ DON'T:");
        System.out.println("     • Catch Exception/Throwable (too broad!)");
        System.out.println("     • Use exceptions for control flow");
        System.out.println("     • Catch and ignore: catch (Exception e) { }  ← BAD!");
        System.out.println("     • Throw generic Exception — be specific!");
        System.out.println("     • Catch Error (OutOfMemoryError, etc) — can't recover!");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Empty catch block (swallowing exceptions):");
        System.out.println("   catch (Exception e) { }  ← SILENT FAILURE! You'll never know!");
        System.out.println();
        System.out.println("2. Catching too broadly:");
        System.out.println("   catch (Exception e) { }  ← Catches EVERYTHING, even bugs!");
        System.out.println("   Better: catch specific exceptions first.");
        System.out.println();
        System.out.println("3. Wrong order of catch blocks:");
        System.out.println("   catch (Exception e) { }  ← Catches all!");
        System.out.println("   catch (IOException e) { } ← NEVER reached! Compile error.");
        System.out.println("   Put SPECIFIC catches FIRST, general LAST.");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("1. Create a safe division method that catches ArithmeticException");
        System.out.println("   and returns a default value.");
        System.out.println("2. Create a custom 'InvalidEmailException' with a field for the email.");
        System.out.println("3. Write a method that reads a number from a string and handles");
        System.out.println("   NumberFormatException gracefully.");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: B_WrapperClasses.java");
    }

    // ── Helper: validate age ──────────────────────────────────────────────
    static void validateAge(int age) {
        if (age < 18) {
            // 'throw' creates and throws a new exception
            throw new IllegalArgumentException("Age must be 18+. Got: " + age);
        }
    }

    // ── Custom exception class ────────────────────────────────────────────
    static class InsufficientFundsException extends Exception {
        private double deficit;

        InsufficientFundsException(String message, double deficit) {
            super(message);
            this.deficit = deficit;
        }

        public double getDeficit() { return deficit; }
    }

    // ── Method that throws custom exception ───────────────────────────────
    static void processWithdrawal(double balance, double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(
                "Cannot withdraw $" + amount + " from balance of $" + balance,
                amount - balance
            );
        }
        System.out.println("  Withdrew $" + amount + " from $" + balance + " → $" + (balance - amount));
    }
}
