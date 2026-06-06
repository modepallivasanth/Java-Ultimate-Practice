package com.ultimate.java.basics;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                          STRINGS IN JAVA                                   ║
 * ║          The Most Used Data Type — Text Manipulation Mastery               ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │                                                                           │
 * │  • A_VariablesAndDataTypes.java (String basics, char type)               │
 * │  • B_Operators.java (+ for concatenation, == vs .equals())               │
 * │  • D_Loops.java (for iterating through characters)                       │
 * │  • E_Methods.java (String methods!)                                      │
 * │  • F_Arrays.java (char arrays relate to Strings)                         │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS A STRING?                                                         │
 * │                                                                           │
 * │  A String is a SEQUENCE OF CHARACTERS.                                    │
 * │                                                                           │
 * │  Think of a String like a NECKLACE OF BEADS:                              │
 * │                                                                           │
 * │    "Hello" = [H] → [e] → [l] → [l] → [o]                                │
 * │     Index:   [0]   [1]   [2]   [3]   [4]                                │
 * │                                                                           │
 * │  Each bead is a character (char), and together they form a String.        │
 * │  Just like beads on a necklace, you can:                                  │
 * │    • Count them (length)                                                  │
 * │    • Pick a specific one (charAt)                                         │
 * │    • Cut a section (substring)                                            │
 * │    • Replace a bead (replace)                                             │
 * │    • But you CAN'T change the necklace itself — you make a NEW one!      │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY ARE STRINGS SPECIAL?                                                  │
 * │                                                                           │
 * │  1. IMMUTABLE: Once created, a String can NEVER be changed.              │
 * │     "Hello" will always be "Hello". If you "modify" it, Java creates     │
 * │     a BRAND NEW String object.                                            │
 * │                                                                           │
 * │  2. STRING POOL: Java keeps a special area in memory called the          │
 * │     "String Pool" to REUSE Strings and save memory.                      │
 * │                                                                           │
 * │  3. MOST USED: Strings are used more than any other object type.          │
 * │     User names, messages, file paths, URLs, JSON — all Strings!          │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class G_Strings {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 7: Strings                                ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: CREATING STRINGS (2 ways — and why it matters!)
        // =====================================================================
        System.out.println("━━━ PART 1: Creating Strings ━━━");
        System.out.println();

        // Way 1: String literal (uses String Pool) — PREFERRED
        String s1 = "Hello";
        String s2 = "Hello";  // Points to the SAME object in the pool!
        System.out.println("  s1 = \"Hello\", s2 = \"Hello\"");
        System.out.println("  s1 == s2?       " + (s1 == s2) + "  ← Same object in String Pool!");
        System.out.println("  s1.equals(s2)?  " + s1.equals(s2) + "  ← Same content (always use this!)");
        System.out.println();

        // Way 2: new String() (creates new object on heap) — AVOID unless necessary
        String s3 = new String("Hello");
        System.out.println("  s3 = new String(\"Hello\")");
        System.out.println("  s1 == s3?       " + (s1 == s3) + " ← DIFFERENT objects (even same text!)");
        System.out.println("  s1.equals(s3)?  " + s1.equals(s3) + "  ← Same content ✅");
        System.out.println();

        // THE STRING POOL (visualized):
        System.out.println("  📦 The String Pool:");
        System.out.println("  ┌────────────── HEAP MEMORY ──────────────────────┐");
        System.out.println("  │                                                  │");
        System.out.println("  │  ┌────── String Pool ──────┐                    │");
        System.out.println("  │  │  \"Hello\" ← s1, s2 both  │    new String()   │");
        System.out.println("  │  │           point here     │    \"Hello\" ← s3   │");
        System.out.println("  │  │  \"World\"                 │    (separate copy) │");
        System.out.println("  │  └─────────────────────────┘                    │");
        System.out.println("  └──────────────────────────────────────────────────┘");
        System.out.println("  Literals share → less memory. new String() → wasteful copy.");
        System.out.println();

        // =====================================================================
        // PART 2: STRING METHODS (Your Swiss Army Knife!)
        // =====================================================================
        System.out.println("━━━ PART 2: Essential String Methods ━━━");
        System.out.println();

        String text = "  Hello, World!  ";
        String name = "Java Programming";

        // --- Length ---
        System.out.println("  text = \"" + text + "\"");
        System.out.println("  name = \"" + name + "\"");
        System.out.println();

        System.out.println("  name.length()    = " + name.length() + "  (count of characters)");

        // --- Character access ---
        System.out.println("  name.charAt(0)   = '" + name.charAt(0) + "' (first character)");
        System.out.println("  name.charAt(5)   = '" + name.charAt(5) + "' (6th character)");

        // --- Case conversion ---
        System.out.println("  name.toUpperCase() = \"" + name.toUpperCase() + "\"");
        System.out.println("  name.toLowerCase() = \"" + name.toLowerCase() + "\"");

        // --- Trimming whitespace ---
        System.out.println("  text.trim()       = \"" + text.trim() + "\"  (removes leading/trailing spaces)");
        System.out.println("  text.strip()      = \"" + text.strip() + "\"  (Java 11+, handles Unicode spaces)");

        // --- Searching ---
        System.out.println("  name.indexOf(\"Pro\")    = " + name.indexOf("Pro") + "  (starts at index 5)");
        System.out.println("  name.indexOf(\"Python\") = " + name.indexOf("Python") + " (not found → -1)");
        System.out.println("  name.contains(\"Java\")  = " + name.contains("Java"));
        System.out.println("  name.startsWith(\"Java\") = " + name.startsWith("Java"));
        System.out.println("  name.endsWith(\"ing\")    = " + name.endsWith("ing"));

        // --- Substring ---
        System.out.println("  name.substring(5)      = \"" + name.substring(5) + "\"  (from index 5 to end)");
        System.out.println("  name.substring(0, 4)   = \"" + name.substring(0, 4) + "\"  (index 0 to 3, exclusive end!)");

        // --- Replacing ---
        System.out.println("  name.replace(\"Java\", \"Python\") = \"" + name.replace("Java", "Python") + "\"");

        // --- Splitting ---
        String csv = "apple,banana,cherry,date";
        String[] parts = csv.split(",");
        System.out.println("  \"" + csv + "\".split(\",\") = "
                + java.util.Arrays.toString(parts));

        // --- Joining ---
        String joined = String.join(" | ", parts);
        System.out.println("  String.join(\" | \", parts) = \"" + joined + "\"");

        // --- Checking content ---
        System.out.println("  \"\".isEmpty()     = " + "".isEmpty() + "  (empty string)");
        System.out.println("  \"  \".isBlank()   = " + "  ".isBlank() + "  (Java 11+, whitespace-only)");
        System.out.println();

        // =====================================================================
        // PART 3: IMMUTABILITY (Why it matters!)
        // =====================================================================
        System.out.println("━━━ PART 3: Immutability — Strings Never Change ━━━");
        System.out.println();

        // CRITICAL CONCEPT:
        // When you "modify" a String, you're actually creating a NEW String.
        // The original is untouched!

        String original = "Hello";
        String modified = original.toUpperCase();

        System.out.println("  original = \"" + original + "\"");
        System.out.println("  modified = original.toUpperCase() → \"" + modified + "\"");
        System.out.println("  original is STILL \"" + original + "\" ← unchanged! Immutable!");
        System.out.println();

        // This means: String operations RETURN new Strings.
        // A common mistake:
        System.out.println("  ⚠️ Common mistake:");
        System.out.println("     String s = \"hello\";");
        System.out.println("     s.toUpperCase();  ← This does NOTHING to s!");
        System.out.println("     // You must capture the result:");
        System.out.println("     s = s.toUpperCase();  ← NOW s is \"HELLO\"");
        System.out.println();

        // =====================================================================
        // PART 4: STRING CONCATENATION (Combining Strings)
        // =====================================================================
        System.out.println("━━━ PART 4: String Concatenation ━━━");
        System.out.println();

        // Way 1: + operator
        String firstName = "John";
        String lastName = "Doe";
        String fullName = firstName + " " + lastName;
        System.out.println("  + operator: \"" + fullName + "\"");

        // Way 2: concat()
        String greeting = "Hello, ".concat(fullName).concat("!");
        System.out.println("  concat():   \"" + greeting + "\"");

        // Way 3: String.format() (like printf for Strings)
        String formatted = String.format("Name: %s, Age: %d, GPA: %.2f", "Alice", 20, 3.85);
        System.out.println("  format():   \"" + formatted + "\"");

        // Way 4: Text blocks (Java 13+) — multiline Strings
        String textBlock = """
                {
                    "name": "Alice",
                    "age": 20,
                    "language": "Java"
                }
                """;
        System.out.println("  Text block (Java 13+):");
        System.out.print(textBlock);
        System.out.println();

        // ⚠️ PERFORMANCE WARNING about + in loops:
        System.out.println("  ⚠️ NEVER use + in a loop for large concatenations!");
        System.out.println("     Each + creates a NEW String object → very slow!");
        System.out.println("     Use StringBuilder instead (see Part 5).");
        System.out.println();

        // =====================================================================
        // PART 5: STRINGBUILDER (For when you need to modify text a LOT)
        // =====================================================================
        System.out.println("━━━ PART 5: StringBuilder — The Mutable String ━━━");
        System.out.println();

        // StringBuilder is like String's MUTABLE twin.
        // It CAN be modified without creating new objects.
        //
        // Think: String = writing with a PEN on paper (permanent)
        //        StringBuilder = writing on a WHITEBOARD (erasable, changeable)
        //
        // Use StringBuilder when you're building a String piece by piece,
        // especially inside a loop.

        // BAD: String concatenation in a loop (creates 1000 objects!)
        // String result = "";
        // for (int i = 0; i < 1000; i++) {
        //     result += i;  // Creates a NEW String each time!
        // }

        // GOOD: StringBuilder (modifies ONE object)
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            sb.append(i);
            if (i < 5) sb.append(" → ");
        }
        String result = sb.toString();  // Convert to String when done
        System.out.println("  StringBuilder result: " + result);

        // StringBuilder methods:
        StringBuilder builder = new StringBuilder("Hello");
        System.out.println("  Start:    \"" + builder + "\"");

        builder.append(" World");
        System.out.println("  append:   \"" + builder + "\"");

        builder.insert(5, ",");
        System.out.println("  insert:   \"" + builder + "\"");

        builder.replace(7, 12, "Java");
        System.out.println("  replace:  \"" + builder + "\"");

        builder.delete(5, 6);
        System.out.println("  delete:   \"" + builder + "\"");

        builder.reverse();
        System.out.println("  reverse:  \"" + builder + "\"");
        System.out.println();

        // StringBuilder vs StringBuffer:
        System.out.println("  📋 StringBuilder vs StringBuffer:");
        System.out.println("  ┌───────────────┬──────────────────┬──────────────────┐");
        System.out.println("  │               │  StringBuilder   │  StringBuffer    │");
        System.out.println("  ├───────────────┼──────────────────┼──────────────────┤");
        System.out.println("  │ Thread-safe?  │  ❌ No           │  ✅ Yes          │");
        System.out.println("  │ Speed         │  ✅ Faster       │  ❌ Slower       │");
        System.out.println("  │ When to use   │  Single-threaded │  Multi-threaded  │");
        System.out.println("  │ Recommended   │  ★ Usually this  │  Only if needed  │");
        System.out.println("  └───────────────┴──────────────────┴──────────────────┘");
        System.out.println();

        // =====================================================================
        // PART 6: STRING COMPARISON (== vs .equals() — explained once and for all!)
        // =====================================================================
        System.out.println("━━━ PART 6: String Comparison (The == Trap!) ━━━");
        System.out.println();

        // THE GOLDEN RULE:
        // == compares REFERENCES (are they the same object in memory?)
        // .equals() compares CONTENT (do they contain the same text?)
        //
        // ALWAYS use .equals() for String comparison!

        String a = "Hello";
        String b = "Hello";
        String c = new String("Hello");
        String d = "HELLO";

        System.out.println("  a = \"Hello\" (literal)");
        System.out.println("  b = \"Hello\" (literal)");
        System.out.println("  c = new String(\"Hello\")");
        System.out.println("  d = \"HELLO\"");
        System.out.println();

        System.out.println("  a == b              → " + (a == b) + "  (same pool object)");
        System.out.println("  a == c              → " + (a == c) + " (different objects!)");
        System.out.println("  a.equals(b)         → " + a.equals(b) + "  (same content ✅)");
        System.out.println("  a.equals(c)         → " + a.equals(c) + "  (same content ✅)");
        System.out.println("  a.equals(d)         → " + a.equals(d) + " (different case!)");
        System.out.println("  a.equalsIgnoreCase(d) → " + a.equalsIgnoreCase(d) + "  (ignores case ✅)");
        System.out.println();

        // compareTo for ordering:
        System.out.println("  \"apple\".compareTo(\"banana\") = " + "apple".compareTo("banana") + " (negative → apple comes first)");
        System.out.println("  \"banana\".compareTo(\"apple\") = " + "banana".compareTo("apple") + "  (positive → banana comes after)");
        System.out.println("  \"apple\".compareTo(\"apple\")  = " + "apple".compareTo("apple") + "  (zero → they're equal)");
        System.out.println();

        // =====================================================================
        // PART 7: STRING FORMATTING (printf-style)
        // =====================================================================
        System.out.println("━━━ PART 7: String Formatting ━━━");
        System.out.println();

        // Format specifiers:
        // %s → String
        // %d → integer (decimal)
        // %f → floating point
        // %n → newline (platform-independent)
        // %b → boolean
        // %c → character
        // %% → literal percent sign

        System.out.printf("  %%s (String):  Name is %s%n", "Alice");
        System.out.printf("  %%d (Integer): Age is %d%n", 25);
        System.out.printf("  %%f (Float):   PI is %f%n", Math.PI);
        System.out.printf("  %%.2f (2 dec): PI is %.2f%n", Math.PI);
        System.out.printf("  %%10s (padded): [%10s]%n", "right");
        System.out.printf("  %%-10s (left):  [%-10s]%n", "left");
        System.out.printf("  %%05d (zero-pad): %05d%n", 42);
        System.out.println();

        // =====================================================================
        // PART 8: USEFUL STRING RECIPES
        // =====================================================================
        System.out.println("━━━ PART 8: Useful Recipes ━━━");
        System.out.println();

        // Recipe 1: Check if palindrome
        String word = "racecar";
        String reversed = new StringBuilder(word).reverse().toString();
        boolean isPalin = word.equals(reversed);
        System.out.println("  Is \"" + word + "\" a palindrome? " + isPalin);

        // Recipe 2: Count vowels
        String sentence = "Hello World";
        int vowelCount = 0;
        for (char ch : sentence.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(ch) != -1) vowelCount++;
        }
        System.out.println("  Vowels in \"" + sentence + "\": " + vowelCount);

        // Recipe 3: Capitalize first letter of each word
        String input = "hello world from java";
        StringBuilder capitalized = new StringBuilder();
        for (String w : input.split(" ")) {
            capitalized.append(Character.toUpperCase(w.charAt(0)))
                       .append(w.substring(1))
                       .append(" ");
        }
        System.out.println("  Capitalize: \"" + capitalized.toString().trim() + "\"");

        // Recipe 4: Extract numbers from a string
        String mixed = "abc123def456";
        String numbersOnly = mixed.replaceAll("[^0-9]", "");
        System.out.println("  Numbers from \"" + mixed + "\": \"" + numbersOnly + "\"");

        // Recipe 5: Repeat a string
        String repeated = "Ha".repeat(3);  // Java 11+
        System.out.println("  \"Ha\".repeat(3) = \"" + repeated + "\"");
        System.out.println();

        // =====================================================================
        // PART 9: CHAR ARRAY ↔ STRING CONVERSION
        // =====================================================================
        System.out.println("━━━ PART 9: char[] ↔ String ━━━");
        System.out.println();

        // String → char array
        String hello = "Hello";
        char[] chars = hello.toCharArray();
        System.out.println("  \"Hello\".toCharArray() = " + java.util.Arrays.toString(chars));

        // char array → String
        char[] letters = {'J', 'a', 'v', 'a'};
        String fromChars = new String(letters);
        System.out.println("  new String({'J','a','v','a'}) = \"" + fromChars + "\"");

        // Also: String.valueOf(charArray)
        System.out.println("  String.valueOf(letters) = \"" + String.valueOf(letters) + "\"");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Using == instead of .equals():");
        System.out.println("   ALWAYS use .equals() for String content comparison!");
        System.out.println();
        System.out.println("2. Ignoring return values (immutability!):");
        System.out.println("   str.toUpperCase();     ← does nothing to str!");
        System.out.println("   str = str.toUpperCase(); ← correct!");
        System.out.println();
        System.out.println("3. NullPointerException:");
        System.out.println("   String s = null;");
        System.out.println("   s.length() → 💥 NullPointerException!");
        System.out.println("   Fix: check (s != null) first, or use \"literal\".equals(s)");
        System.out.println();
        System.out.println("4. Concatenating in a loop:");
        System.out.println("   String s = \"\"; for(...) s += x;  ← SLOW! O(n²)");
        System.out.println("   Use StringBuilder for loop concatenation!");
        System.out.println();
        System.out.println("5. Confusing length vs length():");
        System.out.println("   array.length  ← field (no parentheses)");
        System.out.println("   string.length() ← method (with parentheses)");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("1. Count how many times a word appears in a sentence.");
        System.out.println("2. Implement a simple Caesar cipher (shift each letter by N).");
        System.out.println("3. Check if two strings are anagrams ('listen' ↔ 'silent').");
        System.out.println("4. Find the longest word in a sentence.");
        System.out.println("5. Compress a string: 'aaabbbcc' → 'a3b3c2'");
        System.out.println();
        System.out.println("✅ BASICS MODULE COMPLETE! 🎉");
        System.out.println("   Next module: Object-Oriented Programming (OOP)!");
        System.out.println("   Start with: com.ultimate.java.oop.A_ClassesAndObjects");
    }
}
