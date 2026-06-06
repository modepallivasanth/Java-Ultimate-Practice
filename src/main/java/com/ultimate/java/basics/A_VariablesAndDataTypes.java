package com.ultimate.java.basics;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                    VARIABLES AND DATA TYPES IN JAVA                        ║
 * ║                        The Building Blocks of Everything                   ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES (What you need to know before this):                        │
 * │                                                                           │
 * │  • How to read English (seriously, that's it!)                            │
 * │  • Basic math (addition, subtraction, multiplication, division)           │
 * │  • The concept that computers store information (like your phone saves    │
 * │    contacts)                                                              │
 * │  • Java is installed on your machine (JDK 17+)                           │
 * │  • You know how to run a Java file (right-click → Run, or use terminal)  │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT ARE VARIABLES?                                                       │
 * │                                                                           │
 * │  Think of a variable as a LABELED JAR in your kitchen.                    │
 * │                                                                           │
 * │    🏺 "Sugar" jar  → contains sugar                                      │
 * │    🏺 "Salt" jar   → contains salt                                       │
 * │    🏺 "Flour" jar  → contains flour                                      │
 * │                                                                           │
 * │  Each jar has:                                                            │
 * │    1. A LABEL (the variable name)     → "sugar"                           │
 * │    2. A TYPE of thing it holds        → powder/granules                   │
 * │    3. The actual CONTENTS inside      → the sugar itself                  │
 * │                                                                           │
 * │  In Java:                                                                 │
 * │    int age = 25;                                                          │
 * │    ↑    ↑    ↑                                                            │
 * │    │    │    └── The value (contents of the jar)                          │
 * │    │    └─────── The name (label on the jar)                              │
 * │    └──────────── The type (what kind of jar)                              │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT ARE DATA TYPES?                                                      │
 * │                                                                           │
 * │  Data types define WHAT KIND of data a variable can hold.                 │
 * │                                                                           │
 * │  Why not just have one type for everything? Think of it this way:         │
 * │                                                                           │
 * │  Would you store water in a paper bag? No!                                │
 * │  Would you store rocks in a glass bottle? Probably not!                   │
 * │                                                                           │
 * │  Different types of data need different types of "containers" because:    │
 * │    • Numbers need math operations (you can add 5 + 3)                     │
 * │    • Text needs text operations (you can combine "Hello" + " World")      │
 * │    • True/False needs logic operations (is the light ON or OFF?)          │
 * │                                                                           │
 * │  Java has TWO categories of data types:                                   │
 * │                                                                           │
 * │  1. PRIMITIVE TYPES (8 types) - Simple, raw values stored directly        │
 * │     Think: Actual sugar inside the jar                                    │
 * │                                                                           │
 * │  2. REFERENCE TYPES - Store the ADDRESS (location) of complex data       │
 * │     Think: A note inside the jar that says "sugar is in the warehouse     │
 * │     at shelf B, row 3"                                                    │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY DO WE NEED VARIABLES AND DATA TYPES?                                 │
 * │                                                                           │
 * │  Imagine you're a cashier and a customer buys 3 items:                    │
 * │    Item 1: $5.99                                                          │
 * │    Item 2: $12.50                                                         │
 * │    Item 3: $3.25                                                          │
 * │                                                                           │
 * │  Without variables, you'd have to remember ALL of these numbers           │
 * │  in your head while calculating the total, applying tax, and             │
 * │  giving change. That's what a computer without variables would            │
 * │  have to do — impossible for complex programs!                            │
 * │                                                                           │
 * │  Variables let the program REMEMBER things.                               │
 * │  Data types let the program UNDERSTAND what those things are.             │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class A_VariablesAndDataTypes {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 1: Variables and Data Types                ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: THE 8 PRIMITIVE DATA TYPES
        // =====================================================================
        // Java has exactly 8 primitive types. Think of them as the 8 basic
        // building blocks — like LEGO has basic bricks before the fancy pieces.
        //
        // Let's meet them all:

        System.out.println("━━━ PART 1: The 8 Primitive Data Types ━━━");
        System.out.println();

        // ─────────────────────────────────────────────────────────────────────
        // TYPE 1: byte (Tiny whole numbers)
        // ─────────────────────────────────────────────────────────────────────
        // Size: 1 byte (8 bits) → Range: -128 to 127
        // Real-world analogy: Like a small drawer that can hold tiny items
        // Use when: You know the number will be very small (saves memory!)
        byte myAge = 25;              // Your age will never be 10,000, right?
        byte temperatureCelsius = -5; // Winter temperature
        System.out.println("byte myAge = " + myAge);
        System.out.println("  → byte holds small numbers: -128 to 127");
        System.out.println("  → Uses only 1 byte of memory (the smallest!)");
        System.out.println();

        // ─────────────────────────────────────────────────────────────────────
        // TYPE 2: short (Small whole numbers)
        // ─────────────────────────────────────────────────────────────────────
        // Size: 2 bytes (16 bits) → Range: -32,768 to 32,767
        // Real-world analogy: Like a medium drawer — bigger than byte
        // Use when: byte is too small but int feels wasteful
        short studentsInSchool = 1500;  // Most schools have < 32,767 students
        short elevation = -400;          // Dead Sea elevation in meters
        System.out.println("short studentsInSchool = " + studentsInSchool);
        System.out.println("  → short holds medium numbers: -32,768 to 32,767");
        System.out.println("  → Uses 2 bytes of memory");
        System.out.println();

        // ─────────────────────────────────────────────────────────────────────
        // TYPE 3: int (Standard whole numbers) ★ MOST COMMONLY USED ★
        // ─────────────────────────────────────────────────────────────────────
        // Size: 4 bytes (32 bits) → Range: ~-2.1 billion to ~2.1 billion
        // Real-world analogy: Like a regular storage box — fits most things
        // Use when: In doubt, use int! It's the default for whole numbers.
        int worldPopulation = 2_100_000_000; // Note: underscores make big numbers readable!
        int bankBalance = -50_000;            // Oops, overdraft!
        int year = 2025;
        System.out.println("int year = " + year);
        System.out.println("  → int is the GO-TO type for whole numbers");
        System.out.println("  → Range: about -2.1 billion to 2.1 billion");
        System.out.println("  → Uses 4 bytes of memory");
        System.out.println("  → TIP: Use underscores in big numbers: 2_100_000_000");
        System.out.println();

        // ─────────────────────────────────────────────────────────────────────
        // TYPE 4: long (Very large whole numbers)
        // ─────────────────────────────────────────────────────────────────────
        // Size: 8 bytes (64 bits) → Range: ~-9.2 quintillion to ~9.2 quintillion
        // Real-world analogy: Like a warehouse — for really big things
        // Use when: int isn't big enough (population of Earth, file sizes, etc.)
        // IMPORTANT: Add 'L' at the end to tell Java "this is a long, not an int"
        long distanceToSunInKm = 149_600_000L; // Must end with L!
        long globalPopulation = 8_000_000_000L; // 8 billion doesn't fit in int!
        System.out.println("long distanceToSunInKm = " + distanceToSunInKm);
        System.out.println("  → long is for HUGE numbers that don't fit in int");
        System.out.println("  → MUST add 'L' at the end: 8_000_000_000L");
        System.out.println("  → Uses 8 bytes of memory");
        System.out.println();

        // ─────────────────────────────────────────────────────────────────────
        // TYPE 5: float (Decimal numbers - less precise)
        // ─────────────────────────────────────────────────────────────────────
        // Size: 4 bytes → ~7 decimal digits of precision
        // Real-world analogy: A measuring cup with rough markings
        // Use when: You need decimals but don't need extreme precision
        // IMPORTANT: Add 'f' at the end!
        float piRough = 3.14159f;        // Must end with f!
        float bodyTemperature = 98.6f;
        System.out.println("float piRough = " + piRough);
        System.out.println("  → float holds decimal numbers with ~7 digits precision");
        System.out.println("  → MUST add 'f' at the end: 3.14f");
        System.out.println("  → Uses 4 bytes of memory");
        System.out.println();

        // ─────────────────────────────────────────────────────────────────────
        // TYPE 6: double (Decimal numbers - more precise) ★ DEFAULT FOR DECIMALS ★
        // ─────────────────────────────────────────────────────────────────────
        // Size: 8 bytes → ~15 decimal digits of precision
        // Real-world analogy: A precision measuring instrument
        // Use when: In doubt about decimals, use double! It's the default.
        double piPrecise = 3.141592653589793;  // No suffix needed — double is default!
        double accountBalance = 1_234_567.89;
        System.out.println("double piPrecise = " + piPrecise);
        System.out.println("  → double is the GO-TO type for decimal numbers");
        System.out.println("  → ~15 digits of precision (twice the float!)");
        System.out.println("  → Uses 8 bytes of memory");
        System.out.println("  → No suffix needed — Java treats decimals as double by default");
        System.out.println();

        // ─────────────────────────────────────────────────────────────────────
        // TYPE 7: char (A single character)
        // ─────────────────────────────────────────────────────────────────────
        // Size: 2 bytes → Holds ONE character using Unicode
        // Real-world analogy: A single Scrabble tile
        // Use when: You need exactly one character
        // IMPORTANT: Use SINGLE QUOTES ' ' (not double quotes " ")
        char grade = 'A';
        char dollarSign = '$';
        char heart = '❤';              // Java supports Unicode characters!
        char newLine = '\n';            // Special characters work too
        System.out.println("char grade = '" + grade + "'");
        System.out.println("char heart = '" + heart + "'");
        System.out.println("  → char holds exactly ONE character");
        System.out.println("  → Use SINGLE quotes: 'A' (not \"A\")");
        System.out.println("  → Supports Unicode: emojis, symbols, any language!");
        System.out.println();

        // ─────────────────────────────────────────────────────────────────────
        // TYPE 8: boolean (True or False — that's it!)
        // ─────────────────────────────────────────────────────────────────────
        // Size: ~1 bit (JVM-dependent)
        // Real-world analogy: A light switch — ON or OFF, nothing else
        // Use when: You need a yes/no, true/false, on/off answer
        boolean isJavaFun = true;
        boolean isRaining = false;
        boolean isAdult = (myAge >= 18); // Can be the result of a comparison!
        System.out.println("boolean isJavaFun = " + isJavaFun);
        System.out.println("boolean isAdult = " + isAdult + "  (because age=" + myAge + " >= 18)");
        System.out.println("  → boolean can ONLY be true or false");
        System.out.println("  → Named after George Boole (mathematician)");
        System.out.println();

        // =====================================================================
        // PART 2: REFERENCE TYPES (The Big Ones)
        // =====================================================================
        System.out.println("━━━ PART 2: Reference Types ━━━");
        System.out.println();

        // Reference types store an ADDRESS (reference/pointer) to data in memory,
        // not the data itself. Think of it like:
        //
        // Primitive: You hold the actual apple in your hand 🍎
        // Reference: You hold a MAP that tells you where the apple is stored 🗺️→🍎
        //
        // The most common reference type is String:

        String name = "Vasanth";  // String uses DOUBLE quotes " "
        String greeting = "Hello, " + name + "!";  // + combines (concatenates) strings
        System.out.println("String name = \"" + name + "\"");
        System.out.println("String greeting = \"" + greeting + "\"");
        System.out.println("  → String is a REFERENCE type (starts with uppercase S)");
        System.out.println("  → Use DOUBLE quotes: \"Hello\" (not 'Hello')");
        System.out.println("  → Strings are IMMUTABLE (cannot be changed after creation)");
        System.out.println("  → We'll deep-dive into Strings in G_Strings.java!");
        System.out.println();

        // =====================================================================
        // PART 3: TYPE CASTING (Converting between types)
        // =====================================================================
        System.out.println("━━━ PART 3: Type Casting ━━━");
        System.out.println();

        // Sometimes you need to convert one type to another.
        // There are two kinds:

        // --- WIDENING (Automatic / Implicit) ---
        // Small type → Big type = SAFE, Java does it automatically
        // Think: Pouring water from a small glass into a big bucket — no spillage!
        //
        // byte → short → int → long → float → double
        //   ↑ small                          big ↑

        int myNumber = 100;
        double myDouble = myNumber;  // int (4 bytes) → double (8 bytes) = automatic!
        System.out.println("Widening: int " + myNumber + " → double " + myDouble);
        System.out.println("  → Small to big = automatic, no data loss");
        System.out.println();

        // --- NARROWING (Manual / Explicit) ---
        // Big type → Small type = RISKY, YOU must tell Java to do it
        // Think: Pouring water from a big bucket into a small glass — might overflow!

        double bigNumber = 9.99;
        int smallNumber = (int) bigNumber;  // You must add (int) to force the conversion!
        System.out.println("Narrowing: double " + bigNumber + " → int " + smallNumber);
        System.out.println("  → Big to small = manual, might lose data! (9.99 became 9)");
        System.out.println("  → The decimal part is CHOPPED OFF (not rounded!)");
        System.out.println();

        // =====================================================================
        // PART 4: VARIABLE NAMING RULES & CONVENTIONS
        // =====================================================================
        System.out.println("━━━ PART 4: Naming Rules & Conventions ━━━");
        System.out.println();

        // RULES (Java ENFORCES these — your code won't compile otherwise):
        // ✅ Can contain: letters, digits, underscores (_), dollar signs ($)
        // ✅ Must START with: a letter, underscore, or dollar sign
        // ❌ Cannot start with: a digit
        // ❌ Cannot be a Java keyword (like int, class, public, etc.)
        // ❌ Cannot contain spaces

        // CONVENTIONS (Java RECOMMENDS these — code works without them, but follow them):
        // ✅ Use camelCase: myVariableName (not my_variable_name)
        // ✅ Start with lowercase letter
        // ✅ Use meaningful names: studentAge (not x or sa)
        // ✅ Constants use ALL_CAPS: MAX_SIZE, PI

        int studentAge = 20;         // ✅ Good: camelCase, descriptive
        // int 2ndPlace = 2;         // ❌ ERROR: Can't start with a digit
        // int my age = 20;          // ❌ ERROR: Can't have spaces
        int _valid = 1;              // ✅ Legal but not recommended
        int $alsoValid = 2;          // ✅ Legal but not recommended
        final int MAX_SPEED = 120;   // ✅ Constant: ALL_CAPS with underscores

        System.out.println("Good names:  studentAge, firstName, isLoggedIn");
        System.out.println("Bad names:   x, a, temp, data (too vague!)");
        System.out.println("Constants:   MAX_SPEED = " + MAX_SPEED + " (use final + ALL_CAPS)");
        System.out.println();

        // =====================================================================
        // PART 5: var KEYWORD (Java 10+) — Type Inference
        // =====================================================================
        System.out.println("━━━ PART 5: The 'var' Keyword (Java 10+) ━━━");
        System.out.println();

        // Starting from Java 10, you can let Java GUESS the type for you.
        // Instead of writing the type explicitly, you write 'var' and Java
        // figures it out from the value you assign.

        var message = "Hello!";     // Java knows this is a String
        var count = 42;             // Java knows this is an int
        var price = 19.99;          // Java knows this is a double
        var isActive = true;        // Java knows this is a boolean

        System.out.println("var message = \"Hello!\"  → Java infers: String");
        System.out.println("var count = 42          → Java infers: int");
        System.out.println("var price = 19.99       → Java infers: double");
        System.out.println("  → 'var' is syntactic sugar — the type is still fixed!");
        System.out.println("  → You can NOT do: var x; (must assign a value immediately)");
        System.out.println();

        // =====================================================================
        // PART 6: DEFAULT VALUES (for class-level fields only)
        // =====================================================================
        System.out.println("━━━ PART 6: Default Values ━━━");
        System.out.println();
        System.out.println("When you declare a variable as a CLASS FIELD (not inside a method),");
        System.out.println("Java gives it a default value if you don't assign one:");
        System.out.println();
        System.out.println("  byte, short, int, long  →  0");
        System.out.println("  float, double           →  0.0");
        System.out.println("  char                    →  '\\u0000' (null character)");
        System.out.println("  boolean                 →  false");
        System.out.println("  Reference types          →  null (points to nothing)");
        System.out.println();
        System.out.println("  ⚠️  LOCAL variables (inside methods) have NO default value!");
        System.out.println("  ⚠️  You MUST assign them before using, or you get a compile error.");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES (Learn from others' pain!)
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Forgetting 'L' suffix for long: long x = 8000000000;   ❌");
        System.out.println("   Fix:                            long x = 8000000000L;  ✅");
        System.out.println();
        System.out.println("2. Forgetting 'f' suffix for float: float x = 3.14;   ❌");
        System.out.println("   Fix:                             float x = 3.14f;  ✅");
        System.out.println();
        System.out.println("3. Using \" for char:  char c = \"A\";   ❌");
        System.out.println("   Fix:               char c = 'A';   ✅");
        System.out.println();
        System.out.println("4. Integer division surprise:");
        int result = 7 / 2;
        System.out.println("   7 / 2 = " + result + "  (not 3.5! Integer division truncates)");
        double correctResult = 7.0 / 2;
        System.out.println("   7.0 / 2 = " + correctResult + "  (use at least one double!)");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("Try these yourself! Create variables for:");
        System.out.println("  1. Your name (what type?)");
        System.out.println("  2. Your age (what type?)");
        System.out.println("  3. Your GPA (what type? hint: it has decimals)");
        System.out.println("  4. Whether you like Java (what type?)");
        System.out.println("  5. Your first initial (what type? just ONE character)");
        System.out.println();
        System.out.println("  Then print them all out in a sentence like:");
        System.out.println("  \"Hi, I'm [name], age [age], GPA [gpa], loves Java: [boolean]\"");
        System.out.println();

        // =====================================================================
        // 📊 QUICK REFERENCE CARD
        // =====================================================================
        System.out.println("━━━ 📊 QUICK REFERENCE ━━━");
        System.out.println("┌──────────┬───────┬───────────────────────────────────────┐");
        System.out.println("│ Type     │ Bytes │ Range / Use                           │");
        System.out.println("├──────────┼───────┼───────────────────────────────────────┤");
        System.out.println("│ byte     │   1   │ -128 to 127                           │");
        System.out.println("│ short    │   2   │ -32,768 to 32,767                     │");
        System.out.println("│ int ★    │   4   │ ~-2.1B to ~2.1B (default for ints)    │");
        System.out.println("│ long     │   8   │ ~-9.2 quintillion (add L suffix)      │");
        System.out.println("│ float    │   4   │ ~7 decimal digits (add f suffix)      │");
        System.out.println("│ double ★ │   8   │ ~15 decimal digits (default decimals) │");
        System.out.println("│ char     │   2   │ One character (single quotes)         │");
        System.out.println("│ boolean  │  ~1   │ true or false                         │");
        System.out.println("└──────────┴───────┴───────────────────────────────────────┘");
        System.out.println("  ★ = Default choice for that category");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: B_Operators.java");
    }
}
