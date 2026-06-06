package com.ultimate.java.core;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                       FINAL KEYWORD IN JAVA                                ║
 * ║             "Once Set, Never Changed" — Immutability & Constants           ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │  • Variables and data types (primitives vs references)                    │
 * │  • OOP (classes, inheritance, method overriding)                          │
 * │  • D_StaticKeyword.java (often used together: static final)              │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT DOES 'final' MEAN?                                                   │
 * │                                                                           │
 * │  'final' means "THIS IS THE LAST VERSION. No more changes."              │
 * │                                                                           │
 * │  It can be applied to THREE things:                                       │
 * │    1. VARIABLES → value cannot be changed once assigned (constant)       │
 * │    2. METHODS   → cannot be overridden by child classes                  │
 * │    3. CLASSES   → cannot be extended (no inheritance)                    │
 * │                                                                           │
 * │  Think: Writing with a PERMANENT MARKER 🖊️ — once written, it's final! │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY DO WE NEED 'final'?                                                  │
 * │                                                                           │
 * │  1. SAFETY: Prevent accidental changes to critical values                │
 * │     final double TAX_RATE = 0.08; // Nobody can change the tax rate!     │
 * │                                                                           │
 * │  2. DESIGN: Prevent subclasses from breaking behavior                    │
 * │     final class String → you can't extend String and break it           │
 * │                                                                           │
 * │  3. PERFORMANCE: JVM can optimize final variables & methods              │
 * │                                                                           │
 * │  4. CLARITY: Tells other developers "don't touch this!"                  │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class E_FinalKeyword {

    // =========================================================================
    // PART 1: FINAL VARIABLES
    // =========================================================================

    // ── Static final = TRUE CONSTANT (compile-time constant) ─────────────
    // Convention: ALL_CAPS_WITH_UNDERSCORES
    static final double PI = 3.14159265358979;
    static final int MAX_USERS = 1000;
    static final String APP_NAME = "Java Ultimate Tutorial";

    // ── Final instance field (must be set in constructor, once per object) ─
    static class ImmutableCircle {
        final double radius;  // Must be assigned in constructor!
        final String color;

        ImmutableCircle(double radius, String color) {
            this.radius = radius;  // Assigned ONCE — can never change!
            this.color = color;
            // this.radius = 10;   // ❌ COMPILE ERROR! Already assigned!
        }

        double getArea() {
            return PI * radius * radius;
        }

        @Override
        public String toString() {
            return color + " circle (r=" + radius + ", area=" + String.format("%.2f", getArea()) + ")";
        }
    }

    // =========================================================================
    // PART 2: FINAL METHODS (Cannot be overridden)
    // =========================================================================

    static class BankAccount {
        private double balance;

        BankAccount(double balance) { this.balance = balance; }

        // This method is FINAL — subclasses CANNOT override it!
        // We want to guarantee this security check is NEVER bypassed.
        final void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("    Withdrew $" + amount + " → Balance: $" + balance);
            } else {
                System.out.println("    ❌ Invalid withdrawal!");
            }
        }

        // Non-final — subclasses CAN override this
        void displayBalance() {
            System.out.println("    Balance: $" + balance);
        }
    }

    static class PremiumAccount extends BankAccount {
        PremiumAccount(double balance) { super(balance); }

        // @Override void withdraw(double amount) { }
        // ↑ ❌ COMPILE ERROR! withdraw() is final in parent!

        @Override
        void displayBalance() {
            System.out.println("    ⭐ Premium Account Balance: $" + /* can't access private */ "***");
        }
    }

    // =========================================================================
    // PART 3: FINAL CLASSES (Cannot be extended)
    // =========================================================================

    // final class — NOBODY can create a subclass of this!
    // Java's own String, Integer, Math are all final classes.
    static final class SecurityToken {
        private final String token;
        private final long expiresAt;

        SecurityToken(String token, long expiresAt) {
            this.token = token;
            this.expiresAt = expiresAt;
        }

        boolean isValid() {
            return System.currentTimeMillis() < expiresAt;
        }

        @Override
        public String toString() {
            return "Token[" + token.substring(0, 4) + "***]";
        }
    }

    // class HackedToken extends SecurityToken { }
    // ↑ ❌ COMPILE ERROR! SecurityToken is final — cannot extend!

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 19: Final Keyword                          ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // ── Final variables ──────────────────────────────────────────────
        System.out.println("━━━ PART 1: Final Variables ━━━");
        System.out.println();

        // Constants:
        System.out.println("  PI = " + PI);
        System.out.println("  MAX_USERS = " + MAX_USERS);
        System.out.println("  APP_NAME = " + APP_NAME);
        // PI = 3.0;  ← ❌ COMPILE ERROR! Cannot reassign final variable.
        System.out.println();

        // Final local variable:
        final int maxRetries = 3;
        System.out.println("  final int maxRetries = " + maxRetries);
        // maxRetries = 5;  ← ❌ COMPILE ERROR!
        System.out.println();

        // ⚠️ TRICKY: final with REFERENCE types
        // final means the REFERENCE can't change — but the OBJECT can still be modified!
        final int[] numbers = {1, 2, 3};
        numbers[0] = 99;          // ✅ ALLOWED! Modifying the object's contents.
        // numbers = new int[5];  // ❌ COMPILE ERROR! Can't reassign the reference.
        System.out.println("  final int[] numbers = {1,2,3};");
        System.out.println("  numbers[0] = 99;       ✅ (modifying contents is OK)");
        System.out.println("  numbers = new int[5];   ❌ (reassigning reference is NOT OK)");
        System.out.println();
        System.out.println("  💡 'final' on references = you can't point to a DIFFERENT object.");
        System.out.println("     But you CAN still change the object's insides!");
        System.out.println("     For true immutability → make the class itself immutable.");
        System.out.println();

        // Immutable object:
        ImmutableCircle circle = new ImmutableCircle(5, "Red");
        System.out.println("  Immutable circle: " + circle);
        // circle.radius = 10;  ← ❌ COMPILE ERROR! radius is final.
        System.out.println();

        // ── Final methods ────────────────────────────────────────────────
        System.out.println("━━━ PART 2: Final Methods ━━━");
        System.out.println();

        BankAccount account = new BankAccount(1000);
        account.withdraw(200);    // Works — final doesn't prevent CALLING, just OVERRIDING
        account.displayBalance();
        System.out.println();
        System.out.println("  final methods: Can be called ✅, Cannot be overridden ❌");
        System.out.println("  Use when: You want to GUARANTEE specific behavior in subclasses.");
        System.out.println();

        // ── Final classes ────────────────────────────────────────────────
        System.out.println("━━━ PART 3: Final Classes ━━━");
        System.out.println();

        SecurityToken token = new SecurityToken("abcd1234secret", System.currentTimeMillis() + 3600000);
        System.out.println("  Token: " + token + ", Valid: " + token.isValid());
        System.out.println();
        System.out.println("  Famous final classes in Java:");
        System.out.println("    • String — can't extend and break string behavior");
        System.out.println("    • Integer, Double, etc. — wrapper classes are final");
        System.out.println("    • Math — utility class, no need for subclasses");
        System.out.println("    • System — core system operations, locked down");
        System.out.println();

        // ── Summary table ────────────────────────────────────────────────
        System.out.println("━━━ Summary: Where 'final' Can Be Used ━━━");
        System.out.println();
        System.out.println("  ┌──────────────┬────────────────────────────────────────┐");
        System.out.println("  │ Applied to   │ Effect                                 │");
        System.out.println("  ├──────────────┼────────────────────────────────────────┤");
        System.out.println("  │ Variable     │ Cannot be reassigned after init        │");
        System.out.println("  │ Method       │ Cannot be overridden by subclass       │");
        System.out.println("  │ Class        │ Cannot be extended (no subclasses)     │");
        System.out.println("  │ Parameter    │ Cannot be reassigned inside method     │");
        System.out.println("  └──────────────┴────────────────────────────────────────┘");
        System.out.println();

        // ── Common mistakes ──────────────────────────────────────────────
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Thinking final reference = immutable object:");
        System.out.println("   final List<String> list = new ArrayList<>();");
        System.out.println("   list.add(\"item\");  ← ✅ Allowed! List contents can change.");
        System.out.println("   list = new ArrayList<>();  ← ❌ Reference can't change.");
        System.out.println();
        System.out.println("2. Forgetting to initialize final fields:");
        System.out.println("   final int x;  ← Must be assigned in constructor or declaration!");
        System.out.println();
        System.out.println("3. Confusing static final with final:");
        System.out.println("   static final → class-level constant (one copy)");
        System.out.println("   final → instance-level constant (one per object)");
        System.out.println();
        System.out.println("✅ CORE CONCEPTS MODULE COMPLETE! 🎉");
        System.out.println("   Next module: Collections!");
        System.out.println("   Start with: com.ultimate.java.collections.A_ArrayListDeepDive");
    }
}
