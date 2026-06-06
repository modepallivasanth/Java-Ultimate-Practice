package com.ultimate.java.advanced;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                      OPTIONAL CLASS IN JAVA                                ║
 * ║              No More NullPointerException — Handle Absence Elegantly      ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS OPTIONAL?                                                         │
 * │                                                                           │
 * │  Optional<T> is a container that may or may not contain a value.         │
 * │  It forces you to explicitly handle the "no value" case.                 │
 * │                                                                           │
 * │  Think: A gift box 🎁 that might be empty or contain a present.          │
 * │    Before opening, you CHECK: "Is there something inside?"               │
 * │    You don't blindly reach in (that would be like calling .get() on null)│
 * │                                                                           │
 * │  WHY?                                                                     │
 * │    NullPointerException is the #1 most common Java exception.            │
 * │    Optional makes "might be null" VISIBLE in the type system.            │
 * │    Instead of: String name = user.getName();   ← might be null!          │
 * │    You write:  Optional<String> name = user.getName();  ← explicitly!   │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class D_OptionalClass {

    // Simulated database
    record User(String name, String email, Optional<String> phone) {}

    static Map<Integer, User> userDb = Map.of(
        1, new User("Alice", "alice@email.com", Optional.of("555-0101")),
        2, new User("Bob", "bob@email.com", Optional.empty()),
        3, new User("Charlie", "charlie@email.com", Optional.of("555-0303"))
    );

    static Optional<User> findUser(int id) {
        return Optional.ofNullable(userDb.get(id));
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 28: Optional Class                         ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: CREATING OPTIONALS
        // =====================================================================
        System.out.println("━━━ PART 1: Creating Optionals ━━━");
        System.out.println();

        // Of (MUST be non-null)
        Optional<String> present = Optional.of("Hello");
        System.out.println("  Optional.of(\"Hello\"):   " + present);

        // Empty
        Optional<String> empty = Optional.empty();
        System.out.println("  Optional.empty():       " + empty);

        // OfNullable (can be null or not)
        String maybeNull = null;
        Optional<String> maybe = Optional.ofNullable(maybeNull);
        System.out.println("  Optional.ofNullable(null): " + maybe);

        Optional<String> maybe2 = Optional.ofNullable("World");
        System.out.println("  Optional.ofNullable(\"World\"): " + maybe2);
        System.out.println();

        // =====================================================================
        // PART 2: CHECKING & EXTRACTING VALUES
        // =====================================================================
        System.out.println("━━━ PART 2: Checking & Extracting ━━━");
        System.out.println();

        Optional<String> opt = Optional.of("Java");

        // isPresent / isEmpty
        System.out.println("  opt.isPresent() = " + opt.isPresent());
        System.out.println("  opt.isEmpty()   = " + opt.isEmpty() + "  (Java 11+)");

        // get() — DANGEROUS! Throws NoSuchElementException if empty!
        System.out.println("  opt.get()       = " + opt.get());

        // orElse — provide a default value
        String val1 = empty.orElse("Default Value");
        System.out.println("  empty.orElse(\"Default\") = " + val1);

        // orElseGet — provide default via Supplier (lazy evaluation)
        String val2 = empty.orElseGet(() -> "Computed " + (2 + 2));
        System.out.println("  empty.orElseGet(supplier) = " + val2);

        // orElseThrow — throw if empty
        try {
            empty.orElseThrow(() -> new RuntimeException("No value!"));
        } catch (RuntimeException e) {
            System.out.println("  empty.orElseThrow() → " + e.getMessage());
        }
        System.out.println();

        // =====================================================================
        // PART 3: TRANSFORMING OPTIONALS
        // =====================================================================
        System.out.println("━━━ PART 3: Transforming with map/flatMap/filter ━━━");
        System.out.println();

        Optional<String> name = Optional.of("alice");

        // map — transform the value if present
        Optional<String> upper = name.map(String::toUpperCase);
        System.out.println("  name.map(toUpperCase) = " + upper);

        Optional<Integer> length = name.map(String::length);
        System.out.println("  name.map(length)      = " + length);

        // map on empty → still empty!
        Optional<String> emptyMapped = empty.map(String::toUpperCase);
        System.out.println("  empty.map(toUpperCase) = " + emptyMapped + "  (stays empty!)");

        // filter — keep value only if it matches condition
        Optional<String> filtered = name.filter(n -> n.startsWith("a"));
        System.out.println("  name.filter(starts with 'a') = " + filtered);

        Optional<String> filtered2 = name.filter(n -> n.startsWith("z"));
        System.out.println("  name.filter(starts with 'z') = " + filtered2 + "  (no match → empty)");

        // flatMap — when the mapper returns an Optional (avoids Optional<Optional<>>)
        Optional<String> phone = findUser(1).flatMap(u -> u.phone());
        System.out.println("  User 1 phone: " + phone);

        Optional<String> noPhone = findUser(2).flatMap(u -> u.phone());
        System.out.println("  User 2 phone: " + noPhone + "  (Bob has no phone)");
        System.out.println();

        // =====================================================================
        // PART 4: OPTIONAL IN ACTION
        // =====================================================================
        System.out.println("━━━ PART 4: Practical Examples ━━━");
        System.out.println();

        // Finding a user and getting their info safely
        String userName = findUser(1)
            .map(User::name)
            .map(String::toUpperCase)
            .orElse("UNKNOWN");
        System.out.println("  User 1 name: " + userName);

        String missingUser = findUser(99)
            .map(User::name)
            .orElse("User not found");
        System.out.println("  User 99 name: " + missingUser);

        // ifPresent — do something only if present
        System.out.print("  User 1 info: ");
        findUser(1).ifPresent(u ->
            System.out.println(u.name() + " (" + u.email() + ")")
        );

        // ifPresentOrElse (Java 9+)
        System.out.print("  User 99 info: ");
        findUser(99).ifPresentOrElse(
            u -> System.out.println(u.name()),
            () -> System.out.println("Not found!")
        );
        System.out.println();

        // Streams + Optional
        List<Optional<String>> optionals = List.of(
            Optional.of("A"), Optional.empty(), Optional.of("B"), Optional.empty(), Optional.of("C")
        );

        List<String> presentValues = optionals.stream()
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
        System.out.println("  Filtered optionals: " + presentValues);

        // Java 9: Optional.stream()
        List<String> flatMapped = optionals.stream()
            .flatMap(Optional::stream)
            .collect(Collectors.toList());
        System.out.println("  flatMap optionals:  " + flatMapped);
        System.out.println();

        // =====================================================================
        // BEST PRACTICES
        // =====================================================================
        System.out.println("━━━ Best Practices ━━━");
        System.out.println();
        System.out.println("  ✅ DO:");
        System.out.println("     • Use as return type for methods that might not return a value");
        System.out.println("     • Use orElse/orElseGet/orElseThrow instead of get()");
        System.out.println("     • Use map/flatMap for transformations");
        System.out.println("     • Use ifPresent for side effects");
        System.out.println();
        System.out.println("  ❌ DON'T:");
        System.out.println("     • Use as method parameter (just use the type directly)");
        System.out.println("     • Use as class field (use the type directly)");
        System.out.println("     • Call get() without checking isPresent() first");
        System.out.println("     • Use Optional.of() with a value that might be null");
        System.out.println("     • Return null from a method that returns Optional");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: E_RecordsAndModernJava.java");
    }
}
