package com.ultimate.java.oop;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                       POLYMORPHISM IN JAVA                                 ║
 * ║         One Interface, Many Implementations — The Power of OOP            ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │                                                                           │
 * │  • A_ClassesAndObjects.java (classes, objects)                            │
 * │  • B_Constructors.java (constructors, super)                             │
 * │  • C_Encapsulation.java (access modifiers)                               │
 * │  • D_Inheritance.java (extends, super, @Override, IS-A) ← CRITICAL!     │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS POLYMORPHISM?                                                     │
 * │                                                                           │
 * │  Polymorphism = "many forms" (from Greek: poly=many, morph=form)          │
 * │                                                                           │
 * │  One action, different behaviors depending on WHO performs it.            │
 * │                                                                           │
 * │  Real-world example: The word "OPEN"                                      │
 * │    • "Open" a door   → push/pull                                          │
 * │    • "Open" a book   → spread pages                                       │
 * │    • "Open" a bottle → twist cap                                          │
 * │    • "Open" a file   → double-click                                       │
 * │                                                                           │
 * │  Same word, DIFFERENT ACTION depending on the OBJECT.                     │
 * │  That's polymorphism!                                                     │
 * │                                                                           │
 * │  In Java:                                                                 │
 * │    animal.makeSound();                                                    │
 * │    If animal is a Dog  → "Woof!"                                          │
 * │    If animal is a Cat  → "Meow!"                                          │
 * │    If animal is a Duck → "Quack!"                                         │
 * │    SAME method call, DIFFERENT behavior!                                  │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ TWO TYPES OF POLYMORPHISM:                                                │
 * │                                                                           │
 * │  1. COMPILE-TIME (Static) polymorphism → METHOD OVERLOADING              │
 * │     Same method name, different parameters.                               │
 * │     Java decides WHICH to call at compile time.                           │
 * │                                                                           │
 * │  2. RUNTIME (Dynamic) polymorphism → METHOD OVERRIDING ★                │
 * │     Parent reference, child object.                                       │
 * │     Java decides WHICH to call at runtime. THIS is the star!             │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class E_Polymorphism {

    // =========================================================================
    // CLASS HIERARCHY FOR DEMOS
    // =========================================================================

    static class Shape {
        String name;
        String color;

        Shape(String name, String color) {
            this.name = name;
            this.color = color;
        }

        double getArea() {
            return 0;  // Base implementation — children will override
        }

        void draw() {
            System.out.println("    🎨 Drawing a " + color + " " + name);
        }

        @Override
        public String toString() {
            return color + " " + name + " (area: " + String.format("%.2f", getArea()) + ")";
        }
    }

    static class Circle extends Shape {
        double radius;

        Circle(String color, double radius) {
            super("Circle", color);
            this.radius = radius;
        }

        @Override
        double getArea() {
            return Math.PI * radius * radius;
        }

        @Override
        void draw() {
            System.out.println("    ⭕ Drawing a " + color + " circle with radius " + radius);
        }
    }

    static class Rectangle extends Shape {
        double width, height;

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
        void draw() {
            System.out.println("    🟦 Drawing a " + color + " rectangle " + width + "×" + height);
        }
    }

    static class Triangle extends Shape {
        double base, height;

        Triangle(String color, double base, double height) {
            super("Triangle", color);
            this.base = base;
            this.height = height;
        }

        @Override
        double getArea() {
            return 0.5 * base * height;
        }

        @Override
        void draw() {
            System.out.println("    🔺 Drawing a " + color + " triangle base=" + base + " height=" + height);
        }
    }

    // =========================================================================
    // OVERLOADING EXAMPLE (Compile-time polymorphism)
    // =========================================================================
    static class Calculator {
        // Same name "add", different parameter types/counts
        static int add(int a, int b) {
            return a + b;
        }

        static double add(double a, double b) {
            return a + b;
        }

        static int add(int a, int b, int c) {
            return a + b + c;
        }

        static String add(String a, String b) {
            return a + b;
        }
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 12: Polymorphism                           ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: COMPILE-TIME POLYMORPHISM (Method Overloading)
        // =====================================================================
        System.out.println("━━━ PART 1: Compile-Time Polymorphism (Overloading) ━━━");
        System.out.println();

        System.out.println("  Calculator.add(5, 3)        = " + Calculator.add(5, 3));
        System.out.println("  Calculator.add(5.5, 3.2)    = " + Calculator.add(5.5, 3.2));
        System.out.println("  Calculator.add(1, 2, 3)     = " + Calculator.add(1, 2, 3));
        System.out.println("  Calculator.add(\"Hi\", \" World\") = " + Calculator.add("Hi", " World"));
        System.out.println();
        System.out.println("  Java picks the right add() at COMPILE time based on arguments.");
        System.out.println("  This is the simpler form of polymorphism.");
        System.out.println();

        // =====================================================================
        // PART 2: RUNTIME POLYMORPHISM (The Star of the Show!)
        // =====================================================================
        System.out.println("━━━ PART 2: Runtime Polymorphism (The Big One!) ━━━");
        System.out.println();

        // THE KEY CONCEPT:
        // A parent reference can hold a child object!
        //   Shape myShape = new Circle("Red", 5);
        //     ↑                    ↑
        //   parent type       child object
        //
        // This is called UPCASTING and it's automatic.
        // Think: Every Dog IS-A Animal, so an Animal reference can hold a Dog.

        Shape shape1 = new Circle("Red", 5);         // Circle stored as Shape!
        Shape shape2 = new Rectangle("Blue", 4, 6);  // Rectangle stored as Shape!
        Shape shape3 = new Triangle("Green", 3, 8);  // Triangle stored as Shape!

        // Now the magic — same method call, different behavior!
        System.out.println("  Same method 'draw()', different behavior:");
        shape1.draw();  // Calls Circle's draw()
        shape2.draw();  // Calls Rectangle's draw()
        shape3.draw();  // Calls Triangle's draw()
        System.out.println();

        System.out.println("  Same method 'getArea()', different calculations:");
        System.out.printf("    shape1.getArea() = %.2f  (π × 5²)%n", shape1.getArea());
        System.out.printf("    shape2.getArea() = %.2f  (4 × 6)%n", shape2.getArea());
        System.out.printf("    shape3.getArea() = %.2f  (0.5 × 3 × 8)%n", shape3.getArea());
        System.out.println();

        System.out.println("  🧠 HOW does Java know which draw() to call?");
        System.out.println("     At RUNTIME, Java checks the ACTUAL object type, not the reference type.");
        System.out.println("     shape1 is declared as Shape, but the OBJECT is Circle.");
        System.out.println("     So Java calls Circle's draw(). This is called DYNAMIC DISPATCH.");
        System.out.println();

        // =====================================================================
        // PART 3: THE POWER — Arrays/Collections of different types!
        // =====================================================================
        System.out.println("━━━ PART 3: The Power — Treating Different Types Uniformly ━━━");
        System.out.println();

        // THIS is where polymorphism truly shines!
        // You can put different shapes in ONE array and process them uniformly.

        Shape[] shapes = {
            new Circle("Red", 5),
            new Rectangle("Blue", 4, 6),
            new Triangle("Green", 3, 8),
            new Circle("Yellow", 3),
            new Rectangle("Purple", 10, 2)
        };

        System.out.println("  Processing " + shapes.length + " different shapes with ONE loop:");
        double totalArea = 0;
        for (Shape s : shapes) {
            s.draw();  // Each shape draws itself differently!
            totalArea += s.getArea();
        }
        System.out.printf("    Total area of all shapes: %.2f%n", totalArea);
        System.out.println();

        System.out.println("  🎯 This is POLYMORPHISM in action!");
        System.out.println("     ONE loop processes Circle, Rectangle, AND Triangle.");
        System.out.println("     Each 'knows' how to draw itself and calculate its own area.");
        System.out.println("     Adding a new shape (Hexagon) requires NO changes to this loop!");
        System.out.println();

        // =====================================================================
        // PART 4: UPCASTING AND DOWNCASTING
        // =====================================================================
        System.out.println("━━━ PART 4: Upcasting and Downcasting ━━━");
        System.out.println();

        // UPCASTING: Child → Parent (automatic, always safe)
        // Think: "A Dog IS-A Animal" — always true, no risk.
        Circle circle = new Circle("Gold", 10);
        Shape upcastedShape = circle;  // Upcasting — automatic!
        System.out.println("  Upcasting: Circle → Shape (automatic, safe)");
        System.out.println("  upcastedShape.getArea() = " + String.format("%.2f", upcastedShape.getArea()));
        // upcastedShape.radius → ❌ Compile error! Shape doesn't know about 'radius'
        System.out.println("  ⚠️ After upcasting, you can only call Shape's methods.");
        System.out.println("     upcastedShape.radius → compile error!");
        System.out.println();

        // DOWNCASTING: Parent → Child (manual, risky!)
        // Think: "Is this Animal actually a Dog?" — might not be true!
        // ALWAYS check with instanceof first!
        if (upcastedShape instanceof Circle c) {  // Pattern matching (Java 16+)
            System.out.println("  Downcasting: Shape → Circle (manual, after instanceof check)");
            System.out.println("  Now we can access radius: " + c.radius);
        }
        System.out.println();

        // What happens without instanceof?
        System.out.println("  ⚠️ DANGEROUS DOWNCASTING:");
        System.out.println("     Shape s = new Rectangle(\"Red\", 5, 3);");
        System.out.println("     Circle c = (Circle) s;  → 💥 ClassCastException at runtime!");
        System.out.println("     ALWAYS use instanceof before downcasting!");
        System.out.println();

        // instanceof with pattern matching (Java 16+) — cleaner!
        System.out.println("  Checking types in the shapes array:");
        for (Shape s : shapes) {
            if (s instanceof Circle c) {
                System.out.println("    Circle with radius " + c.radius);
            } else if (s instanceof Rectangle r) {
                System.out.println("    Rectangle " + r.width + "×" + r.height);
            } else if (s instanceof Triangle t) {
                System.out.println("    Triangle base=" + t.base + " height=" + t.height);
            }
        }
        System.out.println();

        // =====================================================================
        // PART 5: POLYMORPHISM WITH METHODS (Passing parent types)
        // =====================================================================
        System.out.println("━━━ PART 5: Polymorphism in Method Parameters ━━━");
        System.out.println();

        // Write ONE method that accepts the PARENT type, and it works
        // with ALL child types!
        System.out.println("  printShapeInfo(circle):");
        printShapeInfo(new Circle("Red", 7));
        System.out.println("  printShapeInfo(rectangle):");
        printShapeInfo(new Rectangle("Blue", 5, 3));
        System.out.println();

        System.out.println("  💡 One method handles ALL shapes — present AND future!");
        System.out.println("     If someone creates class Pentagon extends Shape,");
        System.out.println("     printShapeInfo() works with it WITHOUT changing the method!");
        System.out.println();

        // =====================================================================
        // PART 6: COMPILE-TIME VS RUNTIME COMPARISON
        // =====================================================================
        System.out.println("━━━ PART 6: Compile-Time vs Runtime Polymorphism ━━━");
        System.out.println();
        System.out.println("  ┌──────────────────────┬──────────────────────────────┐");
        System.out.println("  │  COMPILE-TIME         │  RUNTIME                     │");
        System.out.println("  ├──────────────────────┼──────────────────────────────┤");
        System.out.println("  │ Method Overloading    │ Method Overriding            │");
        System.out.println("  │ Same class            │ Different classes (parent→child)│");
        System.out.println("  │ Different parameters  │ Same parameters              │");
        System.out.println("  │ Decided at compile    │ Decided at runtime           │");
        System.out.println("  │ Also: 'static binding'│ Also: 'dynamic dispatch'     │");
        System.out.println("  │ Less powerful         │ More powerful ★              │");
        System.out.println("  └──────────────────────┴──────────────────────────────┘");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. ClassCastException from downcasting without instanceof:");
        System.out.println("   Shape s = new Rectangle(...);");
        System.out.println("   Circle c = (Circle) s;  → 💥 Runtime crash!");
        System.out.println("   Always check: if (s instanceof Circle c) { ... }");
        System.out.println();
        System.out.println("2. Confusing overloading with overriding:");
        System.out.println("   Overloading = same name, DIFFERENT params (same class)");
        System.out.println("   Overriding  = same name, SAME params (parent→child)");
        System.out.println();
        System.out.println("3. Thinking polymorphism works with static methods:");
        System.out.println("   Static methods belong to the CLASS, not the object.");
        System.out.println("   They use static binding (compile-time), not dynamic dispatch.");
        System.out.println();
        System.out.println("4. Accessing child-specific methods through parent reference:");
        System.out.println("   Shape s = new Circle(...);");
        System.out.println("   s.radius → ❌ Compile error! Must downcast first.");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("1. Create a class hierarchy: Vehicle → Car, Motorcycle, Truck");
        System.out.println("   Override: startEngine(), honk(), getFuelEfficiency()");
        System.out.println("   Create a Vehicle[] array and loop through polymorphically.");
        System.out.println();
        System.out.println("2. Create a 'PaymentProcessor' that accepts Payment (parent)");
        System.out.println("   Children: CreditCard, DebitCard, UPI, Cash");
        System.out.println("   Each overrides: processPayment(), getTransactionFee()");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: F_AbstractClasses.java");
    }

    // Helper method that demonstrates polymorphic parameters
    static void printShapeInfo(Shape shape) {
        System.out.println("    " + shape);
    }
}
