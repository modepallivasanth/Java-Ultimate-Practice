package com.ultimate.java.core;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                      WRAPPER CLASSES IN JAVA                               ║
 * ║        Primitives in Object Clothing — Autoboxing & Unboxing               ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │  • A_VariablesAndDataTypes.java (8 primitive types)                       │
 * │  • OOP concepts (classes, objects, methods)                               │
 * │  • Understanding that primitives are NOT objects                          │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT ARE WRAPPER CLASSES?                                                 │
 * │                                                                           │
 * │  Every primitive type has a corresponding OBJECT version (wrapper):       │
 * │    int     → Integer        float   → Float                              │
 * │    byte    → Byte           double  → Double                             │
 * │    short   → Short          char    → Character                          │
 * │    long    → Long           boolean → Boolean                            │
 * │                                                                           │
 * │  Think: A primitive is a RAW DIAMOND 💎                                  │
 * │         A wrapper is the diamond in a JEWELRY BOX 📦💎                   │
 * │         Same diamond, but now it can be: gifted, shipped, displayed.     │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY DO WE NEED WRAPPERS?                                                 │
 * │                                                                           │
 * │  1. Collections only work with OBJECTS (not primitives!)                  │
 * │     ArrayList<int> → ❌  ArrayList<Integer> → ✅                         │
 * │  2. Generics need objects: Map<String, Integer>                           │
 * │  3. Null support: Integer can be null, int cannot                        │
 * │  4. Utility methods: Integer.parseInt(), Integer.MAX_VALUE               │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class B_WrapperClasses {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 16: Wrapper Classes                        ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: AUTOBOXING (Primitive → Object, automatic!)
        // =====================================================================
        System.out.println("━━━ PART 1: Autoboxing & Unboxing ━━━");
        System.out.println();

        // AUTOBOXING: Java automatically converts primitive → wrapper
        Integer num = 42;        // int 42 → Integer.valueOf(42) automatically!
        Double pi = 3.14;        // double → Double
        Boolean flag = true;     // boolean → Boolean

        // UNBOXING: Java automatically converts wrapper → primitive
        int rawNum = num;        // Integer → int automatically!
        double rawPi = pi;
        boolean rawFlag = flag;

        System.out.println("  Autoboxing:  int 42 → Integer " + num);
        System.out.println("  Unboxing:    Integer " + num + " → int " + rawNum);
        System.out.println();

        // =====================================================================
        // PART 2: USEFUL METHODS
        // =====================================================================
        System.out.println("━━━ PART 2: Useful Wrapper Methods ━━━");
        System.out.println();

        // Parsing strings to numbers:
        int parsed = Integer.parseInt("123");
        double parsedDouble = Double.parseDouble("3.14");
        System.out.println("  Integer.parseInt(\"123\") = " + parsed);
        System.out.println("  Double.parseDouble(\"3.14\") = " + parsedDouble);

        // Number to string:
        String numStr = Integer.toString(42);
        String hexStr = Integer.toHexString(255);
        String binStr = Integer.toBinaryString(10);
        System.out.println("  Integer.toString(42) = \"" + numStr + "\"");
        System.out.println("  Integer.toHexString(255) = \"" + hexStr + "\"");
        System.out.println("  Integer.toBinaryString(10) = \"" + binStr + "\"");

        // Min/Max values:
        System.out.println("  Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("  Integer.MIN_VALUE = " + Integer.MIN_VALUE);
        System.out.println("  Double.MAX_VALUE = " + Double.MAX_VALUE);

        // Comparisons:
        System.out.println("  Integer.compare(5, 10) = " + Integer.compare(5, 10) + " (negative → 5 < 10)");
        System.out.println("  Integer.max(5, 10) = " + Integer.max(5, 10));
        System.out.println("  Integer.min(5, 10) = " + Integer.min(5, 10));
        System.out.println();

        // =====================================================================
        // PART 3: THE == TRAP WITH WRAPPERS
        // =====================================================================
        System.out.println("━━━ PART 3: The == Trap (Integer Caching!) ━━━");
        System.out.println();

        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;

        System.out.println("  a = 127, b = 127 → (a == b): " + (a == b) + "  ← Same cached object!");
        System.out.println("  c = 128, d = 128 → (c == d): " + (c == d) + " ← DIFFERENT objects!");
        System.out.println("  c.equals(d):                  " + c.equals(d) + "  ← Same value ✅");
        System.out.println();
        System.out.println("  🧠 WHY? Java caches Integer values -128 to 127.");
        System.out.println("     Within that range, == works (same cached object).");
        System.out.println("     Outside that range, == compares references (not values!).");
        System.out.println("     ALWAYS use .equals() for wrapper comparison!");
        System.out.println();

        // =====================================================================
        // PART 4: NULL DANGER
        // =====================================================================
        System.out.println("━━━ PART 4: Null Unboxing Danger ━━━");
        System.out.println();

        Integer nullableNum = null;  // Wrappers CAN be null!
        System.out.println("  Integer nullableNum = null;  ← Legal for wrappers!");

        try {
            int crash = nullableNum;  // Unboxing null → NullPointerException!
        } catch (NullPointerException e) {
            System.out.println("  int x = nullableNum → 💥 NullPointerException!");
            System.out.println("  Unboxing null throws NPE. Always check for null first.");
        }
        System.out.println();

        // =====================================================================
        // REFERENCE TABLE
        // =====================================================================
        System.out.println("━━━ Quick Reference ━━━");
        System.out.println("  ┌───────────┬───────────┬───────────────────────────┐");
        System.out.println("  │ Primitive  │ Wrapper   │ Parse Method              │");
        System.out.println("  ├───────────┼───────────┼───────────────────────────┤");
        System.out.println("  │ byte      │ Byte      │ Byte.parseByte()          │");
        System.out.println("  │ short     │ Short     │ Short.parseShort()        │");
        System.out.println("  │ int       │ Integer   │ Integer.parseInt()        │");
        System.out.println("  │ long      │ Long      │ Long.parseLong()          │");
        System.out.println("  │ float     │ Float     │ Float.parseFloat()        │");
        System.out.println("  │ double    │ Double    │ Double.parseDouble()      │");
        System.out.println("  │ char      │ Character │ (no parse — use charAt)   │");
        System.out.println("  │ boolean   │ Boolean   │ Boolean.parseBoolean()    │");
        System.out.println("  └───────────┴───────────┴───────────────────────────┘");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: C_Enums.java");
    }
}
