package com.ultimate.java.basics;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                            LOOPS IN JAVA                                   ║
 * ║                  Making Your Program Repeat Things Smartly                  ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │                                                                           │
 * │  • A_VariablesAndDataTypes.java (variables, int, boolean)                │
 * │  • B_Operators.java (comparison: <, >, <=, >=, ++, --)                   │
 * │  • C_ControlFlow.java (if-else — loops often use conditions inside)      │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT ARE LOOPS?                                                           │
 * │                                                                           │
 * │  A loop is a way to REPEAT a block of code multiple times.               │
 * │                                                                           │
 * │  Think of a loop as a PLAYLIST on repeat 🔁:                             │
 * │    - You set the playlist (the code to repeat)                            │
 * │    - You decide when to stop (the condition)                              │
 * │    - The songs play over and over until you say stop                      │
 * │                                                                           │
 * │  Without loops, if you wanted to print "Hello" 1000 times,               │
 * │  you'd need 1000 lines of System.out.println("Hello")!                   │
 * │  With a loop? Just 3 lines.                                              │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY DO WE NEED LOOPS?                                                    │
 * │                                                                           │
 * │  Real-life loops happen everywhere:                                       │
 * │    🔄 Brushing teeth: "Move brush up-down" × 100 strokes                │
 * │    🔄 Eating: "Take a bite, chew, swallow" until plate is empty          │
 * │    🔄 Scrolling Instagram: "Load next post" until you close the app      │
 * │    🔄 Payroll: "Calculate salary" for EACH of 500 employees              │
 * │                                                                           │
 * │  Without loops, programs couldn't process lists, repeat actions,          │
 * │  or handle any task that involves "do this for each item."               │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHICH LOOP SHOULD I USE? (Quick Decision Guide)                           │
 * │                                                                           │
 * │  "Do I know HOW MANY times to repeat?"                                   │
 * │     YES → Use 'for' loop                                                │
 * │     NO  → Use 'while' loop                                              │
 * │                                                                           │
 * │  "Must the code run AT LEAST ONCE?"                                      │
 * │     YES → Use 'do-while' loop                                           │
 * │                                                                           │
 * │  "Am I going through each item in a collection?"                          │
 * │     YES → Use 'for-each' loop                                           │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class D_Loops {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 4: Loops                                   ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: FOR LOOP (When you know how many times)
        // =====================================================================
        System.out.println("━━━ PART 1: for Loop ━━━");
        System.out.println();

        // Syntax:
        //   for (initialization; condition; update) {
        //       // code to repeat
        //   }
        //
        // Broken down:
        //   initialization → runs ONCE at the start (set up counter)
        //   condition      → checked BEFORE each iteration (should I continue?)
        //   update         → runs AFTER each iteration (change the counter)
        //
        // Think: "FOR each day from 1 to 5, go to work."

        System.out.println("Counting from 1 to 5:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("  i = " + i);
        }
        System.out.println();

        // Let's trace through this step by step:
        // ┌────────┬────────────┬───────────┬──────────┬────────────────┐
        // │  Step  │ Initialize │ Condition │ Execute  │    Update      │
        // ├────────┼────────────┼───────────┼──────────┼────────────────┤
        // │   1    │  i = 1     │ 1 <= 5? ✅│ print 1 │ i++ → i = 2    │
        // │   2    │  (skip)    │ 2 <= 5? ✅│ print 2 │ i++ → i = 3    │
        // │   3    │  (skip)    │ 3 <= 5? ✅│ print 3 │ i++ → i = 4    │
        // │   4    │  (skip)    │ 4 <= 5? ✅│ print 4 │ i++ → i = 5    │
        // │   5    │  (skip)    │ 5 <= 5? ✅│ print 5 │ i++ → i = 6    │
        // │   6    │  (skip)    │ 6 <= 5? ❌│  STOP!  │                │
        // └────────┴────────────┴───────────┴──────────┴────────────────┘

        System.out.println("Trace: Init once → Check → Execute → Update → Check → ...");
        System.out.println();

        // Counting backwards
        System.out.println("Countdown:");
        for (int i = 5; i >= 1; i--) {
            System.out.println("  " + i + "...");
        }
        System.out.println("  🚀 Liftoff!");
        System.out.println();

        // Stepping by 2 (skip every other number)
        System.out.println("Even numbers from 2 to 10:");
        for (int i = 2; i <= 10; i += 2) {
            System.out.print("  " + i);
        }
        System.out.println();
        System.out.println();

        // Practical example: Multiplication table
        int number = 7;
        System.out.println("Multiplication table for " + number + ":");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("  %d × %d = %d%n", number, i, number * i);
        }
        System.out.println();

        // =====================================================================
        // PART 2: WHILE LOOP (When you DON'T know how many times)
        // =====================================================================
        System.out.println("━━━ PART 2: while Loop ━━━");
        System.out.println();

        // Syntax:
        //   while (condition) {
        //       // code to repeat
        //       // MUST eventually make condition false, or it loops FOREVER!
        //   }
        //
        // Think: "WHILE there's food on my plate, keep eating."
        // You don't know exactly how many bites — you just eat until done.

        // Example: Halving a number until it reaches 1
        int value = 128;
        System.out.println("Starting value: " + value);
        System.out.println("Halving until we reach 1:");

        int steps = 0;
        while (value > 1) {
            value = value / 2;
            steps++;
            System.out.println("  Step " + steps + ": " + value);
        }
        System.out.println("  Reached 1 in " + steps + " steps!");
        System.out.println();

        // Real-world example: Simple password attempt simulator
        System.out.println("Password attempt simulation:");
        String correctPassword = "java123";
        String[] attempts = {"password", "123456", "java123"};  // simulated
        int attemptIndex = 0;
        boolean loggedIn = false;

        while (!loggedIn && attemptIndex < attempts.length) {
            System.out.println("  Attempt " + (attemptIndex + 1) + ": trying '" + attempts[attemptIndex] + "'");
            if (attempts[attemptIndex].equals(correctPassword)) {
                loggedIn = true;
                System.out.println("  → ✅ Correct! Logged in.");
            } else {
                System.out.println("  → ❌ Wrong password.");
            }
            attemptIndex++;
        }
        System.out.println();

        // =====================================================================
        // PART 3: DO-WHILE LOOP (Always runs at least once)
        // =====================================================================
        System.out.println("━━━ PART 3: do-while Loop ━━━");
        System.out.println();

        // Syntax:
        //   do {
        //       // code runs AT LEAST ONCE
        //   } while (condition);  // ← note the semicolon!
        //
        // Think: At a restaurant:
        //   DO order food, WHILE you're still hungry.
        //   You always order at LEAST ONE dish before checking if you're full.
        //
        // KEY DIFFERENCE from while:
        //   while: Checks condition FIRST, might never execute
        //   do-while: Executes FIRST, then checks condition

        System.out.println("do-while vs while when condition is FALSE from the start:");

        // while — condition is false, body NEVER runs
        System.out.print("  while (false):    ");
        int counter = 0;
        while (counter > 0) {
            System.out.print("This never prints");
            counter--;
        }
        System.out.println("(nothing printed — body never ran)");

        // do-while — condition is false, body runs ONCE
        System.out.print("  do-while (false): ");
        counter = 0;
        do {
            System.out.print("Runs once!");
            counter--;
        } while (counter > 0);
        System.out.println(" (body ran once before checking condition)");
        System.out.println();

        // Practical example: Menu system (always show menu at least once)
        System.out.println("  💡 do-while is PERFECT for menus:");
        System.out.println("     do {");
        System.out.println("         showMenu();");
        System.out.println("         getChoice();");
        System.out.println("     } while (choice != EXIT);");
        System.out.println("  The menu always shows at least once!");
        System.out.println();

        // =====================================================================
        // PART 4: FOR-EACH LOOP (Iterate through collections)
        // =====================================================================
        System.out.println("━━━ PART 4: for-each Loop ━━━");
        System.out.println();

        // Syntax:
        //   for (Type item : collection) {
        //       // use 'item' — it's each element, one at a time
        //   }
        //
        // Think: A teacher calling roll:
        //   "FOR EACH student IN the class, call their name."
        //   You don't care about position numbers — just go through each one.

        String[] fruits = {"Apple", "Banana", "Cherry", "Date", "Elderberry"};

        System.out.println("Fruits in the basket:");
        for (String fruit : fruits) {
            System.out.println("  🍎 " + fruit);
        }
        System.out.println();

        // for-each vs regular for — when to use which?
        System.out.println("  📋 for-each vs regular for:");
        System.out.println("  ┌───────────────────────────┬───────────────────────────────┐");
        System.out.println("  │  Use FOR-EACH when:       │  Use regular FOR when:        │");
        System.out.println("  ├───────────────────────────┼───────────────────────────────┤");
        System.out.println("  │ • Just reading each item  │ • Need the index (position)   │");
        System.out.println("  │ • Don't need the index    │ • Need to modify the array    │");
        System.out.println("  │ • Cleaner, simpler code   │ • Need to go backwards        │");
        System.out.println("  │ • Less chance of bugs     │ • Need to skip items           │");
        System.out.println("  └───────────────────────────┴───────────────────────────────┘");
        System.out.println();

        // =====================================================================
        // PART 5: BREAK AND CONTINUE (Loop control)
        // =====================================================================
        System.out.println("━━━ PART 5: break and continue ━━━");
        System.out.println();

        // break → EXITS the loop immediately. Done. Gone. Out.
        // Think: Fire alarm goes off → STOP what you're doing, leave immediately.

        System.out.println("break example — searching for 'Cherry':");
        for (String fruit : fruits) {
            if (fruit.equals("Cherry")) {
                System.out.println("  → 🎯 Found Cherry! Stopping search.");
                break;  // Exit the loop — no need to check the rest
            }
            System.out.println("  Checking: " + fruit + " — nope");
        }
        System.out.println();

        // continue → SKIPS the rest of this iteration, goes to next one.
        // Think: "This page is boring, SKIP to the next page."

        System.out.println("continue example — skip even numbers:");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue;  // Skip even numbers, jump to next iteration
            }
            System.out.print("  " + i);  // Only odd numbers get here
        }
        System.out.println();
        System.out.println();

        // =====================================================================
        // PART 6: NESTED LOOPS (Loops inside loops)
        // =====================================================================
        System.out.println("━━━ PART 6: Nested Loops ━━━");
        System.out.println();

        // A loop INSIDE another loop.
        // Think: A clock → the minute hand goes around 60 times for EACH
        //        time the hour hand moves once. That's a nested loop!
        //
        // Outer loop: hours (12 times)
        //   Inner loop: minutes (60 times for EACH hour)

        // Example: Print a small pattern
        System.out.println("Star pattern (nested for loops):");
        for (int row = 1; row <= 5; row++) {
            System.out.print("  ");
            for (int col = 1; col <= row; col++) {
                System.out.print("★ ");
            }
            System.out.println();
        }
        System.out.println();

        // Multiplication table (classic nested loop example)
        System.out.println("Mini multiplication table (1-5):");
        System.out.println("  ┌────┬────┬────┬────┬────┬────┐");
        System.out.print("  │ ×  ");
        for (int j = 1; j <= 5; j++) {
            System.out.printf("│ %2d ", j);
        }
        System.out.println("│");
        System.out.println("  ├────┼────┼────┼────┼────┼────┤");

        for (int i = 1; i <= 5; i++) {
            System.out.printf("  │ %2d ", i);
            for (int j = 1; j <= 5; j++) {
                System.out.printf("│ %2d ", i * j);
            }
            System.out.println("│");
        }
        System.out.println("  └────┴────┴────┴────┴────┴────┘");
        System.out.println();

        // ⚠️ PERFORMANCE WARNING about nested loops:
        System.out.println("  ⚠️ PERFORMANCE: Nested loops MULTIPLY the iterations!");
        System.out.println("     Outer runs 100 times × Inner runs 100 times = 10,000 total!");
        System.out.println("     3 levels deep with 100 each = 1,000,000! Be careful.");
        System.out.println();

        // =====================================================================
        // PART 7: LABELED LOOPS (Breaking out of nested loops)
        // =====================================================================
        System.out.println("━━━ PART 7: Labeled break/continue ━━━");
        System.out.println();

        // Normal break only exits the INNERMOST loop.
        // What if you want to exit the OUTER loop from inside the inner one?
        // Use a LABEL!

        System.out.println("Finding first pair that multiplies to 12:");
        outerLoop:  // ← This is a LABEL (any name followed by a colon)
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                if (i * j == 12) {
                    System.out.println("  Found: " + i + " × " + j + " = 12");
                    break outerLoop;  // Breaks out of BOTH loops!
                }
            }
        }
        System.out.println("  (Without the label, break would only exit the inner loop)");
        System.out.println();

        // =====================================================================
        // PART 8: INFINITE LOOPS (On purpose and by accident)
        // =====================================================================
        System.out.println("━━━ PART 8: Infinite Loops ━━━");
        System.out.println();

        // An infinite loop runs FOREVER (until you manually stop it or break out).
        // Sometimes this is intentional (game loops, servers).
        // Often it's a BUG!

        System.out.println("  Intentional infinite loop patterns:");
        System.out.println("    while (true) { ... break; }  ← common idiom");
        System.out.println("    for (;;) { ... break; }      ← also works");
        System.out.println();
        System.out.println("  ⚠️ ACCIDENTAL infinite loop (common bugs):");
        System.out.println("    int i = 0;");
        System.out.println("    while (i < 10) {");
        System.out.println("        System.out.println(i);");
        System.out.println("        // OOPS! Forgot i++; → i stays 0 forever!");
        System.out.println("    }");
        System.out.println();
        System.out.println("  💡 If your program seems 'stuck', it might be in an");
        System.out.println("  infinite loop. Press Ctrl+C in the terminal to stop it!");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Off-by-one errors (the MOST common bug in programming!):");
        System.out.println("   for (int i = 1; i < 5; ...)  → runs 4 times (1,2,3,4)");
        System.out.println("   for (int i = 1; i <= 5; ...) → runs 5 times (1,2,3,4,5)");
        System.out.println("   for (int i = 0; i < 5; ...)  → runs 5 times (0,1,2,3,4)");
        System.out.println("   Always count carefully: < vs <=, starting from 0 vs 1!");
        System.out.println();
        System.out.println("2. Modifying collection while iterating:");
        System.out.println("   for (String s : list) {");
        System.out.println("       list.remove(s);  ❌  ConcurrentModificationException!");
        System.out.println("   }");
        System.out.println();
        System.out.println("3. Forgetting to update the loop variable in while:");
        System.out.println("   while (x < 10) { ... }  ← if x never changes, infinite loop!");
        System.out.println();
        System.out.println("4. Using wrong variable in nested loops:");
        System.out.println("   for (int i ...) { for (int j ...) { array[i][i] ← should be [i][j]! } }");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("1. FizzBuzz (classic interview question!):");
        System.out.println("   Print 1-100. For multiples of 3 print 'Fizz',");
        System.out.println("   multiples of 5 print 'Buzz', both print 'FizzBuzz'.");
        System.out.println();
        System.out.println("2. Print a pyramid pattern:");
        System.out.println("       *         (row 1: 4 spaces, 1 star)");
        System.out.println("      ***        (row 2: 3 spaces, 3 stars)");
        System.out.println("     *****       (row 3: 2 spaces, 5 stars)");
        System.out.println("    *******      (row 4: 1 space, 7 stars)");
        System.out.println();
        System.out.println("3. Find all prime numbers between 2 and 50.");
        System.out.println("   (A prime number is only divisible by 1 and itself)");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: E_Methods.java");
    }
}
