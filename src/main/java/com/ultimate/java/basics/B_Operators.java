package com.ultimate.java.basics;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                         OPERATORS IN JAVA                                  ║
 * ║                    The Verbs of the Programming Language                    ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │                                                                           │
 * │  • A_VariablesAndDataTypes.java (you MUST understand variables first)     │
 * │  • Basic math: addition, subtraction, multiplication, division            │
 * │  • The idea of "true" and "false" (is 5 greater than 3? → true)          │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT ARE OPERATORS?                                                       │
 * │                                                                           │
 * │  If variables are the NOUNS (things), operators are the VERBS (actions).  │
 * │                                                                           │
 * │  "John   KICKED   the ball"                                               │
 * │    ↑       ↑        ↑                                                     │
 * │  noun    VERB      noun                                                   │
 * │                                                                           │
 * │  "  5     +        3  "                                                   │
 * │    ↑      ↑        ↑                                                      │
 * │ operand OPERATOR operand                                                  │
 * │                                                                           │
 * │  Operators PERFORM ACTIONS on data (operands).                            │
 * │  Without operators, your variables would just sit there doing nothing!    │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY DO WE NEED OPERATORS?                                                │
 * │                                                                           │
 * │  Without operators, programming would be like having ingredients but      │
 * │  no cooking tools. You have flour, sugar, eggs (variables), but you       │
 * │  can't MIX, BAKE, or CUT anything without operators!                     │
 * │                                                                           │
 * │  Operators let you:                                                       │
 * │    • Calculate things (math)                                              │
 * │    • Compare things (is A bigger than B?)                                 │
 * │    • Make decisions (if this AND that, then do something)                 │
 * │    • Modify data (increase a score, toggle a switch)                      │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class B_Operators {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 2: Operators                               ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: ARITHMETIC OPERATORS (Math stuff)
        // =====================================================================
        // These are your basic math tools. You already know most of them!

        System.out.println("━━━ PART 1: Arithmetic Operators ━━━");
        System.out.println();

        int a = 17;
        int b = 5;

        System.out.println("Given: a = " + a + ", b = " + b);
        System.out.println();

        // Addition (+)
        System.out.println("  a + b  = " + (a + b) + "   (Addition: straightforward math)");

        // Subtraction (-)
        System.out.println("  a - b  = " + (a - b) + "  (Subtraction)");

        // Multiplication (*)
        System.out.println("  a * b  = " + (a * b) + "  (Multiplication: use * not ×)");

        // Division (/)
        System.out.println("  a / b  = " + (a / b) + "   (Integer division: 17÷5 = 3, remainder dropped!)");

        // Modulus (%) — THE ONE YOU MIGHT NOT KNOW!
        // This gives you the REMAINDER after division.
        // Think: "If I divide 17 cookies among 5 friends equally, how many are LEFT OVER?"
        // 17 ÷ 5 = 3 remainder 2. So 17 % 5 = 2
        System.out.println("  a % b  = " + (a % b) + "   (Modulus: the REMAINDER. 17÷5 = 3 remainder 2)");
        System.out.println();

        // WHY is modulus (%) useful? SO many reasons!
        System.out.println("  💡 Why modulus (%) is SUPER useful:");
        System.out.println("     • Check if a number is even or odd: n % 2 == 0 → even!");
        System.out.println("     • Wrap around: clock hours → 14 % 12 = " + (14 % 12) + " (2 PM)");
        System.out.println("     • Cycle through options: index % arrayLength");
        System.out.println();

        // =====================================================================
        // PART 2: UNARY OPERATORS (Work on ONE operand)
        // =====================================================================
        System.out.println("━━━ PART 2: Unary Operators ━━━");
        System.out.println();

        // "Unary" means "one" — these operators work on a SINGLE variable.
        // Think of them as actions you do to ONE thing:
        //   "Negate this number" or "Increase this by one"

        int score = 10;
        System.out.println("Starting score = " + score);

        // ++ (Increment: add 1)
        // Two flavors:
        //   score++  → POST-increment: use the value FIRST, then add 1
        //   ++score  → PRE-increment:  add 1 FIRST, then use the value

        score++;  // score is now 11
        System.out.println("After score++ → score = " + score + "  (increased by 1)");

        score--;  // score is now 10 again
        System.out.println("After score-- → score = " + score + "  (decreased by 1)");
        System.out.println();

        // THE TRICKY PART: Pre vs Post increment
        // This trips up EVERYONE, even experienced developers!
        System.out.println("  🧠 Pre vs Post increment (THE TRICKY PART):");
        int x = 5;
        System.out.println("  x = " + x);
        System.out.println("  x++ gives: " + (x++) + "  ← uses 5 FIRST, then increments");
        System.out.println("  x is now:  " + x + "  ← now it's 6");

        x = 5; // reset
        System.out.println("  ++x gives: " + (++x) + "  ← increments FIRST, then uses 6");
        System.out.println("  x is now:  " + x + "  ← it's 6");
        System.out.println();
        System.out.println("  📝 Rule of thumb: When in doubt, put ++ on its OWN line.");
        System.out.println("     score++;  // Simple. Clear. No confusion.");
        System.out.println();

        // Unary minus (negate)
        int positive = 42;
        int negative = -positive;
        System.out.println("  Unary minus: -(" + positive + ") = " + negative);

        // Logical NOT (!)
        boolean isHappy = true;
        System.out.println("  Logical NOT: !true = " + (!isHappy) + "  (flips true↔false)");
        System.out.println();

        // =====================================================================
        // PART 3: ASSIGNMENT OPERATORS (Shortcuts!)
        // =====================================================================
        System.out.println("━━━ PART 3: Assignment Operators ━━━");
        System.out.println();

        // = is the basic assignment. But Java has SHORTCUTS for common patterns:

        int total = 100;
        System.out.println("Starting total = " + total);

        total += 50;  // Same as: total = total + 50;
        System.out.println("total += 50  → " + total + "  (same as total = total + 50)");

        total -= 30;  // Same as: total = total - 30;
        System.out.println("total -= 30  → " + total + "  (same as total = total - 30)");

        total *= 2;   // Same as: total = total * 2;
        System.out.println("total *= 2   → " + total + "  (same as total = total * 2)");

        total /= 4;   // Same as: total = total / 4;
        System.out.println("total /= 4   → " + total + "  (same as total = total / 4)");

        total %= 50;  // Same as: total = total % 50;
        System.out.println("total %= 50  → " + total + "  (same as total = total % 50)");
        System.out.println();

        // =====================================================================
        // PART 4: COMPARISON (RELATIONAL) OPERATORS
        // =====================================================================
        System.out.println("━━━ PART 4: Comparison Operators ━━━");
        System.out.println();

        // These COMPARE two values and give you a boolean (true/false) answer.
        // Think: A judge comparing two contestants.

        int alice = 85;
        int bob = 90;
        System.out.println("Alice's score = " + alice + ", Bob's score = " + bob);
        System.out.println();

        System.out.println("  alice == bob  → " + (alice == bob) + "   (Equal to? Use == not =)");
        System.out.println("  alice != bob  → " + (alice != bob) + "    (Not equal to?)");
        System.out.println("  alice > bob   → " + (alice > bob) + "  (Greater than?)");
        System.out.println("  alice < bob   → " + (alice < bob) + "   (Less than?)");
        System.out.println("  alice >= bob  → " + (alice >= bob) + "  (Greater than or equal?)");
        System.out.println("  alice <= bob  → " + (alice <= bob) + "   (Less than or equal?)");
        System.out.println();
        System.out.println("  ⚠️  BIGGEST BEGINNER MISTAKE:");
        System.out.println("     =  means ASSIGN (put value in variable)");
        System.out.println("     == means COMPARE (check if equal)");
        System.out.println("     Writing 'if (x = 5)' instead of 'if (x == 5)' is a CLASSIC bug!");
        System.out.println();

        // =====================================================================
        // PART 5: LOGICAL OPERATORS (Combining conditions)
        // =====================================================================
        System.out.println("━━━ PART 5: Logical Operators ━━━");
        System.out.println();

        // Logical operators combine multiple true/false conditions.
        // Think: Making decisions in real life.
        //
        // "I'll go to the beach IF it's sunny AND I have free time"
        //                          ↑           ↑
        //                      condition1  AND condition2
        //
        // BOTH must be true for me to go. That's the AND (&&) operator!

        boolean isSunny = true;
        boolean haveFreeTime = false;

        System.out.println("isSunny = " + isSunny + ", haveFreeTime = " + haveFreeTime);
        System.out.println();

        // AND (&&) — BOTH must be true
        System.out.println("  && (AND): BOTH must be true");
        System.out.println("  isSunny && haveFreeTime = " + (isSunny && haveFreeTime) + "  (sunny but busy → no beach)");
        System.out.println();

        // OR (||) — AT LEAST ONE must be true
        System.out.println("  || (OR): At least ONE must be true");
        System.out.println("  isSunny || haveFreeTime = " + (isSunny || haveFreeTime) + "  (sunny is enough → yes!)");
        System.out.println();

        // NOT (!) — Flips the result
        System.out.println("  ! (NOT): Flips true ↔ false");
        System.out.println("  !isSunny = " + (!isSunny));
        System.out.println();

        // SHORT-CIRCUIT EVALUATION (Important optimization!)
        // && → If the FIRST condition is false, Java doesn't even CHECK the second one.
        //       Why? Because false AND anything = false. No point checking!
        // || → If the FIRST condition is true, Java doesn't check the second one.
        //       Why? Because true OR anything = true.
        System.out.println("  💡 SHORT-CIRCUIT: Java is lazy (in a smart way)!");
        System.out.println("     false && (anything) → Java skips 'anything', result is false");
        System.out.println("     true  || (anything) → Java skips 'anything', result is true");
        System.out.println("     This matters when 'anything' has side effects (like method calls)!");
        System.out.println();

        // Truth table for reference:
        System.out.println("  📊 Truth Tables:");
        System.out.println("  ┌───────┬───────┬─────────┬─────────┐");
        System.out.println("  │   A   │   B   │ A && B  │ A || B  │");
        System.out.println("  ├───────┼───────┼─────────┼─────────┤");
        System.out.println("  │ true  │ true  │  true   │  true   │");
        System.out.println("  │ true  │ false │  false  │  true   │");
        System.out.println("  │ false │ true  │  false  │  true   │");
        System.out.println("  │ false │ false │  false  │  false  │");
        System.out.println("  └───────┴───────┴─────────┴─────────┘");
        System.out.println();

        // =====================================================================
        // PART 6: TERNARY OPERATOR (The one-liner if-else!)
        // =====================================================================
        System.out.println("━━━ PART 6: Ternary Operator ━━━");
        System.out.println();

        // Syntax: condition ? valueIfTrue : valueIfFalse
        //
        // Think of it as a quick question:
        // "Are you 18 or older?  YES → You can vote.  NO → You can't."

        int age = 20;
        String canVote = (age >= 18) ? "Yes, you can vote!" : "No, too young.";
        System.out.println("  age = " + age);
        System.out.println("  (age >= 18) ? \"Yes\" : \"No\"  →  " + canVote);
        System.out.println();
        System.out.println("  Structure: condition ? ifTrue : ifFalse");
        System.out.println("  ⚠️  Don't nest ternaries! It becomes unreadable. Use if-else instead.");
        System.out.println();

        // =====================================================================
        // PART 7: BITWISE OPERATORS (Advanced — for the curious!)
        // =====================================================================
        System.out.println("━━━ PART 7: Bitwise Operators (Advanced) ━━━");
        System.out.println();

        // These work on the individual BITS (0s and 1s) of numbers.
        // You probably won't use these often, but knowing they exist is important!
        //
        // Remember: computers store everything as binary (base 2):
        //   5 in binary = 0101
        //   3 in binary = 0011

        int p = 5;  // Binary: 0101
        int q = 3;  // Binary: 0011

        System.out.println("  p = 5 (binary: 0101)");
        System.out.println("  q = 3 (binary: 0011)");
        System.out.println();

        // AND (&) — Both bits must be 1
        System.out.println("  p & q  = " + (p & q) + "  (AND: 0101 & 0011 = 0001 = 1)");

        // OR (|) — At least one bit must be 1
        System.out.println("  p | q  = " + (p | q) + "  (OR:  0101 | 0011 = 0111 = 7)");

        // XOR (^) — Bits must be DIFFERENT
        System.out.println("  p ^ q  = " + (p ^ q) + "  (XOR: 0101 ^ 0011 = 0110 = 6)");

        // NOT (~) — Flip all bits
        System.out.println("  ~p     = " + (~p) + " (NOT: flips ALL bits, including sign bit)");

        // Left shift (<<) — Shift bits left (multiply by 2 for each shift)
        System.out.println("  p << 1 = " + (p << 1) + " (Left shift: 0101 → 1010 = 10, same as p*2)");

        // Right shift (>>) — Shift bits right (divide by 2 for each shift)
        System.out.println("  p >> 1 = " + (p >> 1) + "  (Right shift: 0101 → 0010 = 2, same as p/2)");
        System.out.println();
        System.out.println("  💡 When are bitwise operators useful?");
        System.out.println("     • Checking if a number is even: (n & 1) == 0");
        System.out.println("     • Permissions/flags systems (Linux file permissions!)");
        System.out.println("     • Performance-critical code (games, graphics)");
        System.out.println("     • Coding interviews (they love bitwise questions!)");
        System.out.println();

        // =====================================================================
        // PART 8: OPERATOR PRECEDENCE (Who goes first?)
        // =====================================================================
        System.out.println("━━━ PART 8: Operator Precedence ━━━");
        System.out.println();

        // Just like in math: 2 + 3 × 4 = 14 (not 20!)
        // Multiplication happens BEFORE addition.
        //
        // Java follows similar rules. From HIGHEST to LOWEST priority:
        //   1. () parentheses (always first!)
        //   2. ++ -- ! (unary)
        //   3. * / % (multiplication, division, modulus)
        //   4. + - (addition, subtraction)
        //   5. < > <= >= (comparison)
        //   6. == != (equality)
        //   7. && (logical AND)
        //   8. || (logical OR)
        //   9. = += -= etc. (assignment — always LAST)

        int result1 = 2 + 3 * 4;
        int result2 = (2 + 3) * 4;
        System.out.println("  2 + 3 * 4   = " + result1 + "  (* goes first, then +)");
        System.out.println("  (2 + 3) * 4 = " + result2 + "  (parentheses override!)");
        System.out.println();
        System.out.println("  📝 PRO TIP: When in doubt, USE PARENTHESES!");
        System.out.println("  They make your code clearer AND avoid precedence bugs.");
        System.out.println("  (a + b) * c  is clearer than  a + b * c");
        System.out.println();

        // =====================================================================
        // PART 9: instanceof OPERATOR (Type checking)
        // =====================================================================
        System.out.println("━━━ PART 9: instanceof Operator ━━━");
        System.out.println();

        // instanceof checks if an object IS A certain type.
        // Think: "Is this animal a Dog?" → true/false

        String text = "Hello";
        Object obj = text;  // String IS-A Object (we'll learn this in OOP!)

        System.out.println("  \"Hello\" instanceof String → " + (obj instanceof String));
        System.out.println("  \"Hello\" instanceof Object → " + (obj instanceof Object));
        System.out.println("  → We'll explore this deeply in OOP (E_Polymorphism.java)!");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. = vs == confusion:");
        System.out.println("   x = 5   → ASSIGNS 5 to x");
        System.out.println("   x == 5  → CHECKS if x equals 5");
        System.out.println();
        System.out.println("2. Integer division:");
        System.out.println("   5 / 2 = " + (5/2) + "  (not 2.5! Use 5.0/2 for decimals)");
        System.out.println();
        System.out.println("3. String comparison with ==:");
        System.out.println("   NEVER use == to compare Strings! Use .equals()");
        System.out.println("   \"hello\" == \"hello\" might work, but it's checking REFERENCES not VALUES");
        System.out.println("   Always: str1.equals(str2)");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("1. Calculate: What is 100 divided by 7? What's the remainder?");
        System.out.println("2. A shirt costs $49.99 and tax is 8%. Calculate the final price.");
        System.out.println("3. You have a number. Using ONE operator, check if it's even.");
        System.out.println("4. Write a ternary: if temperature > 30, print \"Hot\", else \"Cool\".");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: C_ControlFlow.java");
    }
}
