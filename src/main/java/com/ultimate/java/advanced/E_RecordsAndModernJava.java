package com.ultimate.java.advanced;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                 RECORDS & MODERN JAVA FEATURES                             ║
 * ║         Java 14-21 — Sealed Classes, Pattern Matching & More              ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ This lesson covers the most impactful MODERN Java features:              │
 * │                                                                           │
 * │  1. Records (Java 14)         → Immutable data carriers                  │
 * │  2. Sealed Classes (Java 17)  → Controlled inheritance                   │
 * │  3. Pattern Matching (Java 16-21) → Smart type checks                   │
 * │  4. Text Blocks (Java 15)     → Multi-line strings                       │
 * │  5. Switch Expressions (Java 14) → Modern switch                        │
 * │  6. Date/Time API (Java 8)    → Modern date handling                     │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class E_RecordsAndModernJava {

    // =========================================================================
    // PART 1: RECORDS (Java 14+)
    // =========================================================================
    // A record is an IMMUTABLE data carrier — just holds data!
    // Java auto-generates: constructor, getters, equals(), hashCode(), toString()
    //
    // Before records (40+ lines of boilerplate!):
    //   class Point { int x, y; Point(int x, int y){...} getX(){...} getY(){...}
    //                 equals(){...} hashCode(){...} toString(){...} }
    //
    // With records (1 line!):
    record Point(int x, int y) {}

    record Person(String name, int age, String email) {
        // Compact constructor — for VALIDATION
        Person {
            if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required!");
            if (age < 0 || age > 150) throw new IllegalArgumentException("Invalid age: " + age);
        }

        // Custom methods ARE allowed!
        boolean isAdult() { return age >= 18; }

        // Static factory method
        static Person unknown() { return new Person("Unknown", 0, "none"); }
    }

    record Temperature(double celsius) {
        Temperature {
            if (celsius < -273.15) throw new IllegalArgumentException("Below absolute zero!");
        }
        double fahrenheit() { return celsius * 9.0 / 5.0 + 32; }
        double kelvin() { return celsius + 273.15; }
    }

    // =========================================================================
    // PART 2: SEALED CLASSES (Java 17+)
    // =========================================================================
    // sealed = you declare EXACTLY which classes can extend it.
    // No unauthorized subclasses!

    sealed interface Shape permits CircleShape, RectangleShape, TriangleShape {}

    record CircleShape(double radius) implements Shape {
        double area() { return Math.PI * radius * radius; }
    }
    record RectangleShape(double w, double h) implements Shape {
        double area() { return w * h; }
    }
    record TriangleShape(double base, double height) implements Shape {
        double area() { return 0.5 * base * height; }
    }

    // This would be a COMPILE ERROR:
    // record HexagonShape() implements Shape {}  ← Not in permits list!

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 29: Records & Modern Java Features         ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // ── Records ──────────────────────────────────────────────────────
        System.out.println("━━━ PART 1: Records ━━━");
        System.out.println();

        Point p1 = new Point(3, 4);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 6);

        System.out.println("  Point p1 = " + p1);  // Auto-generated toString!
        System.out.println("  p1.x() = " + p1.x() + ", p1.y() = " + p1.y()); // Getters (no 'get' prefix!)
        System.out.println("  p1.equals(p2) = " + p1.equals(p2) + "  ← same values = equal!");
        System.out.println("  p1.equals(p3) = " + p1.equals(p3));
        System.out.println("  p1.hashCode() = " + p1.hashCode());
        // p1.x = 10; ← ❌ COMPILE ERROR! Records are immutable!
        System.out.println();

        Person alice = new Person("Alice", 25, "alice@email.com");
        System.out.println("  Person: " + alice);
        System.out.println("  isAdult: " + alice.isAdult());

        try {
            new Person("", 25, "x"); // Validation in compact constructor!
        } catch (IllegalArgumentException e) {
            System.out.println("  Validation: " + e.getMessage());
        }
        System.out.println();

        Temperature temp = new Temperature(37.5);
        System.out.printf("  %.1f°C = %.1f°F = %.2fK%n", temp.celsius(), temp.fahrenheit(), temp.kelvin());
        System.out.println();

        System.out.println("  Records give you for FREE:");
        System.out.println("    ✅ Constructor, getters, equals, hashCode, toString");
        System.out.println("    ✅ Immutability (fields are final)");
        System.out.println("    ✅ Less boilerplate than POJOs");
        System.out.println("    ❌ Cannot extend other classes (but can implement interfaces)");
        System.out.println("    ❌ Cannot have mutable instance fields");
        System.out.println();

        // ── Sealed Classes ───────────────────────────────────────────────
        System.out.println("━━━ PART 2: Sealed Classes ━━━");
        System.out.println();

        List<Shape> shapes = List.of(
            new CircleShape(5),
            new RectangleShape(4, 6),
            new TriangleShape(3, 8)
        );

        for (Shape shape : shapes) {
            String info = switch (shape) {
                case CircleShape c -> String.format("Circle (r=%.1f, area=%.2f)", c.radius(), c.area());
                case RectangleShape r -> String.format("Rectangle (%.1fx%.1f, area=%.2f)", r.w(), r.h(), r.area());
                case TriangleShape t -> String.format("Triangle (b=%.1f, h=%.1f, area=%.2f)", t.base(), t.height(), t.area());
            };
            // ↑ No 'default' needed — compiler knows all cases are covered (sealed!)
            System.out.println("  " + info);
        }
        System.out.println();

        // ── Pattern Matching for instanceof (Java 16+) ───────────────────
        System.out.println("━━━ PART 3: Pattern Matching ━━━");
        System.out.println();

        Object[] items = {"Hello", 42, 3.14, new Point(1, 2), List.of("A", "B"), null};

        for (Object item : items) {
            // OLD way:
            //   if (item instanceof String) { String s = (String) item; ... }

            // NEW way (Java 16+): instanceof with variable binding
            String desc;
            if (item instanceof String s && s.length() > 3) {
                desc = "String: \"" + s + "\" (long)";
            } else if (item instanceof Integer n) {
                desc = "Integer: " + n + " (even: " + (n % 2 == 0) + ")";
            } else if (item instanceof Double d) {
                desc = "Double: " + d;
            } else if (item instanceof Point(int x, int y)) {  // Record deconstruction! (Java 21)
                desc = "Point: x=" + x + ", y=" + y;
            } else if (item == null) {
                desc = "null!";
            } else {
                desc = "Other: " + item.getClass().getSimpleName();
            }
            System.out.println("    " + desc);
        }
        System.out.println();

        // ── Text Blocks (Java 15+) ───────────────────────────────────────
        System.out.println("━━━ PART 4: Text Blocks ━━━");
        System.out.println();

        // OLD way (nightmare):
        // String json = "{\n  \"name\": \"Alice\",\n  \"age\": 25\n}";

        // NEW way:
        String json = """
                {
                    "name": "Alice",
                    "age": 25,
                    "email": "alice@email.com"
                }
                """;
        System.out.println("  JSON text block:");
        System.out.println(json);

        String sql = """
                SELECT name, age
                FROM users
                WHERE age > 18
                ORDER BY name
                """;
        System.out.println("  SQL text block:");
        System.out.println(sql);

        // ── Switch Expressions (Java 14+) ────────────────────────────────
        System.out.println("━━━ PART 5: Modern Switch Expressions ━━━");
        System.out.println();

        // Old switch: statements, break, fall-through
        // New switch: expressions, arrow syntax, return values!

        int dayNum = 3;
        String dayName = switch (dayNum) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6, 7 -> "Weekend!";
            default -> "Invalid";
        };
        System.out.println("  Day " + dayNum + " = " + dayName);

        // Switch with blocks (use yield to return)
        String category = switch (dayNum) {
            case 1, 2, 3, 4, 5 -> {
                System.out.println("    (Computing weekday category...)");
                yield "Weekday";
            }
            case 6, 7 -> "Weekend";
            default -> "Unknown";
        };
        System.out.println("  Category: " + category);
        System.out.println();

        // ── Date/Time API (java.time) ────────────────────────────────────
        System.out.println("━━━ PART 6: Modern Date/Time API ━━━");
        System.out.println();

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();
        ZonedDateTime zoned = ZonedDateTime.now();

        System.out.println("  LocalDate.now()     = " + today);
        System.out.println("  LocalTime.now()     = " + now);
        System.out.println("  LocalDateTime.now() = " + dateTime);
        System.out.println("  ZonedDateTime.now() = " + zoned);
        System.out.println();

        // Creating specific dates
        LocalDate birthday = LocalDate.of(2000, 6, 15);
        System.out.println("  Birthday: " + birthday);
        System.out.println("  Day of week: " + birthday.getDayOfWeek());

        // Date math
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate lastMonth = today.minusMonths(1);
        Period age = Period.between(birthday, today);
        System.out.println("  Next week: " + nextWeek);
        System.out.println("  Last month: " + lastMonth);
        System.out.printf("  Age: %d years, %d months, %d days%n", age.getYears(), age.getMonths(), age.getDays());

        // Formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        System.out.println("  Formatted: " + dateTime.format(formatter));
        System.out.println();

        // =====================================================================
        // SUMMARY
        // =====================================================================
        System.out.println("━━━ Modern Java Features Summary ━━━");
        System.out.println();
        System.out.println("  ┌──────────────────────────┬────────┬──────────────────────────┐");
        System.out.println("  │ Feature                  │ Since  │ What It Does             │");
        System.out.println("  ├──────────────────────────┼────────┼──────────────────────────┤");
        System.out.println("  │ var (local inference)     │ Java 10│ Infer variable types     │");
        System.out.println("  │ Switch Expressions        │ Java 14│ Switch returns values    │");
        System.out.println("  │ Records                   │ Java 14│ Immutable data classes   │");
        System.out.println("  │ Text Blocks               │ Java 15│ Multi-line strings       │");
        System.out.println("  │ Pattern Matching          │ Java 16│ Smart instanceof        │");
        System.out.println("  │ Sealed Classes            │ Java 17│ Controlled inheritance   │");
        System.out.println("  │ Record Patterns           │ Java 21│ Deconstruct records     │");
        System.out.println("  └──────────────────────────┴────────┴──────────────────────────┘");
        System.out.println();

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║  🎉🎉🎉 CONGRATULATIONS! 🎉🎉🎉                  ║");
        System.out.println("║                                                      ║");
        System.out.println("║  You've completed ALL 29 lessons of the             ║");
        System.out.println("║  Ultimate Java Tutorial!                             ║");
        System.out.println("║                                                      ║");
        System.out.println("║  Modules completed:                                  ║");
        System.out.println("║    ✅ Module 1: Basics (7 lessons)                   ║");
        System.out.println("║    ✅ Module 2: OOP (7 lessons)                      ║");
        System.out.println("║    ✅ Module 3: Core Concepts (5 lessons)            ║");
        System.out.println("║    ✅ Module 4: Collections (5 lessons)              ║");
        System.out.println("║    ✅ Module 5: Advanced Core (5 lessons)            ║");
        System.out.println("║                                                      ║");
        System.out.println("║  Keep practicing, keep building, keep learning! 🚀  ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
}
