package com.ultimate.java.oop;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                        INTERFACES IN JAVA                                  ║
 * ║              Contracts, Multiple Inheritance & Modern Java Power           ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │                                                                           │
 * │  • D_Inheritance.java (extends, super, IS-A, single inheritance limit)   │
 * │  • E_Polymorphism.java (runtime polymorphism, upcasting)                 │
 * │  • F_AbstractClasses.java (abstract methods, partial implementation)     │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS AN INTERFACE?                                                     │
 * │                                                                           │
 * │  An interface is a CONTRACT — a promise that a class will provide         │
 * │  certain methods.                                                         │
 * │                                                                           │
 * │  Think: ELECTRICAL OUTLET STANDARD                                        │
 * │                                                                           │
 * │    The outlet (interface) says:                                           │
 * │      "I promise to provide 120V, 60Hz power through 3 prongs."           │
 * │                                                                           │
 * │    ANY device (class) that fits the standard can plug in:                 │
 * │      ✅ Laptop charger                                                    │
 * │      ✅ TV                                                                │
 * │      ✅ Toaster                                                           │
 * │      ✅ Phone charger                                                     │
 * │                                                                           │
 * │    The outlet doesn't care WHAT the device is — only that it follows     │
 * │    the contract (3 prongs, accepts 120V).                                │
 * │                                                                           │
 * │  KEY DIFFERENCE FROM ABSTRACT CLASS:                                      │
 * │    • Abstract class: IS-A relationship (Dog IS-A Animal)                 │
 * │    • Interface: CAN-DO relationship (Dog CAN swim, CAN fetch)            │
 * │    • A class can implement MULTIPLE interfaces! (multiple inheritance!)   │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY DO WE NEED INTERFACES?                                               │
 * │                                                                           │
 * │  1. MULTIPLE INHERITANCE:                                                │
 * │     Java allows only ONE parent class (extends) but MANY interfaces      │
 * │     class Dog extends Animal implements Swimmable, Trainable, Lovable    │
 * │                                                                           │
 * │  2. LOOSE COUPLING:                                                      │
 * │     Code depends on the INTERFACE, not the specific class.               │
 * │     You can swap implementations without changing the caller!            │
 * │                                                                           │
 * │  3. STANDARDIZATION:                                                     │
 * │     All classes that implement an interface GUARANTEE the same methods.   │
 * │     ArrayList, LinkedList, Stack → all implement List interface.          │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class G_Interfaces {

    // =========================================================================
    // PART 1: BASIC INTERFACES
    // =========================================================================

    // Declaring an interface — uses 'interface' keyword instead of 'class'
    interface Drawable {
        void draw();    // Abstract by default — no body needed!
        // All methods in an interface are implicitly 'public abstract'
        // All fields are implicitly 'public static final' (constants)
    }

    interface Resizable {
        void resize(double factor);
        double getScale();
    }

    // Interface with constants
    interface GameConstants {
        int MAX_PLAYERS = 4;        // implicitly: public static final
        int DEFAULT_LIVES = 3;
        String GAME_VERSION = "1.0";
    }

    // =========================================================================
    // PART 2: IMPLEMENTING INTERFACES
    // =========================================================================

    // A class 'implements' an interface (not 'extends')
    // MUST provide implementations for ALL interface methods

    static class Circle implements Drawable, Resizable {
        double radius;
        double scale = 1.0;
        String color;

        Circle(String color, double radius) {
            this.color = color;
            this.radius = radius;
        }

        @Override
        public void draw() {
            System.out.println("    ⭕ Drawing " + color + " circle (r=" + (radius * scale) + ")");
        }

        @Override
        public void resize(double factor) {
            this.scale *= factor;
            System.out.println("    📐 Resized circle by " + factor + "x → scale=" + scale);
        }

        @Override
        public double getScale() {
            return scale;
        }
    }

    static class Square implements Drawable, Resizable {
        double side;
        double scale = 1.0;

        Square(double side) { this.side = side; }

        @Override
        public void draw() {
            System.out.println("    🟦 Drawing square (side=" + (side * scale) + ")");
        }

        @Override
        public void resize(double factor) {
            this.scale *= factor;
        }

        @Override
        public double getScale() { return scale; }
    }

    // =========================================================================
    // PART 3: DEFAULT METHODS (Java 8+)
    // =========================================================================

    // Before Java 8, interfaces could ONLY have abstract methods.
    // Now they can have DEFAULT methods — with a body!
    // This lets you ADD new methods to an interface without breaking
    // existing classes that implement it.

    interface Printable {
        void print();

        // Default method — has a body! Implementing classes get it for free.
        default void printInColor(String color) {
            System.out.println("    🎨 [" + color + "] ");
            print();
        }

        // Another default
        default void printMultiple(int times) {
            for (int i = 0; i < times; i++) {
                print();
            }
        }
    }

    // =========================================================================
    // PART 4: STATIC METHODS IN INTERFACES (Java 8+)
    // =========================================================================

    interface MathUtils {
        // Static method — belongs to the interface, not implementing classes
        static int max(int a, int b) {
            return a > b ? a : b;
        }

        static int min(int a, int b) {
            return a < b ? a : b;
        }

        static boolean isEven(int n) {
            return n % 2 == 0;
        }
    }

    // =========================================================================
    // PART 5: REAL-WORLD EXAMPLE — Multiple interfaces
    // =========================================================================

    interface Flyable {
        void fly();
        default String getFlightStatus() { return "In flight"; }
    }

    interface Swimmable {
        void swim();
        default String getSwimStatus() { return "Swimming"; }
    }

    interface Walkable {
        void walk();
    }

    // A class can extend ONE class and implement MANY interfaces!
    static class Duck implements Flyable, Swimmable, Walkable {
        String name;
        Duck(String name) { this.name = name; }

        @Override
        public void fly() { System.out.println("    🦆 " + name + " is flying!"); }

        @Override
        public void swim() { System.out.println("    🦆 " + name + " is swimming!"); }

        @Override
        public void walk() { System.out.println("    🦆 " + name + " is walking!"); }
    }

    static class Penguin implements Swimmable, Walkable {
        // Penguins CAN'T fly — they don't implement Flyable!
        String name;
        Penguin(String name) { this.name = name; }

        @Override
        public void swim() { System.out.println("    🐧 " + name + " is swimming!"); }

        @Override
        public void walk() { System.out.println("    🐧 " + name + " is waddling!"); }
    }

    // =========================================================================
    // PART 6: INTERFACE INHERITANCE
    // =========================================================================

    // Interfaces can extend OTHER interfaces!
    interface Movable {
        void move(int x, int y);
    }

    interface Clickable {
        void onClick();
    }

    // Interface extending multiple interfaces
    interface UIComponent extends Movable, Clickable, Drawable {
        void render();
        // UIComponent now requires: move(), onClick(), draw(), AND render()!
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 14: Interfaces                             ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // DEMO 1: BASIC INTERFACE USAGE
        // =====================================================================
        System.out.println("━━━ DEMO 1: Basic Interfaces ━━━");
        System.out.println();

        Circle circle = new Circle("Red", 5);
        Square square = new Square(4);

        // Use interface type for polymorphism!
        Drawable[] drawables = {circle, square};
        System.out.println("  Drawing all Drawable objects:");
        for (Drawable d : drawables) {
            d.draw();
        }
        System.out.println();

        // Resizable interface:
        Resizable[] resizables = {circle, square};
        System.out.println("  Resizing all Resizable objects:");
        for (Resizable r : resizables) {
            r.resize(2.0);
        }
        System.out.println();

        // =====================================================================
        // DEMO 2: MULTIPLE INTERFACE IMPLEMENTATION
        // =====================================================================
        System.out.println("━━━ DEMO 2: Multiple Interfaces (Duck & Penguin) ━━━");
        System.out.println();

        Duck donald = new Duck("Donald");
        Penguin tux = new Penguin("Tux");

        donald.fly();
        donald.swim();
        donald.walk();
        System.out.println();

        // tux.fly();  ← ❌ Compile error! Penguin doesn't implement Flyable
        tux.swim();
        tux.walk();
        System.out.println();

        // Use interface types for polymorphism:
        Swimmable[] swimmers = {donald, tux};
        System.out.println("  All swimmers:");
        for (Swimmable s : swimmers) {
            s.swim();
        }
        System.out.println();

        // =====================================================================
        // DEMO 3: DEFAULT METHODS
        // =====================================================================
        System.out.println("━━━ DEMO 3: Default Methods ━━━");
        System.out.println();

        System.out.println("  Default methods let you add methods to interfaces");
        System.out.println("  without breaking existing implementations.");
        System.out.println("  duck.getFlightStatus() = \"" + donald.getFlightStatus() + "\"");
        System.out.println("  (This default method was inherited from Flyable interface!)");
        System.out.println();

        // =====================================================================
        // DEMO 4: STATIC METHODS IN INTERFACES
        // =====================================================================
        System.out.println("━━━ DEMO 4: Static Interface Methods ━━━");
        System.out.println();

        // Call via interface name (not through implementing class!)
        System.out.println("  MathUtils.max(10, 20) = " + MathUtils.max(10, 20));
        System.out.println("  MathUtils.min(10, 20) = " + MathUtils.min(10, 20));
        System.out.println("  MathUtils.isEven(7)   = " + MathUtils.isEven(7));
        System.out.println();

        // =====================================================================
        // DEMO 5: INTERFACE AS A TYPE (The real power!)
        // =====================================================================
        System.out.println("━━━ DEMO 5: Interfaces as Types ━━━");
        System.out.println();

        System.out.println("  You can use an interface as a parameter type.");
        System.out.println("  This means your method works with ANY implementing class!");
        System.out.println();

        // This method works with ANY Drawable!
        System.out.println("  drawTwice(circle):");
        drawTwice(circle);
        System.out.println("  drawTwice(square):");
        drawTwice(square);
        System.out.println();

        // =====================================================================
        // COMPARISON: ABSTRACT CLASS VS INTERFACE
        // =====================================================================
        System.out.println("━━━ Abstract Class vs Interface ━━━");
        System.out.println();
        System.out.println("  ┌───────────────────────┬──────────────────────────────────┐");
        System.out.println("  │   Abstract Class       │   Interface                      │");
        System.out.println("  ├───────────────────────┼──────────────────────────────────┤");
        System.out.println("  │ 'extends' (ONE only)   │ 'implements' (MANY allowed)      │");
        System.out.println("  │ IS-A relationship      │ CAN-DO relationship              │");
        System.out.println("  │ Can have constructors  │ No constructors                  │");
        System.out.println("  │ Can have instance fields│ Only constants (public static final)│");
        System.out.println("  │ Any access modifier    │ Methods are public               │");
        System.out.println("  │ Partial implementation │ Contract (+ defaults since Java 8)│");
        System.out.println("  │ Example: Animal         │ Example: Flyable, Serializable  │");
        System.out.println("  └───────────────────────┴──────────────────────────────────┘");
        System.out.println();
        System.out.println("  💡 When to use which?");
        System.out.println("     • Use ABSTRACT CLASS when classes share code AND state");
        System.out.println("     • Use INTERFACE when classes share behavior (contract)");
        System.out.println("     • You can use BOTH: class Dog extends Animal implements Trainable");
        System.out.println();

        // =====================================================================
        // JAVA BUILT-IN INTERFACES (You already use them!)
        // =====================================================================
        System.out.println("━━━ Famous Java Interfaces You Should Know ━━━");
        System.out.println();
        System.out.println("  Comparable<T>  → compareTo()  → for sorting objects");
        System.out.println("  Iterable<T>    → iterator()   → for using in for-each loops");
        System.out.println("  Serializable   → (marker)     → for saving objects to files");
        System.out.println("  Cloneable      → clone()      → for copying objects");
        System.out.println("  Runnable       → run()        → for multi-threading");
        System.out.println("  List<T>        → add(), get() → ArrayList implements List");
        System.out.println("  Map<K,V>       → put(), get() → HashMap implements Map");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Forgetting to make implementing methods public:");
        System.out.println("   Interface methods are implicitly public.");
        System.out.println("   void draw() { }  ← ❌ less accessible than interface!");
        System.out.println("   public void draw() { }  ← ✅");
        System.out.println();
        System.out.println("2. Trying to instantiate an interface:");
        System.out.println("   Drawable d = new Drawable();  ← ❌ Can't instantiate!");
        System.out.println("   Drawable d = new Circle(\"Red\", 5);  ← ✅ Polymorphism!");
        System.out.println();
        System.out.println("3. Using 'extends' instead of 'implements':");
        System.out.println("   class Circle extends Drawable { }  ← ❌ 'extends' is for classes!");
        System.out.println("   class Circle implements Drawable { }  ← ✅");
        System.out.println();
        System.out.println("4. Diamond problem with default methods:");
        System.out.println("   If two interfaces have the same default method, the implementing");
        System.out.println("   class MUST override it to resolve the conflict.");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("1. Create interfaces: Playable, Recordable, Streamable");
        System.out.println("   Implement: Song (Playable + Recordable)");
        System.out.println("             Podcast (Playable + Recordable + Streamable)");
        System.out.println("             LiveStream (Playable + Streamable)");
        System.out.println();
        System.out.println("2. Create a Comparable<Student> so students can be sorted by GPA.");
        System.out.println("   Sort an array of students and print the result.");
        System.out.println();
        System.out.println("✅ OOP MODULE COMPLETE! 🎉");
        System.out.println("   Next module: Core Concepts!");
        System.out.println("   Start with: com.ultimate.java.core.A_ExceptionHandling");
    }

    // Helper method using interface type
    static void drawTwice(Drawable drawable) {
        drawable.draw();
        drawable.draw();
    }
}
