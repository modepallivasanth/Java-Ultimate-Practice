package com.ultimate.java.core;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                       STATIC KEYWORD IN JAVA                               ║
 * ║           Belongs to the CLASS, Not to Any Object                          ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS 'static'?                                                         │
 * │                                                                           │
 * │  'static' means "belongs to the CLASS itself, not to any specific object."│
 * │                                                                           │
 * │  Think: A CLASSROOM (class) vs STUDENTS (objects).                        │
 * │    • Each student has their OWN name, age → instance fields              │
 * │    • The classroom has ONE shared whiteboard → static field              │
 * │    • If one student writes on the board, ALL students see it!            │
 * │                                                                           │
 * │  Without 'static': Every object gets its own copy of the variable.       │
 * │  With 'static': ALL objects share ONE copy of the variable.              │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class D_StaticKeyword {

    // ── Example class ────────────────────────────────────────────────────
    static class Student {
        // STATIC field — shared by ALL students (ONE copy in the CLASS)
        static int totalStudents = 0;
        static String schoolName = "Java Academy";

        // INSTANCE fields — each student has their OWN copy
        String name;
        int id;

        Student(String name) {
            this.name = name;
            totalStudents++;        // Increments the SHARED counter
            this.id = totalStudents; // Each student gets a unique ID
        }

        // STATIC method — belongs to the class, no 'this' keyword available!
        static int getTotalStudents() {
            // Can access static fields ✅
            // Cannot access 'this' or instance fields ❌
            return totalStudents;
        }

        // INSTANCE method — belongs to an object, has 'this'
        void display() {
            System.out.println("    #" + id + " " + name + " at " + schoolName);
        }
    }

    // ── Static block ─────────────────────────────────────────────────────
    static class Config {
        static String appName;
        static String version;

        // Static initializer block — runs ONCE when the class is first loaded
        // Useful for complex initialization of static fields.
        static {
            System.out.println("    🔧 Static block executing (class loading)...");
            appName = "Java Ultimate Tutorial";
            version = "1.0.0";
        }
    }

    // ── Static inner class ───────────────────────────────────────────────
    static class MathHelper {
        static final double PI = 3.14159265358979;
        static final double E = 2.71828182845905;

        static double circleArea(double r) { return PI * r * r; }
        static int factorial(int n) {
            int result = 1;
            for (int i = 2; i <= n; i++) result *= i;
            return result;
        }
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 18: Static Keyword                        ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // ── Static fields (shared across ALL objects) ────────────────────
        System.out.println("━━━ PART 1: Static Fields ━━━");
        System.out.println();

        System.out.println("  Before creating students: Total = " + Student.getTotalStudents());
        Student s1 = new Student("Alice");
        Student s2 = new Student("Bob");
        Student s3 = new Student("Charlie");

        s1.display();
        s2.display();
        s3.display();
        System.out.println("  Total students (via class): " + Student.totalStudents);
        System.out.println("  → ALL three students share the SAME totalStudents counter!");
        System.out.println();

        // Change school name via class → affects ALL students!
        Student.schoolName = "Ultimate Java Academy";
        System.out.println("  After changing Student.schoolName:");
        s1.display();
        s2.display();
        System.out.println("  → ALL students now show the new school name!");
        System.out.println();

        // ── Static methods ───────────────────────────────────────────────
        System.out.println("━━━ PART 2: Static Methods ━━━");
        System.out.println();

        // Call static methods on the CLASS, not an object
        System.out.println("  Student.getTotalStudents() = " + Student.getTotalStudents());
        System.out.println("  MathHelper.circleArea(5) = " + String.format("%.2f", MathHelper.circleArea(5)));
        System.out.println("  MathHelper.factorial(6) = " + MathHelper.factorial(6));
        System.out.println();
        System.out.println("  📋 Static method rules:");
        System.out.println("     • Call via ClassName.method() (not object.method())");
        System.out.println("     • Cannot use 'this' (no object context!)");
        System.out.println("     • Cannot access instance fields/methods directly");
        System.out.println("     • CAN access other static members");
        System.out.println("     • main() is static — that's why you need 'static' on helper methods!");
        System.out.println();

        // ── Static block ─────────────────────────────────────────────────
        System.out.println("━━━ PART 3: Static Initializer Block ━━━");
        System.out.println();
        System.out.println("  Accessing Config class for the first time:");
        System.out.println("  App: " + Config.appName + " v" + Config.version);
        System.out.println("  (The static block ran when the class was first loaded!)");
        System.out.println();

        // ── Summary ──────────────────────────────────────────────────────
        System.out.println("━━━ Static vs Instance Summary ━━━");
        System.out.println();
        System.out.println("  ┌──────────────────┬─────────────────┬────────────────────┐");
        System.out.println("  │                  │ STATIC          │ INSTANCE           │");
        System.out.println("  ├──────────────────┼─────────────────┼────────────────────┤");
        System.out.println("  │ Belongs to       │ Class           │ Object             │");
        System.out.println("  │ Copies           │ ONE (shared)    │ One per object     │");
        System.out.println("  │ Access           │ ClassName.x     │ object.x           │");
        System.out.println("  │ 'this' keyword   │ ❌ Not available│ ✅ Available        │");
        System.out.println("  │ Loaded when      │ Class loads     │ Object created     │");
        System.out.println("  │ Use case         │ Utilities,      │ Object state,      │");
        System.out.println("  │                  │ counters, const │ behavior            │");
        System.out.println("  └──────────────────┴─────────────────┴────────────────────┘");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: E_FinalKeyword.java");
    }
}
