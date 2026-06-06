package com.ultimate.java.basics;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                          METHODS IN JAVA                                   ║
 * ║              Breaking Your Code Into Reusable, Named Blocks                ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │                                                                           │
 * │  • A_VariablesAndDataTypes.java (data types, int, String, etc.)          │
 * │  • B_Operators.java (arithmetic operations)                               │
 * │  • C_ControlFlow.java (if-else, because methods often contain logic)     │
 * │  • D_Loops.java (for/while, methods can contain loops)                   │
 * │  • Understanding that main(String[] args) is a method you've been using! │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT ARE METHODS?                                                         │
 * │                                                                           │
 * │  A method is a NAMED BLOCK OF CODE that performs a specific task.         │
 * │  You can CALL (use) it whenever you need that task done.                  │
 * │                                                                           │
 * │  Think of methods like RECIPES in a cookbook:                              │
 * │                                                                           │
 * │    Recipe: "How to Make Pancakes" (this is the method NAME)              │
 * │    Ingredients needed: flour, eggs, milk (these are PARAMETERS)           │
 * │    Steps: mix, pour, flip (this is the method BODY)                      │
 * │    Result: delicious pancakes (this is the RETURN VALUE)                  │
 * │                                                                           │
 * │  You write the recipe ONCE, then use it whenever you want pancakes.       │
 * │  You don't rewrite the recipe every time!                                │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY DO WE NEED METHODS?                                                  │
 * │                                                                           │
 * │  Without methods, imagine a program that needs to calculate tax in        │
 * │  20 different places. You'd copy-paste the tax formula 20 times!         │
 * │                                                                           │
 * │  Problems without methods:                                                │
 * │    ❌ Code duplication (same code in 20 places)                           │
 * │    ❌ Hard to update (change tax rate → update 20 places!)               │
 * │    ❌ Hard to read (main method is 5000 lines long)                      │
 * │    ❌ Hard to debug (bug in one copy? Check all 20!)                     │
 * │                                                                           │
 * │  With methods:                                                            │
 * │    ✅ Write once, use anywhere                                            │
 * │    ✅ Update once → fixed everywhere                                     │
 * │    ✅ Clean, readable, organized code                                     │
 * │    ✅ Easy to test and debug                                              │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ METHOD ANATOMY:                                                           │
 * │                                                                           │
 * │  public static int add(int a, int b) {                                   │
 * │    ↑      ↑     ↑   ↑      ↑                                             │
 * │    │      │     │   │      └── Parameters (inputs)                       │
 * │    │      │     │   └── Method name                                       │
 * │    │      │     └── Return type (what it gives back)                     │
 * │    │      └── Static (belongs to class, not object — more in OOP!)       │
 * │    └── Access modifier (who can use this method)                          │
 * │                                                                           │
 * │      return a + b;   ← The value given back to the caller                │
 * │  }                                                                        │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class E_Methods {

    // =========================================================================
    // METHOD DEFINITIONS (we define them here, OUTSIDE main, but INSIDE class)
    // =========================================================================

    // ─────────────────────────────────────────────────────────────────────────
    // EXAMPLE 1: Simplest method — no parameters, no return value
    // ─────────────────────────────────────────────────────────────────────────
    // 'void' means "this method returns NOTHING"
    // Think: A doorbell — you press it, it rings, it doesn't give you anything back.
    static void sayHello() {
        System.out.println("  👋 Hello from the sayHello() method!");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // EXAMPLE 2: Method WITH parameters (inputs)
    // ─────────────────────────────────────────────────────────────────────────
    // Parameters are like blanks in a form: "Dear _____, thank you for _____."
    // You fill in the blanks when you CALL the method.
    static void greet(String name, String timeOfDay) {
        System.out.println("  🌟 Good " + timeOfDay + ", " + name + "! Welcome to Java.");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // EXAMPLE 3: Method WITH a return value
    // ─────────────────────────────────────────────────────────────────────────
    // Return type is 'int' — this method GIVES BACK an integer.
    // Think: A calculator — you give it numbers, it gives back an answer.
    static int add(int a, int b) {
        return a + b;  // 'return' sends the answer back to whoever called this method
    }

    // ─────────────────────────────────────────────────────────────────────────
    // EXAMPLE 4: Method with logic inside
    // ─────────────────────────────────────────────────────────────────────────
    static String getLetterGrade(int score) {
        if (score >= 90) return "A";
        else if (score >= 80) return "B";
        else if (score >= 70) return "C";
        else if (score >= 60) return "D";
        else return "F";
    }

    // ─────────────────────────────────────────────────────────────────────────
    // EXAMPLE 5: Method overloading (same name, different parameters)
    // ─────────────────────────────────────────────────────────────────────────
    // This is METHOD OVERLOADING: multiple methods with the SAME NAME
    // but DIFFERENT parameter lists.
    //
    // Think: The word "run"
    //   "run()"           → just run (no details)
    //   "run(distance)"   → run a specific distance
    //   "run(distance, speed)" → run a distance at a certain speed
    //
    // Java knows which one to call based on the ARGUMENTS you pass!

    static int multiply(int a, int b) {
        return a * b;
    }

    static double multiply(double a, double b) {
        return a * b;
    }

    static int multiply(int a, int b, int c) {
        return a * b * c;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // EXAMPLE 6: Varargs (Variable number of arguments)
    // ─────────────────────────────────────────────────────────────────────────
    // What if you don't know how MANY arguments there will be?
    // Use varargs: int... numbers (three dots!)
    // This means: "accept ANY number of ints — 0, 1, 5, 100, whatever!"
    // Inside the method, 'numbers' acts like an array.

    static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // EXAMPLE 7: Method that returns boolean (Predicate)
    // ─────────────────────────────────────────────────────────────────────────
    // Methods that return boolean are often named starting with
    // "is", "has", "can", "should" — they answer a YES/NO question.

    static boolean isEven(int number) {
        return number % 2 == 0;
    }

    static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0) return false;
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // EXAMPLE 8: Recursion (A method that calls ITSELF!)
    // ─────────────────────────────────────────────────────────────────────────
    // Think: Russian nesting dolls (Matryoshka) — open one, there's another inside.
    //
    // Factorial: 5! = 5 × 4 × 3 × 2 × 1 = 120
    //   factorial(5) = 5 × factorial(4)
    //     factorial(4) = 4 × factorial(3)
    //       factorial(3) = 3 × factorial(2)
    //         factorial(2) = 2 × factorial(1)
    //           factorial(1) = 1  ← BASE CASE (stop here!)
    //
    // Every recursive method MUST have a BASE CASE or it loops forever!

    static long factorial(int n) {
        if (n <= 1) return 1;       // BASE CASE: stop recursing!
        return n * factorial(n - 1); // RECURSIVE CASE: call myself with a smaller number
    }

    // =========================================================================
    // MAIN METHOD — where we CALL (use) all the methods above
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 5: Methods                                ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: CALLING METHODS
        // =====================================================================
        System.out.println("━━━ PART 1: Calling Methods ━━━");
        System.out.println();

        // Call the simplest method:
        sayHello();  // Just call by name + parentheses!

        // Call with parameters (passing ARGUMENTS):
        greet("Vasanth", "morning");
        greet("Java Student", "evening");
        System.out.println();

        // =====================================================================
        // PART 2: RETURN VALUES (Getting results from methods)
        // =====================================================================
        System.out.println("━━━ PART 2: Return Values ━━━");
        System.out.println();

        // Method returns a value → STORE it in a variable or USE it directly!
        int result = add(15, 27);
        System.out.println("  add(15, 27) = " + result);

        // Or use directly (no variable needed):
        System.out.println("  add(100, 200) = " + add(100, 200));

        // Return value in a condition:
        String grade = getLetterGrade(85);
        System.out.println("  Score 85 → Grade: " + grade);
        System.out.println("  Score 42 → Grade: " + getLetterGrade(42));
        System.out.println();

        // =====================================================================
        // PART 3: METHOD OVERLOADING (Same name, different signatures)
        // =====================================================================
        System.out.println("━━━ PART 3: Method Overloading ━━━");
        System.out.println();

        // Java picks the RIGHT version based on the arguments:
        System.out.println("  multiply(3, 4)       = " + multiply(3, 4));           // calls int version
        System.out.println("  multiply(3.5, 2.0)   = " + multiply(3.5, 2.0));       // calls double version
        System.out.println("  multiply(2, 3, 4)    = " + multiply(2, 3, 4));         // calls 3-param version
        System.out.println();

        System.out.println("  💡 How Java picks the right method:");
        System.out.println("     1. Match by NUMBER of parameters");
        System.out.println("     2. Match by TYPE of parameters");
        System.out.println("     3. Return type alone does NOT distinguish methods!");
        System.out.println();

        // =====================================================================
        // PART 4: VARARGS (Variable Arguments)
        // =====================================================================
        System.out.println("━━━ PART 4: Varargs ━━━");
        System.out.println();

        System.out.println("  sum()              = " + sum());
        System.out.println("  sum(5)             = " + sum(5));
        System.out.println("  sum(1, 2, 3)       = " + sum(1, 2, 3));
        System.out.println("  sum(1, 2, 3, 4, 5) = " + sum(1, 2, 3, 4, 5));
        System.out.println();
        System.out.println("  💡 Varargs rules:");
        System.out.println("     • Only ONE varargs per method");
        System.out.println("     • Must be the LAST parameter");
        System.out.println("     • Inside the method, it's treated as an array");
        System.out.println();

        // =====================================================================
        // PART 5: BOOLEAN-RETURNING METHODS (Predicates)
        // =====================================================================
        System.out.println("━━━ PART 5: Boolean Methods (Predicates) ━━━");
        System.out.println();

        System.out.println("  isEven(4) = " + isEven(4) + "    isEven(7) = " + isEven(7));
        System.out.println("  isPrime(17) = " + isPrime(17) + "  isPrime(15) = " + isPrime(15));
        System.out.println();

        // Using boolean methods in conditions — super clean!
        int num = 42;
        if (isEven(num)) {
            System.out.println("  " + num + " is even! ✅");
        }

        // Find primes from 2-30
        System.out.print("  Primes 2-30: ");
        for (int i = 2; i <= 30; i++) {
            if (isPrime(i)) System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();

        // =====================================================================
        // PART 6: RECURSION
        // =====================================================================
        System.out.println("━━━ PART 6: Recursion ━━━");
        System.out.println();

        System.out.println("  factorial(5)  = " + factorial(5) + "     (5×4×3×2×1)");
        System.out.println("  factorial(10) = " + factorial(10) + " (10×9×8×...×1)");
        System.out.println();

        // Let's visualize the recursion:
        System.out.println("  📐 How factorial(4) unfolds:");
        System.out.println("     factorial(4)");
        System.out.println("       → 4 × factorial(3)");
        System.out.println("           → 3 × factorial(2)");
        System.out.println("               → 2 × factorial(1)");
        System.out.println("                   → 1  (BASE CASE!)");
        System.out.println("               ← 2 × 1 = 2");
        System.out.println("           ← 3 × 2 = 6");
        System.out.println("       ← 4 × 6 = 24");
        System.out.println("     = 24 ✅");
        System.out.println();

        System.out.println("  ⚠️ RECURSION WARNINGS:");
        System.out.println("     • ALWAYS have a base case, or you get StackOverflowError!");
        System.out.println("     • Each call uses memory (call stack). Deep recursion = crash.");
        System.out.println("     • Often, a loop is simpler and more efficient.");
        System.out.println("     • But some problems (trees, fractals) are naturally recursive.");
        System.out.println();

        // =====================================================================
        // PART 7: PASS BY VALUE (How Java passes arguments)
        // =====================================================================
        System.out.println("━━━ PART 7: Pass by Value ━━━");
        System.out.println();

        // This is CRITICAL and trips up many developers!
        //
        // Java is ALWAYS pass by value.
        // For primitives: Java passes a COPY of the value.
        //   → The original variable is UNAFFECTED.
        //
        // Think: I photocopy a document and give you the COPY.
        //        You scribble on your copy — my original is untouched.

        int original = 10;
        System.out.println("  Before calling tryToChange(10): original = " + original);
        tryToChange(original);
        System.out.println("  After calling tryToChange(10):  original = " + original + " ← UNCHANGED!");
        System.out.println();
        System.out.println("  Java passes a COPY of primitives. The original is never modified.");
        System.out.println("  (For reference types, it passes a copy of the REFERENCE — more in OOP!)");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Forgetting 'return' in a non-void method:");
        System.out.println("   int add(int a, int b) { a + b; }  ❌ (no return!)");
        System.out.println("   int add(int a, int b) { return a + b; }  ✅");
        System.out.println();
        System.out.println("2. Unreachable code after return:");
        System.out.println("   return x;");
        System.out.println("   System.out.println(x);  ❌ (code after return is dead code!)");
        System.out.println();
        System.out.println("3. Thinking you can change a primitive by passing it to a method:");
        System.out.println("   Java is pass-by-value! The original is never changed.");
        System.out.println();
        System.out.println("4. Confusing parameters vs arguments:");
        System.out.println("   Parameters: the NAMES in method definition (int a, int b)");
        System.out.println("   Arguments:  the VALUES you pass when calling (add(5, 3))");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("Create these methods:");
        System.out.println("  1. celsiusToFahrenheit(double c) → returns double (F = C × 9/5 + 32)");
        System.out.println("  2. max(int a, int b, int c) → returns the largest of three numbers");
        System.out.println("  3. isPalindrome(String s) → returns true if s reads same forwards/backwards");
        System.out.println("  4. fibonacci(int n) → returns nth Fibonacci number (try with recursion!)");
        System.out.println("  5. power(int base, int exp) → returns base^exp (try with recursion!)");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: F_Arrays.java");
    }

    // Helper method for Part 7 (pass by value demo)
    static void tryToChange(int number) {
        number = 999;  // This changes the LOCAL copy, not the original!
        System.out.println("  Inside tryToChange: number = " + number);
    }
}
