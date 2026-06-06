package com.ultimate.java.core;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                           ENUMS IN JAVA                                    ║
 * ║              Type-Safe Constants With Superpowers                           ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT ARE ENUMS?                                                           │
 * │                                                                           │
 * │  An enum (enumeration) is a special class that represents a FIXED SET    │
 * │  of constants.                                                            │
 * │                                                                           │
 * │  Think: Traffic light → only RED, YELLOW, GREEN. Never PURPLE.           │
 * │         Days of week → only MON-SUN. Never "Funday".                     │
 * │         Card suits → only HEARTS, DIAMONDS, CLUBS, SPADES.              │
 * │                                                                           │
 * │  WHY not use String constants?                                            │
 * │    String day = "Munday";  ← TYPO! Compiles fine. Bug at runtime. 😱    │
 * │    Day day = Day.MUNDAY;   ← COMPILE ERROR! Caught immediately. ✅      │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class C_Enums {

    // ── Simple enum ──────────────────────────────────────────────────────
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    // ── Enum with fields, constructor, and methods! ──────────────────────
    enum Planet {
        MERCURY(3.303e+23, 2.4397e6),
        VENUS(4.869e+24, 6.0518e6),
        EARTH(5.976e+24, 6.37814e6),
        MARS(6.421e+23, 3.3972e6);

        private final double mass;    // in kilograms
        private final double radius;  // in meters

        // Enum constructor — always private (implicitly)!
        Planet(double mass, double radius) {
            this.mass = mass;
            this.radius = radius;
        }

        double surfaceGravity() {
            final double G = 6.67300E-11;
            return G * mass / (radius * radius);
        }

        double surfaceWeight(double otherMass) {
            return otherMass * surfaceGravity();
        }
    }

    // ── Enum implementing an interface ───────────────────────────────────
    interface Describable {
        String describe();
    }

    enum Season implements Describable {
        SPRING {
            @Override public String describe() { return "🌸 Flowers bloom, nature awakens!"; }
        },
        SUMMER {
            @Override public String describe() { return "☀️ Hot days, vacations, and ice cream!"; }
        },
        AUTUMN {
            @Override public String describe() { return "🍂 Leaves fall, cozy sweater weather!"; }
        },
        WINTER {
            @Override public String describe() { return "❄️ Cold, snowy, hot chocolate time!"; }
        };
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 17: Enums                                  ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // ── Using enums ──────────────────────────────────────────────────
        System.out.println("━━━ PART 1: Basic Enum Usage ━━━");
        System.out.println();

        Day today = Day.WEDNESDAY;
        System.out.println("  Today is: " + today);
        System.out.println("  Ordinal (position): " + today.ordinal());
        System.out.println("  Name: " + today.name());
        System.out.println();

        // Enums work great with switch!
        switch (today) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY ->
                System.out.println("  It's a weekday — time to work! 💼");
            case SATURDAY, SUNDAY ->
                System.out.println("  It's the weekend — time to relax! 🏖️");
        }
        System.out.println();

        // Iterating all values
        System.out.println("  All days:");
        for (Day d : Day.values()) {
            System.out.println("    " + d.ordinal() + ": " + d);
        }
        System.out.println();

        // valueOf — String to enum
        Day friday = Day.valueOf("FRIDAY");
        System.out.println("  Day.valueOf(\"FRIDAY\") = " + friday);
        System.out.println();

        // ── Enum with fields and methods ─────────────────────────────────
        System.out.println("━━━ PART 2: Enums with Fields & Methods ━━━");
        System.out.println();

        double earthWeight = 75.0; // kg
        System.out.println("  Your weight on different planets (if you weigh 75kg on Earth):");
        for (Planet p : Planet.values()) {
            System.out.printf("    %-8s: %.2f N%n", p, p.surfaceWeight(earthWeight));
        }
        System.out.println();

        // ── Enum implementing interface ──────────────────────────────────
        System.out.println("━━━ PART 3: Enums with Interfaces ━━━");
        System.out.println();
        for (Season s : Season.values()) {
            System.out.println("  " + s + ": " + s.describe());
        }
        System.out.println();

        // ── Key facts ────────────────────────────────────────────────────
        System.out.println("━━━ Key Facts About Enums ━━━");
        System.out.println();
        System.out.println("  ✅ Type-safe — compiler catches invalid values");
        System.out.println("  ✅ Can have fields, methods, constructors, interfaces");
        System.out.println("  ✅ Can be used in switch statements");
        System.out.println("  ✅ Implicitly final and static");
        System.out.println("  ✅ All enums extend java.lang.Enum (can't extend anything else)");
        System.out.println("  ✅ Compare with == (not .equals()) — they're singletons!");
        System.out.println("  ✅ Thread-safe by default");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: D_StaticKeyword.java");
    }
}
