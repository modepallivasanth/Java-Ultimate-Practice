package com.ultimate.java.oop;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                    CLASSES AND OBJECTS IN JAVA                              ║
 * ║             The Foundation of Object-Oriented Programming                  ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │                                                                           │
 * │  • ALL Basics module (variables, operators, control flow, loops, methods) │
 * │  • Understanding of data types (int, String, boolean, etc.)               │
 * │  • Understanding of methods (parameters, return types)                    │
 * │  • The concept that we've been using classes all along!                   │
 * │    (Every .java file IS a class. main() IS inside a class.)              │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT ARE CLASSES AND OBJECTS?                                             │
 * │                                                                           │
 * │  CLASS = A BLUEPRINT (a design plan)                                      │
 * │  OBJECT = A THING built from that blueprint                               │
 * │                                                                           │
 * │  Real-world analogy:                                                      │
 * │                                                                           │
 * │    🏗️ BLUEPRINT (Class)          🏠🏠🏠 HOUSES (Objects)                │
 * │    ┌─────────────────┐           ┌──────────────────────┐                │
 * │    │ House Blueprint  │           │ House 1: Red, 3 beds  │               │
 * │    │                  │ ────────→ │ House 2: Blue, 2 beds │               │
 * │    │ - color          │  create   │ House 3: Green, 4 beds│               │
 * │    │ - bedrooms       │  objects  └──────────────────────┘                │
 * │    │ - openDoor()     │                                                   │
 * │    └─────────────────┘                                                   │
 * │                                                                           │
 * │    You write the blueprint ONCE, then build MANY houses from it.         │
 * │    Each house has the same STRUCTURE but different VALUES.                │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY DO WE NEED CLASSES AND OBJECTS?                                      │
 * │                                                                           │
 * │  Without OOP, imagine storing data for 100 students:                     │
 * │    String name1, name2, name3, ...name100;                               │
 * │    int age1, age2, age3, ...age100;                                      │
 * │    double gpa1, gpa2, gpa3, ...gpa100;                                   │
 * │    That's 300 variables! 😱                                              │
 * │                                                                           │
 * │  With OOP:                                                                │
 * │    Student[] students = new Student[100];                                │
 * │    Each Student object bundles name + age + gpa TOGETHER.                │
 * │    Clean. Organized. Scalable.                                            │
 * │                                                                           │
 * │  OOP lets you MODEL REAL-WORLD THINGS in code.                           │
 * │  A BankAccount has a balance and methods to deposit/withdraw.             │
 * │  A Car has speed, fuel, and methods to accelerate/brake.                 │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class A_ClassesAndObjects {

    // =========================================================================
    // PART 1: DEFINING A CLASS (Creating the Blueprint)
    // =========================================================================

    // A class has two main components:
    //   1. FIELDS (attributes/properties) → What the object HAS (data)
    //   2. METHODS (behaviors/actions)    → What the object DOES (functions)
    //
    // Let's create a simple Dog class:

    // ── Inner class: Dog ──────────────────────────────────────────────────
    // (Note: We're putting this inside A_ClassesAndObjects for simplicity.
    //  Normally, each class gets its own file!)

    static class Dog {
        // FIELDS (What a Dog HAS)
        String name;       // Every dog has a name
        String breed;      // Every dog has a breed
        int age;           // Every dog has an age
        String color;      // Every dog has a color
        boolean isHungry;  // Every dog can be hungry or not

        // METHODS (What a Dog DOES)
        void bark() {
            System.out.println("    🐕 " + name + " says: Woof! Woof!");
        }

        void eat(String food) {
            System.out.println("    🦴 " + name + " is eating " + food + ". Yummy!");
            isHungry = false;
        }

        void displayInfo() {
            System.out.println("    ┌──────────────────────────┐");
            System.out.println("    │ Dog Info                  │");
            System.out.println("    ├──────────────────────────┤");
            System.out.println("    │ Name:   " + padRight(name, 17) + "│");
            System.out.println("    │ Breed:  " + padRight(breed, 17) + "│");
            System.out.println("    │ Age:    " + padRight(age + " years", 17) + "│");
            System.out.println("    │ Color:  " + padRight(color, 17) + "│");
            System.out.println("    │ Hungry: " + padRight(String.valueOf(isHungry), 17) + "│");
            System.out.println("    └──────────────────────────┘");
        }
    }

    // ── Inner class: BankAccount ──────────────────────────────────────────
    // A more practical example!

    static class BankAccount {
        String ownerName;
        String accountNumber;
        double balance;

        void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.printf("    💰 Deposited $%.2f. New balance: $%.2f%n", amount, balance);
            } else {
                System.out.println("    ❌ Invalid deposit amount!");
            }
        }

        void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.printf("    💸 Withdrew $%.2f. New balance: $%.2f%n", amount, balance);
            } else if (amount > balance) {
                System.out.printf("    ❌ Insufficient funds! Balance: $%.2f, Requested: $%.2f%n", balance, amount);
            } else {
                System.out.println("    ❌ Invalid withdrawal amount!");
            }
        }

        void displayBalance() {
            System.out.printf("    📊 Account %s (%s): $%.2f%n", accountNumber, ownerName, balance);
        }
    }

    // =========================================================================
    // MAIN METHOD — Creating and Using Objects
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 8: Classes and Objects                     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 2: CREATING OBJECTS (Building from the Blueprint)
        // =====================================================================
        System.out.println("━━━ PART 1: Creating Objects ━━━");
        System.out.println();

        // To create an object, use the 'new' keyword:
        //   ClassName objectName = new ClassName();
        //
        // Think: "Use the House Blueprint to BUILD a new house"
        //   Dog myDog = new Dog();
        //   ↑    ↑       ↑   ↑
        //   │    │       │   └── Constructor call (we'll learn more next lesson!)
        //   │    │       └── 'new' keyword: "create a new one!"
        //   │    └── Variable name (reference to the object)
        //   └── Type (the blueprint/class)

        Dog dog1 = new Dog();    // Create first dog
        dog1.name = "Buddy";     // Set its fields using the DOT (.) operator
        dog1.breed = "Golden Retriever";
        dog1.age = 3;
        dog1.color = "Golden";
        dog1.isHungry = true;

        Dog dog2 = new Dog();    // Create second dog — DIFFERENT object!
        dog2.name = "Max";
        dog2.breed = "German Shepherd";
        dog2.age = 5;
        dog2.color = "Black & Tan";
        dog2.isHungry = false;

        System.out.println("  Created two Dog objects from the same blueprint:");
        System.out.println();
        dog1.displayInfo();
        System.out.println();
        dog2.displayInfo();
        System.out.println();

        // =====================================================================
        // PART 3: CALLING METHODS ON OBJECTS
        // =====================================================================
        System.out.println("━━━ PART 2: Calling Methods on Objects ━━━");
        System.out.println();

        // Use the DOT (.) operator to call methods:
        //   objectName.methodName(arguments);

        dog1.bark();            // Buddy barks
        dog2.bark();            // Max barks
        dog1.eat("kibble");     // Buddy eats
        System.out.println("    Buddy is still hungry? " + dog1.isHungry + " (eating set it to false!)");
        System.out.println();

        // =====================================================================
        // PART 4: PRACTICAL EXAMPLE — BankAccount
        // =====================================================================
        System.out.println("━━━ PART 3: Practical Example — BankAccount ━━━");
        System.out.println();

        BankAccount account = new BankAccount();
        account.ownerName = "Vasanth";
        account.accountNumber = "ACC-001";
        account.balance = 1000.00;

        account.displayBalance();
        account.deposit(500.00);
        account.withdraw(200.00);
        account.withdraw(5000.00);  // Should fail — insufficient funds!
        account.displayBalance();
        System.out.println();

        // =====================================================================
        // PART 5: OBJECTS IN MEMORY (How it works under the hood)
        // =====================================================================
        System.out.println("━━━ PART 4: Objects in Memory ━━━");
        System.out.println();

        // When you write:  Dog myDog = new Dog();
        //
        // Two things happen:
        // 1. 'new Dog()' creates the OBJECT on the HEAP (big memory area)
        // 2. 'myDog' is a REFERENCE (pointer) stored on the STACK (small, fast memory)
        //    that POINTS TO the object on the heap.
        //
        //   STACK                    HEAP
        //   ┌──────────┐           ┌───────────────────┐
        //   │ myDog ──────────────→│ Dog Object        │
        //   │ (reference)│          │ name: "Buddy"     │
        //   └──────────┘           │ breed: "Golden..." │
        //                          │ age: 3             │
        //                          └───────────────────┘

        System.out.println("  Dog dog1 → REFERENCE (points to object on heap)");
        System.out.println("  Dog dog2 → DIFFERENT reference (different object)");
        System.out.println();

        // IMPORTANT: What happens when two references point to the SAME object?
        Dog dog3 = dog1;  // dog3 now points to the SAME object as dog1!
        dog3.name = "Buddy Jr.";  // This ALSO changes dog1.name!

        System.out.println("  After: Dog dog3 = dog1; dog3.name = \"Buddy Jr.\";");
        System.out.println("  dog1.name = \"" + dog1.name + "\"  ← ALSO changed!");
        System.out.println("  dog3.name = \"" + dog3.name + "\"  ← Same object!");
        System.out.println("  dog1 == dog3? " + (dog1 == dog3) + "  ← Same reference!");
        System.out.println("  dog1 == dog2? " + (dog1 == dog2) + " ← Different objects!");
        System.out.println();

        // Visualize:
        System.out.println("  STACK                    HEAP");
        System.out.println("  ┌──────────┐           ┌───────────────────┐");
        System.out.println("  │ dog1 ──────────┬────→│ Dog Object        │");
        System.out.println("  ├──────────┤     │     │ name: \"Buddy Jr.\" │");
        System.out.println("  │ dog3 ──────────┘     │ age: 3            │");
        System.out.println("  ├──────────┤           └───────────────────┘");
        System.out.println("  │ dog2 ──────────────→ │ Dog Object        │");
        System.out.println("  └──────────┘           │ name: \"Max\"       │");
        System.out.println("                         └───────────────────┘");
        System.out.println();

        // =====================================================================
        // PART 6: null — The "Nothing" Reference
        // =====================================================================
        System.out.println("━━━ PART 5: null — The Nothing Reference ━━━");
        System.out.println();

        // 'null' means the reference points to NOTHING.
        // Think: An empty jar label that says "Cookies" but has no jar!

        Dog emptyDog = null;
        System.out.println("  Dog emptyDog = null;  → points to nothing");
        System.out.println("  emptyDog == null? " + (emptyDog == null));
        System.out.println();
        System.out.println("  ⚠️ Calling a method on null → NullPointerException (NPE)!");
        System.out.println("     emptyDog.bark() → 💥 CRASH! There's no dog to bark!");
        System.out.println("     ALWAYS check: if (dog != null) { dog.bark(); }");
        System.out.println();

        // =====================================================================
        // PART 7: 'this' KEYWORD (Referring to the current object)
        // =====================================================================
        System.out.println("━━━ PART 6: The 'this' Keyword (Preview) ━━━");
        System.out.println();
        System.out.println("  'this' refers to the CURRENT OBJECT.");
        System.out.println("  When you call dog1.bark(), inside bark(), 'this' IS dog1.");
        System.out.println("  When you call dog2.bark(), inside bark(), 'this' IS dog2.");
        System.out.println("  We'll explore 'this' deeply in B_Constructors.java!");
        System.out.println();

        // =====================================================================
        // PART 8: CLASS VS OBJECT COMPARISON
        // =====================================================================
        System.out.println("━━━ PART 7: Class vs Object Summary ━━━");
        System.out.println();
        System.out.println("  ┌────────────────────────┬──────────────────────────────┐");
        System.out.println("  │       CLASS             │          OBJECT              │");
        System.out.println("  ├────────────────────────┼──────────────────────────────┤");
        System.out.println("  │ Blueprint / Template    │ Instance / Real thing        │");
        System.out.println("  │ Exists in code          │ Exists in memory (heap)      │");
        System.out.println("  │ Created ONCE            │ Created MANY times           │");
        System.out.println("  │ Defines structure       │ Has actual values             │");
        System.out.println("  │ No memory allocated     │ Memory allocated with 'new'  │");
        System.out.println("  │ Example: Dog (concept)  │ Example: Buddy (a real dog)  │");
        System.out.println("  └────────────────────────┴──────────────────────────────┘");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Forgetting 'new':");
        System.out.println("   Dog d;       ← just a reference, no object created!");
        System.out.println("   Dog d = new Dog();  ← creates the actual object ✅");
        System.out.println();
        System.out.println("2. NullPointerException:");
        System.out.println("   Dog d = null;");
        System.out.println("   d.bark();  💥 NPE! Always check for null first.");
        System.out.println();
        System.out.println("3. Confusing == with .equals():");
        System.out.println("   == checks if two references point to the SAME object");
        System.out.println("   .equals() checks if two objects have the SAME content");
        System.out.println("   (We'll override .equals() in later lessons!)");
        System.out.println();
        System.out.println("4. Thinking assignment copies objects:");
        System.out.println("   Dog copy = original;  ← NOT a copy! Same object, two names.");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("1. Create a 'Book' class with: title, author, pages, isRead.");
        System.out.println("   Add methods: read(), getInfo().");
        System.out.println("   Create 3 books and display their info.");
        System.out.println();
        System.out.println("2. Create a 'Rectangle' class with width and height.");
        System.out.println("   Add methods: getArea(), getPerimeter(), isSquare().");
        System.out.println();
        System.out.println("3. Create a 'Student' class with name, grades (int array).");
        System.out.println("   Add methods: getAverage(), getHighest(), isPassing().");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: B_Constructors.java");
    }

    // Helper method for padding
    static String padRight(String s, int width) {
        return String.format("%-" + width + "s", s);
    }
}
