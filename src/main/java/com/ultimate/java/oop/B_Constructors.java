package com.ultimate.java.oop;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                       CONSTRUCTORS IN JAVA                                 ║
 * ║            The Special Method That Brings Objects to Life                   ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │                                                                           │
 * │  • A_ClassesAndObjects.java (classes, objects, fields, methods, 'new')    │
 * │  • E_Methods.java (parameters, return types, overloading)                │
 * │  • Understanding that 'new Dog()' calls a constructor                    │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS A CONSTRUCTOR?                                                    │
 * │                                                                           │
 * │  A constructor is a SPECIAL METHOD that runs AUTOMATICALLY when you       │
 * │  create a new object. Its job is to INITIALIZE (set up) the object.      │
 * │                                                                           │
 * │  Think of a constructor like a BIRTH CERTIFICATE:                        │
 * │    When a baby is born, the birth certificate is filled out with:         │
 * │    - Name ✅                                                              │
 * │    - Date of birth ✅                                                     │
 * │    - Parents ✅                                                           │
 * │                                                                           │
 * │  The constructor fills in all the INITIAL DATA for a new object.          │
 * │                                                                           │
 * │  Rules:                                                                   │
 * │    1. Constructor name MUST match the class name                          │
 * │    2. NO return type (not even void!)                                     │
 * │    3. Called automatically by 'new'                                       │
 * │    4. Can be overloaded (multiple constructors with different params)     │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY DO WE NEED CONSTRUCTORS?                                             │
 * │                                                                           │
 * │  Without constructors:                                                    │
 * │    Dog d = new Dog();                                                    │
 * │    d.name = "Buddy";      // What if you forget this?                    │
 * │    d.breed = "Golden";    // Or this?                                    │
 * │    d.age = 3;             // The object exists in an INCOMPLETE state!   │
 * │                                                                           │
 * │  With constructors:                                                       │
 * │    Dog d = new Dog("Buddy", "Golden", 3);                                │
 * │    // EVERYTHING is set up in ONE LINE. Can't forget anything!           │
 * │    // The object is READY TO USE immediately.                            │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class B_Constructors {

    // =========================================================================
    // EXAMPLE CLASS: Car (with multiple types of constructors)
    // =========================================================================
    static class Car {
        String make;
        String model;
        int year;
        String color;
        double mileage;

        // ─────────────────────────────────────────────────────────────────
        // CONSTRUCTOR 1: Default constructor (no parameters)
        // ─────────────────────────────────────────────────────────────────
        // This creates a car with default values.
        // If you DON'T write ANY constructor, Java gives you one like this
        // for free (the "no-arg constructor"). But once you write ANY
        // constructor, the free one disappears!
        Car() {
            this.make = "Unknown";
            this.model = "Unknown";
            this.year = 2024;
            this.color = "White";
            this.mileage = 0;
            System.out.println("    🚗 Default constructor called! A blank car is born.");
        }

        // ─────────────────────────────────────────────────────────────────
        // CONSTRUCTOR 2: Parameterized constructor (with some parameters)
        // ─────────────────────────────────────────────────────────────────
        // THE 'this' KEYWORD:
        // When parameter names are the SAME as field names, use 'this' to
        // distinguish them:
        //   this.make = make;
        //   ↑           ↑
        //   field      parameter (passed as argument)
        Car(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
            this.color = "White";    // default color
            this.mileage = 0;        // new car = 0 miles
            System.out.println("    🚗 3-param constructor: " + make + " " + model + " (" + year + ")");
        }

        // ─────────────────────────────────────────────────────────────────
        // CONSTRUCTOR 3: Full constructor (all parameters)
        // ─────────────────────────────────────────────────────────────────
        Car(String make, String model, int year, String color, double mileage) {
            this.make = make;
            this.model = model;
            this.year = year;
            this.color = color;
            this.mileage = mileage;
            System.out.println("    🚗 Full constructor: " + year + " " + color + " " + make + " " + model);
        }

        // ─────────────────────────────────────────────────────────────────
        // CONSTRUCTOR 4: Copy constructor (create from another car)
        // ─────────────────────────────────────────────────────────────────
        // Sometimes you want to create a car that's an EXACT COPY of another.
        // Think: "I want the same car as John, please!"
        Car(Car other) {
            this.make = other.make;
            this.model = other.model;
            this.year = other.year;
            this.color = other.color;
            this.mileage = other.mileage;
            System.out.println("    🚗 Copy constructor: Cloned a " + make + " " + model);
        }

        void displayInfo() {
            System.out.printf("    → %d %s %s %s (%.1f miles)%n", year, color, make, model, mileage);
        }
    }

    // =========================================================================
    // EXAMPLE: Constructor Chaining (one constructor calls another!)
    // =========================================================================
    static class Employee {
        String name;
        String department;
        double salary;
        String employeeId;

        // Full constructor
        Employee(String name, String department, double salary, String employeeId) {
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.employeeId = employeeId;
        }

        // Calls the full constructor using this(...)!
        // Think: "Use the full form, but fill in some defaults for me."
        Employee(String name, String department) {
            this(name, department, 50000.0, "EMP-" + System.currentTimeMillis());
            // ↑ this(...) calls the other constructor!
            // MUST be the FIRST statement in the constructor.
        }

        // Default — calls the 2-param, which calls the 4-param!
        Employee() {
            this("New Hire", "Unassigned");
        }

        void display() {
            System.out.printf("    → %s | Dept: %s | Salary: $%.0f | ID: %s%n",
                    name, department, salary, employeeId);
        }
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 9: Constructors                            ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: DEFAULT CONSTRUCTOR
        // =====================================================================
        System.out.println("━━━ PART 1: Default Constructor ━━━");
        System.out.println();

        Car defaultCar = new Car();  // Calls the no-arg constructor
        defaultCar.displayInfo();
        System.out.println();

        // =====================================================================
        // PART 2: PARAMETERIZED CONSTRUCTORS
        // =====================================================================
        System.out.println("━━━ PART 2: Parameterized Constructors ━━━");
        System.out.println();

        Car car1 = new Car("Toyota", "Camry", 2023);
        car1.displayInfo();
        System.out.println();

        Car car2 = new Car("Tesla", "Model 3", 2024, "Red", 150.5);
        car2.displayInfo();
        System.out.println();

        // Java picks the RIGHT constructor based on arguments (overloading!)
        System.out.println("  💡 Java picks the constructor based on:");
        System.out.println("     • Number of arguments");
        System.out.println("     • Types of arguments");
        System.out.println("     • Order of arguments");
        System.out.println();

        // =====================================================================
        // PART 3: COPY CONSTRUCTOR
        // =====================================================================
        System.out.println("━━━ PART 3: Copy Constructor ━━━");
        System.out.println();

        Car car3 = new Car(car2);  // Copy of the Tesla
        car3.color = "Blue";       // Change the copy's color
        System.out.println("  Original:");
        car2.displayInfo();
        System.out.println("  Copy (with color changed):");
        car3.displayInfo();
        System.out.println("  → They are SEPARATE objects! Changing one doesn't affect the other.");
        System.out.println();

        // =====================================================================
        // PART 4: CONSTRUCTOR CHAINING (this(...))
        // =====================================================================
        System.out.println("━━━ PART 4: Constructor Chaining ━━━");
        System.out.println();

        // Constructor chaining: one constructor calls another using this(...)
        // This avoids code duplication!

        Employee emp1 = new Employee("Alice", "Engineering", 95000, "EMP-001");
        Employee emp2 = new Employee("Bob", "Marketing");  // Uses defaults for salary & ID
        Employee emp3 = new Employee();                      // All defaults

        emp1.display();
        emp2.display();
        emp3.display();
        System.out.println();

        System.out.println("  📐 How chaining works:");
        System.out.println("     Employee()");
        System.out.println("       → calls this(\"New Hire\", \"Unassigned\")");
        System.out.println("         → calls this(name, dept, 50000, auto-id)");
        System.out.println("           → final constructor sets all fields");
        System.out.println();
        System.out.println("  Rules for this(...):");
        System.out.println("     • Must be the FIRST statement in the constructor");
        System.out.println("     • Can only call ONE other constructor");
        System.out.println("     • Cannot create circular chains (A→B→A = error!)");
        System.out.println();

        // =====================================================================
        // PART 5: THE INVISIBLE DEFAULT CONSTRUCTOR
        // =====================================================================
        System.out.println("━━━ PART 5: The Invisible Default Constructor ━━━");
        System.out.println();

        System.out.println("  If you write NO constructors at all, Java provides a");
        System.out.println("  free default constructor that does nothing:");
        System.out.println();
        System.out.println("    class Foo {");
        System.out.println("        // No constructor written");
        System.out.println("    }");
        System.out.println("    // Java secretly adds: Foo() { }");
        System.out.println("    Foo f = new Foo();  ← works because of the invisible constructor!");
        System.out.println();
        System.out.println("  ⚠️ BUT! Once you write ANY constructor, the free one VANISHES:");
        System.out.println();
        System.out.println("    class Bar {");
        System.out.println("        Bar(int x) { }   // You wrote a constructor");
        System.out.println("    }");
        System.out.println("    Bar b = new Bar();     ← ❌ COMPILE ERROR! No no-arg constructor!");
        System.out.println("    Bar b = new Bar(5);    ← ✅ This works.");
        System.out.println();
        System.out.println("  📝 Best practice: If you write a parameterized constructor,");
        System.out.println("  also write a no-arg constructor if you want both options.");
        System.out.println();

        // =====================================================================
        // PART 6: CONSTRUCTOR VS METHOD (What's the difference?)
        // =====================================================================
        System.out.println("━━━ PART 6: Constructor vs Method ━━━");
        System.out.println();
        System.out.println("  ┌───────────────────────┬─────────────────────────────┐");
        System.out.println("  │     CONSTRUCTOR        │          METHOD             │");
        System.out.println("  ├───────────────────────┼─────────────────────────────┤");
        System.out.println("  │ Same name as class     │ Any name you choose         │");
        System.out.println("  │ NO return type          │ Has a return type (or void) │");
        System.out.println("  │ Called by 'new'         │ Called by name              │");
        System.out.println("  │ Runs once (at creation) │ Can run many times          │");
        System.out.println("  │ Initializes object      │ Performs actions            │");
        System.out.println("  │ Can't be inherited      │ Can be inherited            │");
        System.out.println("  └───────────────────────┴─────────────────────────────┘");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Adding a return type to constructor:");
        System.out.println("   void Car() { }   ← This is a METHOD named 'Car', not a constructor!");
        System.out.println("   Car() { }         ← This is the constructor ✅");
        System.out.println();
        System.out.println("2. Forgetting 'this' when params match field names:");
        System.out.println("   Car(String make) { make = make; }   ← Assigns param to itself!");
        System.out.println("   Car(String make) { this.make = make; }  ← Correct ✅");
        System.out.println();
        System.out.println("3. this(...) not as first statement:");
        System.out.println("   Car(String make) {");
        System.out.println("       System.out.println(\"hi\");");
        System.out.println("       this(make, \"Unknown\", 2024);  ← ❌ Must be FIRST!");
        System.out.println("   }");
        System.out.println();
        System.out.println("4. Expecting the free default constructor after writing one:");
        System.out.println("   Once you write ANY constructor, the free one is gone.");
        System.out.println("   Add Car() { } explicitly if you need it.");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("1. Create a 'Student' class with constructors:");
        System.out.println("   - Default: name='Unknown', grade=0");
        System.out.println("   - With name only (default grade=0)");
        System.out.println("   - With name and grade");
        System.out.println("   - Copy constructor");
        System.out.println("   Use constructor chaining (this(...))!");
        System.out.println();
        System.out.println("2. Create a 'Pizza' class where the constructor validates:");
        System.out.println("   - Size must be 'S', 'M', or 'L'");
        System.out.println("   - Toppings must be 1-5");
        System.out.println("   Throw IllegalArgumentException for invalid inputs!");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: C_Encapsulation.java");
    }
}
