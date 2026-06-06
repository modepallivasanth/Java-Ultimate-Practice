package com.ultimate.java.basics;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                         CONTROL FLOW IN JAVA                               ║
 * ║                    Teaching Your Program to Make Decisions                  ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │                                                                           │
 * │  • A_VariablesAndDataTypes.java (variables, boolean)                      │
 * │  • B_Operators.java (comparison operators: ==, !=, >, <, >=, <=)         │
 * │  • B_Operators.java (logical operators: &&, ||, !)                        │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS CONTROL FLOW?                                                     │
 * │                                                                           │
 * │  Normally, Java runs your code line by line, top to bottom, like          │
 * │  reading a book. Control flow lets you CHANGE this order.                 │
 * │                                                                           │
 * │  Think of it as a CHOOSE YOUR OWN ADVENTURE book:                        │
 * │                                                                           │
 * │    "You enter a dark cave. Do you:                                        │
 * │      A) Go left  → Turn to page 42                                       │
 * │      B) Go right → Turn to page 78                                       │
 * │      C) Go back  → Turn to page 15"                                      │
 * │                                                                           │
 * │  Without control flow, every program would do the EXACT same thing       │
 * │  every time. No decisions, no choices, no intelligence.                   │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY DO WE NEED CONTROL FLOW?                                             │
 * │                                                                           │
 * │  Real-life example:                                                       │
 * │                                                                           │
 * │  When you wake up in the morning:                                         │
 * │    IF it's a weekday → go to work/school                                 │
 * │    ELSE IF it's Saturday → sleep in, then go to gym                      │
 * │    ELSE (it's Sunday) → full rest day                                    │
 * │                                                                           │
 * │  You make DECISIONS every minute. Programs need to do the same.           │
 * │  Control flow = giving your program a BRAIN to make decisions.            │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class C_ControlFlow {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 3: Control Flow                            ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: IF STATEMENT (The simplest decision)
        // =====================================================================
        System.out.println("━━━ PART 1: if Statement ━━━");
        System.out.println();

        // Syntax:
        //   if (condition) {
        //       // code that runs ONLY when condition is true
        //   }
        //
        // Think: "IF it's raining, THEN take an umbrella."
        // If it's NOT raining? You just skip the umbrella part.

        int temperature = 38;

        System.out.println("Temperature: " + temperature + "°C");

        if (temperature > 35) {
            System.out.println("  → 🔥 It's really hot! Stay hydrated!");
        }
        // If temperature was 20, nothing would print. The block is simply SKIPPED.
        System.out.println();

        // =====================================================================
        // PART 2: IF-ELSE (Two choices)
        // =====================================================================
        System.out.println("━━━ PART 2: if-else Statement ━━━");
        System.out.println();

        // Syntax:
        //   if (condition) {
        //       // runs when condition is TRUE
        //   } else {
        //       // runs when condition is FALSE
        //   }
        //
        // Think: "IF you have a ticket, enter the concert. ELSE, go home."
        // ONE of these ALWAYS runs. Never both. Never neither.

        int age = 16;
        System.out.println("Age: " + age);

        if (age >= 18) {
            System.out.println("  → ✅ You are an adult. You can vote!");
        } else {
            System.out.println("  → ❌ You are a minor. " + (18 - age) + " years until you can vote.");
        }
        System.out.println();

        // =====================================================================
        // PART 3: IF-ELSE IF-ELSE (Multiple choices)
        // =====================================================================
        System.out.println("━━━ PART 3: if-else if-else (The Ladder) ━━━");
        System.out.println();

        // When you have MORE than two options.
        // Think: A grading system — not just pass/fail, but A, B, C, D, F.
        //
        // Syntax:
        //   if (condition1) {
        //       // ...
        //   } else if (condition2) {
        //       // ...
        //   } else if (condition3) {
        //       // ...
        //   } else {
        //       // default: none of the above
        //   }
        //
        // ⚠️ IMPORTANT: Java checks from TOP to BOTTOM and STOPS at the
        //    first true condition! Order matters!

        int score = 78;
        String grade;

        System.out.println("Score: " + score);

        if (score >= 90) {
            grade = "A";
            System.out.println("  → Grade: A — Outstanding! 🌟");
        } else if (score >= 80) {
            grade = "B";
            System.out.println("  → Grade: B — Great job! 👍");
        } else if (score >= 70) {
            grade = "C";
            System.out.println("  → Grade: C — Decent, keep it up! 📚");
        } else if (score >= 60) {
            grade = "D";
            System.out.println("  → Grade: D — Needs improvement ⚠️");
        } else {
            grade = "F";
            System.out.println("  → Grade: F — Failed 😞");
        }
        System.out.println();

        // Let's trace through this:
        // score = 78
        //   Is 78 >= 90? NO  → skip
        //   Is 78 >= 80? NO  → skip
        //   Is 78 >= 70? YES → execute this block, print "C", STOP checking
        //
        // Even though 78 >= 60 is ALSO true, we never check it because
        // we already found a match. That's why ORDER MATTERS!

        // =====================================================================
        // PART 4: NESTED IF (Decisions inside decisions)
        // =====================================================================
        System.out.println("━━━ PART 4: Nested if Statements ━━━");
        System.out.println();

        // You can put if statements INSIDE other if statements.
        // Think: "IF you have money, THEN IF the store is open, buy groceries."

        boolean hasLicense = true;
        int driverAge = 17;

        System.out.println("Has license: " + hasLicense + ", Age: " + driverAge);

        if (hasLicense) {
            if (driverAge >= 18) {
                System.out.println("  → ✅ You can drive independently!");
            } else {
                System.out.println("  → ⚠️ You have a license but need an adult in the car.");
            }
        } else {
            System.out.println("  → ❌ You need to get a license first!");
        }
        System.out.println();

        // 💡 TIP: You can often FLATTEN nested ifs using && (AND)
        // The above is equivalent to:
        //   if (hasLicense && driverAge >= 18) { ... }
        //   else if (hasLicense && driverAge < 18) { ... }
        //   else { ... }
        // Choose whichever is more readable!

        System.out.println("  💡 TIP: Nested if can often be flattened with && (AND)");
        System.out.println("     if (hasLicense && age >= 18) → same as nested version");
        System.out.println("     Choose whichever is more readable!");
        System.out.println();

        // =====================================================================
        // PART 5: SWITCH STATEMENT (Multiple exact matches)
        // =====================================================================
        System.out.println("━━━ PART 5: switch Statement ━━━");
        System.out.println();

        // switch is PERFECT when you're comparing ONE variable against
        // MANY possible EXACT values.
        //
        // Think: A vending machine!
        //   Press A1 → Chips
        //   Press A2 → Candy
        //   Press B1 → Soda
        //   Press B2 → Water
        //   Anything else → "Invalid selection"
        //
        // You COULD use if-else-if, but switch is CLEANER for this pattern.

        int dayNumber = 3;
        System.out.println("Day number: " + dayNumber);

        switch (dayNumber) {
            case 1:
                System.out.println("  → Monday — Back to the grind! 😤");
                break;  // IMPORTANT: break stops "falling through" to the next case
            case 2:
                System.out.println("  → Tuesday — At least it's not Monday 😅");
                break;
            case 3:
                System.out.println("  → Wednesday — Hump day! Half way there 🐫");
                break;
            case 4:
                System.out.println("  → Thursday — Almost Friday! 😊");
                break;
            case 5:
                System.out.println("  → Friday — TGIF! 🎉");
                break;
            case 6:
            case 7:
                // Notice: no break after case 6! This is "fall-through".
                // Both 6 and 7 hit this same code. Useful for grouping!
                System.out.println("  → Weekend — Time to relax! 🏖️");
                break;
            default:
                // default is like "else" — runs when nothing matches
                System.out.println("  → Invalid day number! Must be 1-7.");
        }
        System.out.println();

        // ⚠️ THE BREAK TRAP!
        System.out.println("  ⚠️ THE BREAK TRAP:");
        System.out.println("     If you FORGET 'break', Java 'falls through' to the next case!");
        System.out.println("     This is a VERY common bug. Always add break unless you");
        System.out.println("     intentionally want fall-through (like grouping cases 6 & 7).");
        System.out.println();

        // What types can switch work with?
        System.out.println("  📋 switch works with: byte, short, int, char, String, enum");
        System.out.println("  ❌ switch does NOT work with: long, float, double, boolean");
        System.out.println();

        // =====================================================================
        // PART 6: ENHANCED SWITCH (Java 14+) — The Modern Way!
        // =====================================================================
        System.out.println("━━━ PART 6: Enhanced switch (Java 14+) ━━━");
        System.out.println();

        // Java 14 introduced a BETTER switch syntax using arrows (->).
        // No more break statements! No more fall-through bugs!

        String season = "SUMMER";
        System.out.println("Season: " + season);

        // Arrow syntax: case VALUE -> action;
        // No break needed! Each arrow is self-contained.
        switch (season) {
            case "SPRING" -> System.out.println("  → 🌸 Flowers blooming!");
            case "SUMMER" -> System.out.println("  → ☀️ Time for the beach!");
            case "AUTUMN" -> System.out.println("  → 🍂 Leaves are falling!");
            case "WINTER" -> System.out.println("  → ❄️ Bundle up!");
            default -> System.out.println("  → Unknown season");
        }
        System.out.println();

        // Switch EXPRESSION (returns a value!)
        // This is incredibly clean:
        String activity = switch (season) {
            case "SPRING" -> "Gardening";
            case "SUMMER" -> "Swimming";
            case "AUTUMN" -> "Hiking";
            case "WINTER" -> "Skiing";
            default -> "Reading";
        };  // Note the semicolon! It's an expression (statement), so it needs one.

        System.out.println("  Recommended activity for " + season + ": " + activity);
        System.out.println();
        System.out.println("  💡 Enhanced switch advantages:");
        System.out.println("     • No break needed (no fall-through bugs!)");
        System.out.println("     • Can return values (switch expression)");
        System.out.println("     • Cleaner, more readable syntax");
        System.out.println("     • Multiple values per case: case \"A\", \"B\" -> ...");
        System.out.println();

        // =====================================================================
        // PART 7: COMBINING CONDITIONS (Real-world scenarios)
        // =====================================================================
        System.out.println("━━━ PART 7: Real-World Scenarios ━━━");
        System.out.println();

        // Let's build something practical: A simple login validator
        String username = "admin";
        String password = "secret123";
        boolean isAccountLocked = false;

        System.out.println("Login attempt: user='" + username + "'");

        if (isAccountLocked) {
            System.out.println("  → 🔒 Account is locked! Contact support.");
        } else if (username.equals("admin") && password.equals("secret123")) {
            System.out.println("  → ✅ Login successful! Welcome, admin.");
        } else if (username.equals("admin")) {
            System.out.println("  → ❌ Wrong password!");
        } else {
            System.out.println("  → ❌ User not found!");
        }
        System.out.println();

        // Another example: Ticket pricing system
        int customerAge = 65;
        boolean isMember = true;
        double ticketPrice;

        if (customerAge < 5) {
            ticketPrice = 0;        // Free for toddlers
        } else if (customerAge < 12) {
            ticketPrice = 5.00;     // Kids discount
        } else if (customerAge >= 65) {
            ticketPrice = 7.50;     // Senior discount
        } else {
            ticketPrice = 15.00;    // Adult price
        }

        // Apply member discount on top
        if (isMember) {
            ticketPrice *= 0.8;  // 20% off for members
        }

        System.out.println("  🎟️ Ticket Pricing:");
        System.out.println("  Customer age: " + customerAge + ", Member: " + isMember);
        System.out.printf("  Final price: $%.2f%n", ticketPrice);
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Using = instead of == in conditions:");
        System.out.println("   if (x = 5)  ❌  (this ASSIGNS, doesn't compare!)");
        System.out.println("   if (x == 5) ✅");
        System.out.println();
        System.out.println("2. Comparing Strings with ==:");
        System.out.println("   if (name == \"admin\")      ❌  (compares references)");
        System.out.println("   if (name.equals(\"admin\")) ✅  (compares content)");
        System.out.println();
        System.out.println("3. Forgetting break in switch:");
        System.out.println("   Without break, code 'falls through' to next case!");
        System.out.println("   Solution: Use enhanced switch (Java 14+) or ALWAYS add break.");
        System.out.println();
        System.out.println("4. Empty if body (dangling semicolon):");
        System.out.println("   if (x > 5);  ❌  (the semicolon ends the if!)");
        System.out.println("   {                 (this block ALWAYS runs!)");
        System.out.println("       // code");
        System.out.println("   }");
        System.out.println();
        System.out.println("5. Overlapping conditions in wrong order:");
        System.out.println("   if (score >= 60) → matches 78, stops here!");
        System.out.println("   else if (score >= 70) → 78 NEVER reaches this!");
        System.out.println("   Always go from MOST SPECIFIC to LEAST SPECIFIC.");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("1. BMI Calculator: Given height and weight, calculate BMI and");
        System.out.println("   print the category (Underweight, Normal, Overweight, Obese).");
        System.out.println("2. Month name: Given a month number (1-12), print the month name");
        System.out.println("   using a switch statement.");
        System.out.println("3. Triangle classifier: Given 3 side lengths, determine if the");
        System.out.println("   triangle is equilateral, isosceles, or scalene.");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: D_Loops.java");
    }
}
