package com.ultimate.java.oop;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                       ENCAPSULATION IN JAVA                                ║
 * ║              Protecting Your Data Like a Pro (Data Hiding)                 ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │                                                                           │
 * │  • A_ClassesAndObjects.java (fields, methods, objects)                   │
 * │  • B_Constructors.java (initializing objects, 'this' keyword)            │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS ENCAPSULATION?                                                    │
 * │                                                                           │
 * │  Encapsulation = WRAPPING data (fields) and code (methods) together       │
 * │  into a single unit (class), and CONTROLLING ACCESS to the data.          │
 * │                                                                           │
 * │  Think of an ATM machine:                                                │
 * │                                                                           │
 * │    ┌──────────────────────────┐                                          │
 * │    │       ATM MACHINE         │                                          │
 * │    │  ┌────────────────────┐  │                                          │
 * │    │  │  💰 Cash vault      │  │  ← PRIVATE: You can't reach in!         │
 * │    │  │  📊 Transaction log │  │  ← PRIVATE: Hidden from users           │
 * │    │  │  🔐 PIN database    │  │  ← PRIVATE: Super secret!               │
 * │    │  └────────────────────┘  │                                          │
 * │    │                          │                                          │
 * │    │  [Check Balance] button  │  ← PUBLIC: You CAN use these             │
 * │    │  [Withdraw] button       │  ← PUBLIC: Controlled access             │
 * │    │  [Deposit] button        │  ← PUBLIC: With validation!              │
 * │    └──────────────────────────┘                                          │
 * │                                                                           │
 * │  You can't grab money directly from the vault. You MUST use the          │
 * │  buttons (public methods), which have rules (validation).                │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY DO WE NEED ENCAPSULATION?                                            │
 * │                                                                           │
 * │  WITHOUT encapsulation:                                                   │
 * │    account.balance = -1000000;  // 💥 Set balance to negative million!   │
 * │    account.balance = 0;         // 💥 Reset someone's savings to 0!      │
 * │    ANYONE can mess with the data. No rules. No validation. Chaos!        │
 * │                                                                           │
 * │  WITH encapsulation:                                                      │
 * │    account.withdraw(-1000000);  // ❌ "Invalid amount!" (method rejects) │
 * │    account.setBalance(0);       // ❌ Method doesn't exist! Data safe.   │
 * │    Only controlled, validated access. Data integrity preserved.           │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class C_Encapsulation {

    // =========================================================================
    // PART 1: ACCESS MODIFIERS (The 4 Levels of Visibility)
    // =========================================================================

    // Before encapsulation, we need to understand ACCESS MODIFIERS:
    //
    // ┌─────────────┬───────┬─────────┬──────────┬───────────┐
    // │  Modifier    │ Class │ Package │ Subclass │ Everywhere│
    // ├─────────────┼───────┼─────────┼──────────┼───────────┤
    // │ public       │  ✅   │   ✅    │    ✅    │    ✅     │
    // │ protected    │  ✅   │   ✅    │    ✅    │    ❌     │
    // │ (default)*   │  ✅   │   ✅    │    ❌    │    ❌     │
    // │ private      │  ✅   │   ❌    │    ❌    │    ❌     │
    // └─────────────┴───────┴─────────┴──────────┴───────────┘
    //  * "default" = no modifier written (also called "package-private")
    //
    // Think of it as security levels:
    //   private    = Only I can see my diary 📔
    //   default    = Family members can see it 👨‍👩‍👧‍👦
    //   protected  = Extended family too 👨‍👩‍👧‍👦👴👵
    //   public     = Published on social media 🌍

    // =========================================================================
    // EXAMPLE: BAD (No encapsulation) vs GOOD (With encapsulation)
    // =========================================================================

    // ── BAD: No encapsulation ─────────────────────────────────────────────
    static class BadBankAccount {
        String ownerName;     // Anyone can access!
        double balance;       // Anyone can set to -99999!
        String pin;           // Anyone can read the PIN! 😱
    }

    // ── GOOD: Proper encapsulation ────────────────────────────────────────
    static class BankAccount {
        // PRIVATE fields — hidden from outside!
        private String ownerName;
        private double balance;
        private String pin;
        private String accountId;

        // Constructor
        BankAccount(String ownerName, String pin, double initialDeposit) {
            this.ownerName = ownerName;
            this.pin = pin;
            this.accountId = "ACC-" + System.nanoTime() % 10000;
            if (initialDeposit >= 0) {
                this.balance = initialDeposit;
            } else {
                this.balance = 0;
                System.out.println("    ⚠️ Invalid initial deposit. Set to 0.");
            }
        }

        // ─── GETTER methods (read-only access) ───────────────────────────
        // Getters let outsiders READ the data, but not change it.
        // Convention: getFieldName() for regular types, isFieldName() for boolean.

        public String getOwnerName() {
            return ownerName;
        }

        public double getBalance() {
            return balance;
            // Note: We provide getBalance() but NOT setBalance()!
            // This means balance can ONLY be changed through deposit/withdraw.
        }

        public String getAccountId() {
            return accountId;
        }

        // Notice: NO getPin()! The PIN should NEVER be readable from outside.

        // ─── SETTER methods (controlled write access) ─────────────────────
        // Setters let outsiders CHANGE data, but with VALIDATION.

        public void setOwnerName(String newName) {
            if (newName != null && !newName.trim().isEmpty()) {
                this.ownerName = newName;
            } else {
                System.out.println("    ❌ Invalid name! Name cannot be empty.");
            }
        }

        // No setBalance()! Use deposit/withdraw instead.
        // No setPin() without verification of old pin:

        public boolean changePin(String oldPin, String newPin) {
            if (this.pin.equals(oldPin)) {
                if (newPin != null && newPin.length() >= 4) {
                    this.pin = newPin;
                    System.out.println("    ✅ PIN changed successfully.");
                    return true;
                } else {
                    System.out.println("    ❌ New PIN must be at least 4 characters.");
                }
            } else {
                System.out.println("    ❌ Incorrect old PIN!");
            }
            return false;
        }

        // ─── Business logic methods (controlled operations) ───────────────
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.printf("    💰 Deposited $%.2f → Balance: $%.2f%n", amount, balance);
            } else {
                System.out.println("    ❌ Deposit amount must be positive!");
            }
        }

        public boolean withdraw(double amount, String pin) {
            if (!this.pin.equals(pin)) {
                System.out.println("    ❌ Incorrect PIN!");
                return false;
            }
            if (amount <= 0) {
                System.out.println("    ❌ Withdrawal amount must be positive!");
                return false;
            }
            if (amount > balance) {
                System.out.printf("    ❌ Insufficient funds! Balance: $%.2f%n", balance);
                return false;
            }
            balance -= amount;
            System.out.printf("    💸 Withdrew $%.2f → Balance: $%.2f%n", amount, balance);
            return true;
        }

        @Override
        public String toString() {
            return String.format("Account[%s | Owner: %s | Balance: $%.2f]",
                    accountId, ownerName, balance);
        }
    }

    // =========================================================================
    // EXAMPLE: Encapsulated Person with validation
    // =========================================================================
    static class Person {
        private String name;
        private int age;
        private String email;

        Person(String name, int age, String email) {
            setName(name);    // Use setters in constructor too — DRY!
            setAge(age);
            setEmail(email);
        }

        // Getter + Setter for name
        public String getName() { return name; }

        public void setName(String name) {
            if (name != null && name.length() >= 2) {
                this.name = name;
            } else {
                System.out.println("    ⚠️ Name must be at least 2 characters. Kept: " + this.name);
            }
        }

        // Getter + Setter for age
        public int getAge() { return age; }

        public void setAge(int age) {
            if (age >= 0 && age <= 150) {
                this.age = age;
            } else {
                System.out.println("    ⚠️ Invalid age: " + age + ". Must be 0-150.");
            }
        }

        // Getter + Setter for email
        public String getEmail() { return email; }

        public void setEmail(String email) {
            if (email != null && email.contains("@") && email.contains(".")) {
                this.email = email;
            } else {
                System.out.println("    ⚠️ Invalid email: " + email);
            }
        }

        // Derived property (calculated from fields — no need for a field!)
        public boolean isAdult() {
            return age >= 18;
        }

        @Override
        public String toString() {
            return String.format("Person[%s, age %d, %s, adult: %b]", name, age, email, isAdult());
        }
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 10: Encapsulation                          ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // DEMO 1: WHY PUBLIC FIELDS ARE DANGEROUS
        // =====================================================================
        System.out.println("━━━ DEMO 1: Why Public Fields Are Dangerous ━━━");
        System.out.println();

        BadBankAccount badAccount = new BadBankAccount();
        badAccount.ownerName = "Alice";
        badAccount.balance = 10000;
        badAccount.pin = "1234";

        // Anyone can do this — NO PROTECTION!
        badAccount.balance = -9999999;  // 💥 Negative balance!
        badAccount.pin = "";             // 💥 Empty PIN!
        System.out.println("  Bad account balance: $" + badAccount.balance + " ← NEGATIVE! No validation!");
        System.out.println("  Bad account PIN: '" + badAccount.pin + "' ← EMPTY! Anyone can read!");
        System.out.println("  This is why we need encapsulation! 🛡️");
        System.out.println();

        // =====================================================================
        // DEMO 2: ENCAPSULATED BANK ACCOUNT IN ACTION
        // =====================================================================
        System.out.println("━━━ DEMO 2: Encapsulated BankAccount ━━━");
        System.out.println();

        BankAccount account = new BankAccount("Alice", "secure123", 5000);
        System.out.println("  " + account);
        System.out.println();

        // ✅ Reading through getters — safe!
        System.out.println("  Owner: " + account.getOwnerName());
        System.out.println("  Balance: $" + account.getBalance());
        System.out.println("  Account ID: " + account.getAccountId());
        // account.pin → ❌ COMPILE ERROR! 'pin' is private!
        // account.balance = -999 → ❌ COMPILE ERROR! 'balance' is private!
        System.out.println();

        // ✅ Operations with validation:
        account.deposit(1500);
        account.deposit(-100);          // Rejected — negative!
        account.withdraw(2000, "secure123");  // Valid
        account.withdraw(2000, "wrongpin");   // Rejected — wrong PIN!
        account.withdraw(50000, "secure123"); // Rejected — insufficient funds!
        System.out.println();

        // ✅ Changing name with validation:
        account.setOwnerName("Alice Johnson");
        System.out.println("  Updated name: " + account.getOwnerName());
        account.setOwnerName("");  // Rejected!
        System.out.println();

        // ✅ Changing PIN requires old PIN:
        account.changePin("wrongpin", "newpin123");  // Rejected!
        account.changePin("secure123", "ab");         // Rejected — too short!
        account.changePin("secure123", "newSecure456"); // ✅ Success!
        System.out.println();

        // =====================================================================
        // DEMO 3: PERSON WITH VALIDATION
        // =====================================================================
        System.out.println("━━━ DEMO 3: Encapsulated Person ━━━");
        System.out.println();

        Person person = new Person("Bob", 25, "bob@email.com");
        System.out.println("  " + person);

        // Valid changes:
        person.setAge(26);
        System.out.println("  After setAge(26): " + person);

        // Invalid changes (rejected with messages):
        person.setAge(-5);         // Invalid!
        person.setAge(200);        // Invalid!
        person.setName("A");       // Too short!
        person.setEmail("bad");    // No @ symbol!
        System.out.println("  After invalid attempts: " + person + " ← unchanged!");
        System.out.println();

        // =====================================================================
        // THE RULES OF ENCAPSULATION
        // =====================================================================
        System.out.println("━━━ The Golden Rules of Encapsulation ━━━");
        System.out.println();
        System.out.println("  1️⃣  Make fields PRIVATE");
        System.out.println("     private int age;  ← hidden from outside world");
        System.out.println();
        System.out.println("  2️⃣  Provide PUBLIC getters (if reading is allowed)");
        System.out.println("     public int getAge() { return age; }");
        System.out.println();
        System.out.println("  3️⃣  Provide PUBLIC setters WITH VALIDATION (if writing is allowed)");
        System.out.println("     public void setAge(int age) {");
        System.out.println("         if (age >= 0 && age <= 150) this.age = age;");
        System.out.println("     }");
        System.out.println();
        System.out.println("  4️⃣  Some fields should have NO setter (read-only)");
        System.out.println("     Balance → only changed through deposit/withdraw");
        System.out.println("     PIN → only changed through changePin(oldPin, newPin)");
        System.out.println();
        System.out.println("  5️⃣  Some fields should have NO getter either (write-only / internal)");
        System.out.println("     PIN → never readable from outside!");
        System.out.println();

        // =====================================================================
        // ACCESS MODIFIERS REFERENCE
        // =====================================================================
        System.out.println("━━━ Access Modifiers Reference ━━━");
        System.out.println();
        System.out.println("  ┌─────────────┬───────┬─────────┬──────────┬───────────┐");
        System.out.println("  │  Modifier    │ Class │ Package │ Subclass │ World     │");
        System.out.println("  ├─────────────┼───────┼─────────┼──────────┼───────────┤");
        System.out.println("  │ private      │  ✅   │   ❌    │    ❌    │    ❌     │");
        System.out.println("  │ (default)    │  ✅   │   ✅    │    ❌    │    ❌     │");
        System.out.println("  │ protected    │  ✅   │   ✅    │    ✅    │    ❌     │");
        System.out.println("  │ public       │  ✅   │   ✅    │    ✅    │    ✅     │");
        System.out.println("  └─────────────┴───────┴─────────┴──────────┴───────────┘");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Private fields with public setter and NO validation:");
        System.out.println("   public void setAge(int age) { this.age = age; }");
        System.out.println("   ↑ This defeats the purpose! Add validation!");
        System.out.println();
        System.out.println("2. Returning mutable objects from getters:");
        System.out.println("   public List<String> getItems() { return items; }");
        System.out.println("   ↑ Caller can modify the list! Return a copy instead:");
        System.out.println("   return new ArrayList<>(items); or Collections.unmodifiableList(items);");
        System.out.println();
        System.out.println("3. Not using setters in the constructor:");
        System.out.println("   Constructor sets fields directly → bypasses validation!");
        System.out.println("   Better: call setAge(age) in the constructor too.");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("1. Create an encapsulated 'Temperature' class:");
        System.out.println("   - Private field: celsius (double)");
        System.out.println("   - Setter validates: must be >= -273.15 (absolute zero)");
        System.out.println("   - Getters for: getCelsius(), getFahrenheit(), getKelvin()");
        System.out.println();
        System.out.println("2. Create an encapsulated 'Password' class:");
        System.out.println("   - Private field: hashedPassword");
        System.out.println("   - NO getter for the password (can't read it!)");
        System.out.println("   - Method: verify(String attempt) → boolean");
        System.out.println("   - Method: changePassword(String oldPwd, String newPwd)");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: D_Inheritance.java");
    }
}
