package com.ultimate.java.oop;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                        INHERITANCE IN JAVA                                 ║
 * ║              Building New Classes From Existing Ones                        ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │                                                                           │
 * │  • A_ClassesAndObjects.java (classes, objects, fields, methods)           │
 * │  • B_Constructors.java (constructors, 'this' keyword)                    │
 * │  • C_Encapsulation.java (access modifiers, private/public/protected)     │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS INHERITANCE?                                                      │
 * │                                                                           │
 * │  Inheritance lets a new class INHERIT (receive) fields and methods        │
 * │  from an existing class.                                                  │
 * │                                                                           │
 * │  Think: FAMILY TRAITS                                                     │
 * │    You inherit traits from your parents: eye color, hair type, etc.       │
 * │    You're not a COPY of your parents — you're YOU with their traits       │
 * │    PLUS your own unique ones.                                             │
 * │                                                                           │
 * │    Parent class (superclass):  Animal                                     │
 * │      │ name, age, eat(), sleep()                                          │
 * │      │                                                                    │
 * │      ├── Child class:  Dog                                                │
 * │      │   name, age, eat(), sleep() ← INHERITED!                          │
 * │      │   + bark(), fetch() ← NEW, specific to Dog                        │
 * │      │                                                                    │
 * │      └── Child class:  Cat                                                │
 * │          name, age, eat(), sleep() ← INHERITED!                           │
 * │          + purr(), climb() ← NEW, specific to Cat                        │
 * │                                                                           │
 * │  KEY TERMINOLOGY:                                                         │
 * │    Parent/Super/Base class  = the class being inherited FROM              │
 * │    Child/Sub/Derived class  = the class that inherits                     │
 * │    "extends" keyword        = "inherits from"                             │
 * │    IS-A relationship        = Dog IS-A Animal, Cat IS-A Animal            │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY DO WE NEED INHERITANCE?                                              │
 * │                                                                           │
 * │  Without inheritance:                                                     │
 * │    class Dog { String name; int age; void eat(){} void bark(){} }        │
 * │    class Cat { String name; int age; void eat(){} void purr(){} }        │
 * │    class Bird { String name; int age; void eat(){} void fly(){} }        │
 * │    ↑ name, age, eat() are DUPLICATED in every class! 😱                  │
 * │                                                                           │
 * │  With inheritance:                                                        │
 * │    class Animal { String name; int age; void eat(){} } ← write ONCE     │
 * │    class Dog extends Animal { void bark(){} }          ← only new stuff  │
 * │    class Cat extends Animal { void purr(){} }          ← only new stuff  │
 * │    ✅ No duplication. Change eat() once → fixed everywhere!              │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class D_Inheritance {

    // =========================================================================
    // THE PARENT CLASS (Superclass)
    // =========================================================================
    static class Animal {
        protected String name;    // 'protected' = accessible in subclasses!
        protected int age;
        protected String sound;

        Animal(String name, int age) {
            this.name = name;
            this.age = age;
            this.sound = "...";
            System.out.println("    🔧 Animal constructor called for: " + name);
        }

        void eat(String food) {
            System.out.println("    🍽️ " + name + " is eating " + food);
        }

        void sleep() {
            System.out.println("    💤 " + name + " is sleeping... Zzz");
        }

        void makeSound() {
            System.out.println("    🔊 " + name + " says: " + sound);
        }

        String getInfo() {
            return name + " (age " + age + ")";
        }
    }

    // =========================================================================
    // CHILD CLASSES (Subclasses) — use 'extends' keyword
    // =========================================================================

    // ── Dog extends Animal ────────────────────────────────────────────────
    static class Dog extends Animal {
        private String breed;

        Dog(String name, int age, String breed) {
            super(name, age);  // MUST call parent constructor FIRST!
            this.breed = breed;
            this.sound = "Woof!";
            System.out.println("    🔧 Dog constructor called for: " + name);
        }

        // NEW method — only Dogs can fetch!
        void fetch(String item) {
            System.out.println("    🎾 " + name + " fetches the " + item + "!");
        }

        // OVERRIDING — changing inherited behavior
        @Override
        void eat(String food) {
            System.out.println("    🐕 " + name + " wolfs down " + food + " in 2 seconds!");
        }

        @Override
        String getInfo() {
            return name + " the " + breed + " (age " + age + ")";
        }
    }

    // ── Cat extends Animal ────────────────────────────────────────────────
    static class Cat extends Animal {
        private boolean isIndoor;

        Cat(String name, int age, boolean isIndoor) {
            super(name, age);
            this.isIndoor = isIndoor;
            this.sound = "Meow!";
        }

        void purr() {
            System.out.println("    😺 " + name + " is purring... prrrr");
        }

        @Override
        void eat(String food) {
            System.out.println("    🐱 " + name + " delicately nibbles " + food);
        }

        @Override
        String getInfo() {
            return name + " (" + (isIndoor ? "indoor" : "outdoor") + " cat, age " + age + ")";
        }
    }

    // =========================================================================
    // MULTI-LEVEL INHERITANCE (Grandchild!)
    // =========================================================================
    // Animal → Dog → GuideDog (a chain of inheritance!)

    static class GuideDog extends Dog {
        private String assignedPerson;

        GuideDog(String name, int age, String breed, String assignedPerson) {
            super(name, age, breed);  // Calls Dog's constructor
            this.assignedPerson = assignedPerson;
            System.out.println("    🔧 GuideDog constructor called for: " + name);
        }

        void guide() {
            System.out.println("    🦮 " + name + " is guiding " + assignedPerson + " safely!");
        }

        @Override
        String getInfo() {
            return super.getInfo() + " [Guide dog for " + assignedPerson + "]";
        }
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 11: Inheritance                            ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: BASIC INHERITANCE
        // =====================================================================
        System.out.println("━━━ PART 1: Basic Inheritance ━━━");
        System.out.println();

        Dog buddy = new Dog("Buddy", 3, "Golden Retriever");
        System.out.println();

        // Dog INHERITS eat() and sleep() from Animal:
        buddy.eat("kibble");      // Overridden version!
        buddy.sleep();            // Inherited as-is from Animal
        buddy.makeSound();        // Inherited, but 'sound' was changed in constructor
        buddy.fetch("ball");      // Dog's own method
        System.out.println("  Info: " + buddy.getInfo());
        System.out.println();

        Cat whiskers = new Cat("Whiskers", 5, true);
        whiskers.eat("tuna");     // Cat's overridden version
        whiskers.sleep();         // Inherited from Animal
        whiskers.purr();          // Cat's own method
        System.out.println("  Info: " + whiskers.getInfo());
        System.out.println();

        // =====================================================================
        // PART 2: super KEYWORD
        // =====================================================================
        System.out.println("━━━ PART 2: The 'super' Keyword ━━━");
        System.out.println();

        // 'super' refers to the PARENT class. Used for:
        //
        // 1. super() → Call parent's constructor (MUST be first line!)
        // 2. super.method() → Call parent's version of an overridden method
        // 3. super.field → Access parent's field (if not private)

        System.out.println("  super() in constructor — notice the order:");
        System.out.println("  ┌───────────────────────────────────┐");
        GuideDog rex = new GuideDog("Rex", 4, "Labrador", "Alice");
        System.out.println("  └───────────────────────────────────┘");
        System.out.println("  Construction order: Animal → Dog → GuideDog (parent first!)");
        System.out.println();

        rex.eat("premium kibble");  // Inherited overridden from Dog
        rex.fetch("toy");           // Inherited from Dog
        rex.guide();                // GuideDog's own method
        System.out.println("  Info: " + rex.getInfo());
        System.out.println();

        // =====================================================================
        // PART 3: METHOD OVERRIDING
        // =====================================================================
        System.out.println("━━━ PART 3: Method Overriding ━━━");
        System.out.println();

        // When a child class provides its OWN version of a method
        // that already exists in the parent class.
        //
        // Rules for overriding:
        // 1. Same method name
        // 2. Same parameters
        // 3. Same or more accessible modifier (can go public→public, not public→private)
        // 4. Same or narrower return type
        // 5. Use @Override annotation (not required, but HIGHLY recommended!)

        System.out.println("  Same method call, different behavior based on TYPE:");

        Animal genericAnimal = new Animal("Generic", 1);
        System.out.println();

        genericAnimal.eat("food");    // Animal's eat()
        buddy.eat("food");            // Dog's eat() (overridden)
        whiskers.eat("food");         // Cat's eat() (overridden)
        System.out.println();

        System.out.println("  @Override annotation:");
        System.out.println("    • Tells Java: 'I'm intentionally overriding a parent method'");
        System.out.println("    • Compiler checks that the parent method actually exists");
        System.out.println("    • If you misspell the method name, @Override catches it!");
        System.out.println("    • ALWAYS use it. Always.");
        System.out.println();

        // =====================================================================
        // PART 4: WHAT IS INHERITED? WHAT ISN'T?
        // =====================================================================
        System.out.println("━━━ PART 4: What Gets Inherited? ━━━");
        System.out.println();
        System.out.println("  ✅ INHERITED:");
        System.out.println("     • public methods");
        System.out.println("     • protected methods");
        System.out.println("     • public fields");
        System.out.println("     • protected fields");
        System.out.println("     • default (package-private) members (if same package)");
        System.out.println();
        System.out.println("  ❌ NOT INHERITED:");
        System.out.println("     • private fields and methods (exist but can't access directly)");
        System.out.println("     • Constructors (but you can CALL them with super())");
        System.out.println("     • Static members (belong to the class, not inherited per se)");
        System.out.println();
        System.out.println("  💡 private fields still EXIST in the child object's memory!");
        System.out.println("     You just can't access them directly. Use parent's getters/setters.");
        System.out.println();

        // =====================================================================
        // PART 5: IS-A RELATIONSHIP
        // =====================================================================
        System.out.println("━━━ PART 5: IS-A Relationship ━━━");
        System.out.println();

        // Inheritance creates an IS-A relationship:
        // Dog IS-A Animal → true
        // Cat IS-A Animal → true
        // Animal IS-A Dog → false!

        System.out.println("  instanceof checks:");
        System.out.println("  buddy instanceof Dog:    " + (buddy instanceof Dog));
        System.out.println("  buddy instanceof Animal: " + (buddy instanceof Animal) + " ← Dog IS-A Animal!");
        Animal whiskerAsAnimal = whiskers;  // Upcast for instanceof demo
        System.out.println("  whiskers instanceof Dog: " + (whiskerAsAnimal instanceof Dog) + " ← Cat is NOT a Dog");
        System.out.println("  rex instanceof Dog:      " + (rex instanceof Dog) + " ← GuideDog IS-A Dog");
        System.out.println("  rex instanceof Animal:   " + (rex instanceof Animal) + " ← GuideDog IS-A Animal too!");
        System.out.println();

        // =====================================================================
        // PART 6: JAVA'S RULES ON INHERITANCE
        // =====================================================================
        System.out.println("━━━ PART 6: Java's Inheritance Rules ━━━");
        System.out.println();
        System.out.println("  1. Single inheritance only: class can extend ONLY ONE parent");
        System.out.println("     class Dog extends Animal { }   ← ✅ ONE parent");
        System.out.println("     class Dog extends Animal, Pet { } ← ❌ COMPILE ERROR!");
        System.out.println("     (For multiple 'parents', use Interfaces! → G_Interfaces.java)");
        System.out.println();
        System.out.println("  2. Every class inherits from Object (the ultimate parent)");
        System.out.println("     Object → Animal → Dog → GuideDog");
        System.out.println("     That's why every object has toString(), equals(), hashCode()!");
        System.out.println();
        System.out.println("  3. 'final' prevents inheritance:");
        System.out.println("     final class Secure { }");
        System.out.println("     class Hacked extends Secure { }  ← ❌ Can't extend final class!");
        System.out.println("     (String class is final — you can't extend String!)");
        System.out.println();
        System.out.println("  4. 'final' on methods prevents overriding:");
        System.out.println("     final void important() { }  ← subclass can't override this!");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Forgetting super() in child constructor:");
        System.out.println("   If parent has NO no-arg constructor, you MUST call super(args)!");
        System.out.println("   Otherwise → compile error.");
        System.out.println();
        System.out.println("2. Using inheritance for HAS-A relationships:");
        System.out.println("   Car HAS-A Engine ← Use composition (field), NOT inheritance!");
        System.out.println("   ElectricCar IS-A Car ← Use inheritance ✅");
        System.out.println();
        System.out.println("3. Overriding with wrong method signature:");
        System.out.println("   Parent: void eat(String food)");
        System.out.println("   Child:  void eat(int food)  ← This is OVERLOADING, not overriding!");
        System.out.println("   Use @Override to catch this mistake.");
        System.out.println();
        System.out.println("4. Breaking Liskov Substitution Principle (LSP):");
        System.out.println("   If Dog IS-A Animal, then anywhere you use Animal,");
        System.out.println("   you should be able to use Dog without breaking things.");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("Create a class hierarchy:");
        System.out.println("  Shape (parent) → fields: color, filled");
        System.out.println("    ├── Circle (child) → field: radius");
        System.out.println("    ├── Rectangle (child) → fields: width, height");
        System.out.println("    └── Triangle (child) → fields: base, height");
        System.out.println();
        System.out.println("  Each shape should override: getArea(), getPerimeter(), toString()");
        System.out.println("  Use super() in constructors and @Override on methods.");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: E_Polymorphism.java");
    }
}
