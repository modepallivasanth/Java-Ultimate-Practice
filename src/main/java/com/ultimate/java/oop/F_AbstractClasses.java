package com.ultimate.java.oop;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                     ABSTRACT CLASSES IN JAVA                               ║
 * ║          The "Incomplete Blueprint" — Forcing Children to Finish           ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │                                                                           │
 * │  • D_Inheritance.java (extends, super, @Override)                        │
 * │  • E_Polymorphism.java (runtime polymorphism, upcasting)                 │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS AN ABSTRACT CLASS?                                                │
 * │                                                                           │
 * │  An abstract class is an INCOMPLETE blueprint that:                       │
 * │    • CAN have completed methods (normal methods with body)               │
 * │    • CAN have incomplete methods (abstract — no body, just signature)    │
 * │    • CANNOT be instantiated (you can't do 'new AbstractClass()')         │
 * │                                                                           │
 * │  Think of it like a FILL-IN-THE-BLANK worksheet:                         │
 * │                                                                           │
 * │    "Recipe for _____________ (fill in dish name)"                         │
 * │    Step 1: Preheat oven to 350°F     ← COMPLETED (same for all)         │
 * │    Step 2: Mix ________________      ← BLANK (each recipe is different) │
 * │    Step 3: Bake for ___ minutes      ← BLANK (each recipe is different) │
 * │    Step 4: Let cool for 10 minutes   ← COMPLETED (same for all)         │
 * │                                                                           │
 * │  The worksheet PROVIDES the common steps and FORCES you to fill in       │
 * │  the blanks. You can't submit the worksheet with blanks!                 │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY DO WE NEED ABSTRACT CLASSES?                                         │
 * │                                                                           │
 * │  Problem: In D_Inheritance, our base Shape class had:                    │
 * │    double getArea() { return 0; }  ← This is WRONG for the base class!  │
 * │                                                                           │
 * │  A "Shape" with area 0 makes no sense. Shape should NOT be able to      │
 * │  calculate area because it's too generic — only specific shapes          │
 * │  (Circle, Rectangle) know their area formula.                            │
 * │                                                                           │
 * │  Abstract class solves this:                                              │
 * │    abstract double getArea();  ← No body! Forces EVERY child to         │
 * │                                   implement its own formula.              │
 * │    You can't create new Shape() — only new Circle(), new Rectangle().   │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class F_AbstractClasses {

    // =========================================================================
    // THE ABSTRACT CLASS
    // =========================================================================
    abstract static class Shape {
        // Normal fields — abstract classes CAN have fields!
        protected String name;
        protected String color;

        // Normal constructor — abstract classes CAN have constructors!
        // (Used by children via super())
        Shape(String name, String color) {
            this.name = name;
            this.color = color;
        }

        // ─── ABSTRACT METHODS (no body — MUST be implemented by children) ──
        // Think: "I don't know HOW to calculate area for a generic shape.
        //         Each specific shape must tell me."
        abstract double getArea();

        abstract double getPerimeter();

        abstract void draw();

        // ─── CONCRETE METHODS (have a body — inherited by children) ────────
        // Think: "This logic is the SAME for all shapes, no need to rewrite."
        void displayInfo() {
            System.out.printf("    %s %s | Area: %.2f | Perimeter: %.2f%n",
                    color, name, getArea(), getPerimeter());
            // ↑ Notice: it calls getArea() which is abstract!
            // At runtime, the CHILD's implementation runs. Polymorphism!
        }

        boolean isLargerThan(Shape other) {
            return this.getArea() > other.getArea();
        }

        @Override
        public String toString() {
            return String.format("%s %s (area=%.2f)", color, name, getArea());
        }
    }

    // =========================================================================
    // CONCRETE CLASSES (Complete the blanks!)
    // =========================================================================

    static class Circle extends Shape {
        private double radius;

        Circle(String color, double radius) {
            super("Circle", color);
            this.radius = radius;
        }

        @Override
        double getArea() {
            return Math.PI * radius * radius;
        }

        @Override
        double getPerimeter() {
            return 2 * Math.PI * radius;
        }

        @Override
        void draw() {
            System.out.println("    ⭕ Drawing " + color + " circle (r=" + radius + ")");
        }
    }

    static class Rectangle extends Shape {
        private double width, height;

        Rectangle(String color, double width, double height) {
            super("Rectangle", color);
            this.width = width;
            this.height = height;
        }

        @Override
        double getArea() {
            return width * height;
        }

        @Override
        double getPerimeter() {
            return 2 * (width + height);
        }

        @Override
        void draw() {
            System.out.println("    🟦 Drawing " + color + " rectangle (" + width + "×" + height + ")");
        }
    }

    static class Triangle extends Shape {
        private double a, b, c; // three sides
        private double height;  // height for area calculation

        Triangle(String color, double a, double b, double c, double height) {
            super("Triangle", color);
            this.a = a;
            this.b = b;
            this.c = c;
            this.height = height;
        }

        @Override
        double getArea() {
            return 0.5 * a * height;  // Using base (a) × height
        }

        @Override
        double getPerimeter() {
            return a + b + c;
        }

        @Override
        void draw() {
            System.out.println("    🔺 Drawing " + color + " triangle (sides: " + a + ", " + b + ", " + c + ")");
        }
    }

    // =========================================================================
    // EXAMPLE 2: Abstract class with a Template Method Pattern
    // =========================================================================
    // This is a DESIGN PATTERN where the abstract class defines the SKELETON
    // of an algorithm, and children fill in the specific steps.

    abstract static class Beverage {
        // Template method — defines the ORDER of steps (final = can't override!)
        final void prepare() {
            System.out.println("    ☕ Preparing " + getName() + ":");
            boilWater();          // Common step
            brew();               // Abstract — each beverage brews differently
            pourInCup();          // Common step
            if (wantsCondiments()) {
                addCondiments();  // Abstract — each beverage adds different things
            }
            System.out.println("    ✅ " + getName() + " is ready!");
        }

        // Common steps (concrete — same for all)
        private void boilWater() {
            System.out.println("      Boiling water...");
        }

        private void pourInCup() {
            System.out.println("      Pouring into cup...");
        }

        // Steps that vary (abstract — children must implement)
        abstract void brew();
        abstract void addCondiments();
        abstract String getName();

        // Hook method — children CAN override but don't HAVE TO
        boolean wantsCondiments() {
            return true;  // default: yes
        }
    }

    static class Tea extends Beverage {
        @Override void brew() { System.out.println("      Steeping tea bag..."); }
        @Override void addCondiments() { System.out.println("      Adding honey and lemon..."); }
        @Override String getName() { return "Tea"; }
    }

    static class Coffee extends Beverage {
        @Override void brew() { System.out.println("      Dripping coffee through filter..."); }
        @Override void addCondiments() { System.out.println("      Adding sugar and cream..."); }
        @Override String getName() { return "Coffee"; }
    }

    static class BlackCoffee extends Beverage {
        @Override void brew() { System.out.println("      Dripping espresso..."); }
        @Override void addCondiments() { /* not called */ }
        @Override String getName() { return "Black Coffee"; }
        @Override boolean wantsCondiments() { return false; }  // Override hook!
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 13: Abstract Classes                       ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: YOU CANNOT INSTANTIATE ABSTRACT CLASSES!
        // =====================================================================
        System.out.println("━━━ PART 1: Abstract Classes Cannot Be Instantiated ━━━");
        System.out.println();

        // Shape shape = new Shape("Test", "Red");  // ❌ COMPILE ERROR!
        // "Cannot instantiate the type Shape"
        System.out.println("  Shape s = new Shape(\"Test\", \"Red\");  ← ❌ COMPILE ERROR!");
        System.out.println("  You CANNOT create an instance of an abstract class.");
        System.out.println("  You must use a CONCRETE (non-abstract) child class.");
        System.out.println();

        // But you CAN use Shape as a reference type (polymorphism!):
        Shape circle = new Circle("Red", 5);
        Shape rect = new Rectangle("Blue", 4, 6);
        Shape triangle = new Triangle("Green", 3, 4, 5, 4);
        System.out.println("  Shape circle = new Circle(\"Red\", 5);  ← ✅ This works!");
        System.out.println("  The reference is Shape, the object is Circle.");
        System.out.println();

        // =====================================================================
        // PART 2: ABSTRACT CLASSES IN ACTION
        // =====================================================================
        System.out.println("━━━ PART 2: Abstract Classes in Action ━━━");
        System.out.println();

        Shape[] shapes = {circle, rect, triangle};

        for (Shape s : shapes) {
            s.draw();          // Each shape draws itself (polymorphism!)
            s.displayInfo();   // Concrete method calls abstract getArea()!
        }
        System.out.println();

        // isLargerThan() — concrete method using abstract getArea()
        System.out.println("  Is circle larger than rectangle? " + circle.isLargerThan(rect));
        System.out.println("  Circle area: " + String.format("%.2f", circle.getArea())
                + ", Rectangle area: " + String.format("%.2f", rect.getArea()));
        System.out.println();

        // =====================================================================
        // PART 3: TEMPLATE METHOD PATTERN
        // =====================================================================
        System.out.println("━━━ PART 3: Template Method Pattern ━━━");
        System.out.println();

        // Same prepare() algorithm, different brewing and condiments!
        new Tea().prepare();
        System.out.println();
        new Coffee().prepare();
        System.out.println();
        new BlackCoffee().prepare();
        System.out.println();

        // =====================================================================
        // PART 4: ABSTRACT CLASS RULES
        // =====================================================================
        System.out.println("━━━ PART 4: Abstract Class Rules ━━━");
        System.out.println();
        System.out.println("  ✅ CAN have:");
        System.out.println("     • Constructors (called via super())");
        System.out.println("     • Fields (instance and static)");
        System.out.println("     • Concrete methods (with body)");
        System.out.println("     • Abstract methods (no body)");
        System.out.println("     • Static methods");
        System.out.println("     • Any access modifier on methods/fields");
        System.out.println();
        System.out.println("  ❌ CANNOT:");
        System.out.println("     • Be instantiated (no 'new AbstractClass()')");
        System.out.println("     • Have abstract constructors");
        System.out.println("     • Have abstract static methods");
        System.out.println("     • Have abstract final methods (contradiction!)");
        System.out.println();
        System.out.println("  📝 CHILD CLASS RULES:");
        System.out.println("     • Must implement ALL abstract methods, OR");
        System.out.println("     • Be declared abstract itself");
        System.out.println();

        // =====================================================================
        // PART 5: ABSTRACT VS CONCRETE
        // =====================================================================
        System.out.println("━━━ PART 5: When to Use Abstract Classes? ━━━");
        System.out.println();
        System.out.println("  Use abstract classes when:");
        System.out.println("  ✅ You have shared code among related classes");
        System.out.println("  ✅ Some methods have a common implementation");
        System.out.println("  ✅ Some methods MUST be customized by each child");
        System.out.println("  ✅ You want to prevent direct instantiation of the parent");
        System.out.println();
        System.out.println("  Don't use abstract classes when:");
        System.out.println("  ❌ All methods have implementations (use a regular class)");
        System.out.println("  ❌ There's no shared code (use an Interface instead!)");
        System.out.println("  ❌ You need multiple inheritance (use Interfaces!)");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Trying to instantiate an abstract class:");
        System.out.println("   new Shape(\"...\", \"...\")  ← ❌ Compile error!");
        System.out.println();
        System.out.println("2. Child forgets to implement an abstract method:");
        System.out.println("   class Square extends Shape { }  ← ❌ Must implement getArea() etc!");
        System.out.println("   Fix: implement ALL abstract methods, or make Square abstract too.");
        System.out.println();
        System.out.println("3. Confusing abstract class with interface:");
        System.out.println("   Abstract class: partial implementation, single inheritance");
        System.out.println("   Interface: pure contract (mostly), multiple inheritance");
        System.out.println("   → Next lesson covers interfaces!");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("1. Create abstract class 'Employee' with:");
        System.out.println("   - Abstract: calculateSalary()");
        System.out.println("   - Concrete: displayPaySlip() that calls calculateSalary()");
        System.out.println("   Children: FullTimeEmployee, PartTimeEmployee, Contractor");
        System.out.println("   Each calculates salary differently.");
        System.out.println();
        System.out.println("2. Create an abstract 'Game' template:");
        System.out.println("   Template method: play() → initialize(), start(), end()");
        System.out.println("   Children: Chess, Cricket, Football");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: G_Interfaces.java");
    }
}
