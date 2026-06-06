package com.ultimate.java.basics;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                           ARRAYS IN JAVA                                   ║
 * ║                Your First Data Structure — Storing Multiple Things          ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ PREREQUISITES:                                                            │
 * │                                                                           │
 * │  • A_VariablesAndDataTypes.java (primitives, reference types)            │
 * │  • D_Loops.java (for loop, for-each — essential for working with arrays) │
 * │  • E_Methods.java (we'll use methods to process arrays)                  │
 * │  • The concept that ONE variable holds ONE value                          │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS AN ARRAY?                                                         │
 * │                                                                           │
 * │  An array is a CONTAINER that holds MULTIPLE values of the SAME TYPE,    │
 * │  stored in a FIXED-SIZE, ORDERED sequence.                                │
 * │                                                                           │
 * │  Think of an array like an EGG CARTON:                                    │
 * │                                                                           │
 * │    ┌─────┬─────┬─────┬─────┬─────┬─────┐                                │
 * │    │  🥚 │  🥚 │  🥚 │     │  🥚 │  🥚 │                                │
 * │    │ [0] │ [1] │ [2] │ [3] │ [4] │ [5] │ ← INDICES (positions)          │
 * │    └─────┴─────┴─────┴─────┴─────┴─────┘                                │
 * │                                                                           │
 * │  Key properties:                                                          │
 * │    • FIXED SIZE: Once you buy a 6-egg carton, you can't make it 12.      │
 * │    • SAME TYPE: All slots hold eggs. You can't put a watermelon in one.  │
 * │    • INDEXED: Each slot has a number, starting from 0 (NOT 1!).          │
 * │    • ORDERED: Slot 0 is always before slot 1.                            │
 * └─────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHY DO WE NEED ARRAYS?                                                   │
 * │                                                                           │
 * │  Without arrays:                                                          │
 * │    int student1 = 85;                                                     │
 * │    int student2 = 92;                                                     │
 * │    int student3 = 78;                                                     │
 * │    ... (imagine 500 students!)                                            │
 * │                                                                           │
 * │  With arrays:                                                             │
 * │    int[] scores = {85, 92, 78, ...};  // ALL scores in ONE variable!     │
 * │    // Process ALL scores with a simple loop!                              │
 * │                                                                           │
 * │  Arrays solve: "How do I store MANY related values without creating       │
 * │  hundreds of individual variables?"                                       │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class F_Arrays {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 6: Arrays                                  ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: CREATING ARRAYS (3 ways)
        // =====================================================================
        System.out.println("━━━ PART 1: Creating Arrays ━━━");
        System.out.println();

        // WAY 1: Declare and initialize with values (MOST COMMON)
        int[] scores = {85, 92, 78, 95, 88};
        System.out.println("Way 1: int[] scores = {85, 92, 78, 95, 88};");
        System.out.println("  → Created with 5 values. Size is fixed at 5.");

        // WAY 2: Declare with a specific size (values are default: 0 for int)
        int[] emptyScores = new int[5];  // 5 slots, all containing 0
        System.out.println("Way 2: int[] emptyScores = new int[5];");
        System.out.println("  → Created with 5 slots, all initialized to 0.");

        // WAY 3: Declare first, assign later
        int[] lateArray;
        lateArray = new int[]{10, 20, 30};
        System.out.println("Way 3: int[] lateArray; lateArray = new int[]{10, 20, 30};");
        System.out.println();

        // Array of different types:
        String[] names = {"Alice", "Bob", "Charlie"};
        double[] prices = {9.99, 24.50, 3.75};
        boolean[] attendance = {true, true, false, true};
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        System.out.println("Arrays can hold any type: String[], double[], boolean[], char[]");
        System.out.println();

        // =====================================================================
        // PART 2: ACCESSING ELEMENTS (Reading and Writing)
        // =====================================================================
        System.out.println("━━━ PART 2: Accessing Elements ━━━");
        System.out.println();

        // Access elements using their INDEX (position number)
        // ⚠️ CRITICAL: Indexing starts at 0, NOT 1!
        //
        //   scores:  {85, 92, 78, 95, 88}
        //   indices:  [0] [1] [2] [3] [4]
        //
        //   First element:  scores[0] = 85
        //   Last element:   scores[4] = 88
        //   Or:             scores[scores.length - 1] = 88

        System.out.println("  scores = {85, 92, 78, 95, 88}");
        System.out.println("  scores[0] = " + scores[0] + "  (first element — index 0!)");
        System.out.println("  scores[2] = " + scores[2] + "  (third element — index 2!)");
        System.out.println("  scores[4] = " + scores[4] + "  (last element — index 4)");
        System.out.println("  scores.length = " + scores.length + "  (total number of elements)");
        System.out.println("  scores[scores.length - 1] = " + scores[scores.length - 1] + "  (last element trick)");
        System.out.println();

        // Modifying an element:
        System.out.println("  Modifying: scores[2] = 99;");
        scores[2] = 99;
        System.out.print("  scores is now: {");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i]);
            if (i < scores.length - 1) System.out.print(", ");
        }
        System.out.println("}");
        System.out.println();

        // ⚠️ THE DREADED ArrayIndexOutOfBoundsException!
        System.out.println("  ⚠️ ArrayIndexOutOfBoundsException:");
        System.out.println("     scores[5] → 💥 CRASH! Only indices 0-4 exist.");
        System.out.println("     scores[-1] → 💥 CRASH! No negative indices in Java.");
        System.out.println("     This is the MOST COMMON array error. Always check bounds!");
        System.out.println();

        // =====================================================================
        // PART 3: ITERATING (Looping through arrays)
        // =====================================================================
        System.out.println("━━━ PART 3: Iterating Through Arrays ━━━");
        System.out.println();

        String[] fruits = {"Apple", "Banana", "Cherry", "Date", "Elderberry"};

        // Method 1: Classic for loop (when you need the index)
        System.out.println("  Method 1: Classic for loop");
        for (int i = 0; i < fruits.length; i++) {
            System.out.println("    fruits[" + i + "] = " + fruits[i]);
        }
        System.out.println();

        // Method 2: Enhanced for-each loop (when you just need values)
        System.out.println("  Method 2: For-each loop (cleaner!)");
        for (String fruit : fruits) {
            System.out.println("    🍎 " + fruit);
        }
        System.out.println();

        // =====================================================================
        // PART 4: COMMON ARRAY OPERATIONS
        // =====================================================================
        System.out.println("━━━ PART 4: Common Operations ━━━");
        System.out.println();

        int[] numbers = {34, 12, 78, 56, 23, 91, 45, 67};
        System.out.print("  numbers = ");
        printArray(numbers);
        System.out.println();

        // Finding the sum
        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        System.out.println("  Sum: " + sum);

        // Finding the average
        double average = (double) sum / numbers.length;
        System.out.printf("  Average: %.2f%n", average);

        // Finding the max
        int max = numbers[0];  // Start with first element
        for (int n : numbers) {
            if (n > max) max = n;
        }
        System.out.println("  Max: " + max);

        // Finding the min
        int min = numbers[0];
        for (int n : numbers) {
            if (n < min) min = n;
        }
        System.out.println("  Min: " + min);

        // Searching for a value
        int searchFor = 56;
        int foundIndex = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == searchFor) {
                foundIndex = i;
                break;
            }
        }
        System.out.println("  Search for " + searchFor + ": found at index " + foundIndex);

        // Counting occurrences
        int[] data = {1, 3, 5, 3, 7, 3, 9};
        int target = 3;
        int count = 0;
        for (int d : data) {
            if (d == target) count++;
        }
        System.out.println("  Count of " + target + " in {1,3,5,3,7,3,9}: " + count);
        System.out.println();

        // =====================================================================
        // PART 5: ARRAYS UTILITY CLASS (java.util.Arrays)
        // =====================================================================
        System.out.println("━━━ PART 5: java.util.Arrays (Built-in Helpers!) ━━━");
        System.out.println();

        // Java provides a utility class with handy methods:
        int[] unsorted = {34, 12, 78, 56, 23};

        // toString — print nicely
        System.out.println("  Arrays.toString(unsorted) = " + java.util.Arrays.toString(unsorted));

        // sort — sorts in ascending order (MODIFIES the array!)
        java.util.Arrays.sort(unsorted);
        System.out.println("  After Arrays.sort():      " + java.util.Arrays.toString(unsorted));

        // binarySearch — find element in a SORTED array (must sort first!)
        int idx = java.util.Arrays.binarySearch(unsorted, 56);
        System.out.println("  Arrays.binarySearch(56):   found at index " + idx);

        // fill — fill entire array with a value
        int[] filled = new int[5];
        java.util.Arrays.fill(filled, 42);
        System.out.println("  Arrays.fill(arr, 42):      " + java.util.Arrays.toString(filled));

        // equals — compare two arrays (don't use == for arrays!)
        int[] a1 = {1, 2, 3};
        int[] a2 = {1, 2, 3};
        int[] a3 = {1, 2, 4};
        System.out.println("  {1,2,3} equals {1,2,3}?    " + java.util.Arrays.equals(a1, a2));
        System.out.println("  {1,2,3} equals {1,2,4}?    " + java.util.Arrays.equals(a1, a3));
        System.out.println("  ⚠️  a1 == a2 is " + (a1 == a2) + " (compares REFERENCES, not content!)");

        // copyOf — create a copy
        int[] original = {10, 20, 30};
        int[] copy = java.util.Arrays.copyOf(original, original.length);
        System.out.println("  Arrays.copyOf():           " + java.util.Arrays.toString(copy));
        System.out.println();

        // =====================================================================
        // PART 6: 2D ARRAYS (Arrays of arrays!)
        // =====================================================================
        System.out.println("━━━ PART 6: 2D Arrays (Grids/Tables) ━━━");
        System.out.println();

        // A 2D array is an array where EACH ELEMENT is itself an array.
        // Think: A spreadsheet, chess board, or seating chart.
        //
        //              Column 0  Column 1  Column 2
        //   Row 0  →   [ 1,        2,        3 ]
        //   Row 1  →   [ 4,        5,        6 ]
        //   Row 2  →   [ 7,        8,        9 ]

        int[][] grid = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("  2D Grid:");
        for (int[] row : grid) {
            System.out.print("    ");
            for (int cell : row) {
                System.out.printf("%3d", cell);
            }
            System.out.println();
        }
        System.out.println();

        // Accessing 2D array elements:
        System.out.println("  grid[0][0] = " + grid[0][0] + "  (row 0, col 0 → top-left)");
        System.out.println("  grid[1][2] = " + grid[1][2] + "  (row 1, col 2)");
        System.out.println("  grid[2][2] = " + grid[2][2] + "  (row 2, col 2 → bottom-right)");
        System.out.println("  grid.length = " + grid.length + "  (number of rows)");
        System.out.println("  grid[0].length = " + grid[0].length + "  (number of columns in row 0)");
        System.out.println();

        // =====================================================================
        // PART 7: JAGGED ARRAYS (Rows with different lengths!)
        // =====================================================================
        System.out.println("━━━ PART 7: Jagged Arrays ━━━");
        System.out.println();

        // In Java, each row can have a DIFFERENT length!
        // Think: A tournament bracket where each round has fewer teams.

        int[][] jagged = {
            {1, 2, 3, 4, 5},    // Row 0: 5 elements
            {6, 7, 8},          // Row 1: 3 elements
            {9, 10},            // Row 2: 2 elements
            {11}                // Row 3: 1 element
        };

        System.out.println("  Jagged array (rows have different lengths):");
        for (int i = 0; i < jagged.length; i++) {
            System.out.println("    Row " + i + " (" + jagged[i].length + " elements): "
                    + java.util.Arrays.toString(jagged[i]));
        }
        System.out.println();

        // =====================================================================
        // PART 8: ARRAYS AND METHODS
        // =====================================================================
        System.out.println("━━━ PART 8: Passing Arrays to Methods ━━━");
        System.out.println();

        // ⚠️ CRITICAL CONCEPT:
        // When you pass an array to a method, you pass a REFERENCE to the array.
        // This means the method CAN MODIFY the original array!
        // (Unlike primitives which pass a copy.)

        int[] myArray = {10, 20, 30};
        System.out.println("  Before method call: " + java.util.Arrays.toString(myArray));
        doubleAllElements(myArray);
        System.out.println("  After method call:  " + java.util.Arrays.toString(myArray));
        System.out.println("  → The original array WAS modified! Arrays pass by reference value.");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. ArrayIndexOutOfBoundsException:");
        System.out.println("   Array of size 5 → valid indices: 0, 1, 2, 3, 4");
        System.out.println("   scores[5] → 💥 CRASH! Off by one error.");
        System.out.println();
        System.out.println("2. Comparing arrays with ==:");
        System.out.println("   arr1 == arr2  → compares REFERENCES (are they the same object?)");
        System.out.println("   Arrays.equals(arr1, arr2)  → compares CONTENT ✅");
        System.out.println();
        System.out.println("3. Printing arrays directly:");
        System.out.println("   System.out.println(arr) → prints weird '[I@1234' (memory address!)");
        System.out.println("   System.out.println(Arrays.toString(arr)) → prints nicely ✅");
        System.out.println();
        System.out.println("4. Forgetting arrays are FIXED SIZE:");
        System.out.println("   You can't add or remove elements. Need flexibility? → Use ArrayList!");
        System.out.println("   (We'll cover ArrayList in the Collections module!)");
        System.out.println();
        System.out.println("5. Confusing length vs length():");
        System.out.println("   array.length  → NO parentheses (it's a field)");
        System.out.println("   string.length() → WITH parentheses (it's a method)");
        System.out.println();

        // =====================================================================
        // 🏋️ MINI CHALLENGE
        // =====================================================================
        System.out.println("━━━ 🏋️ MINI CHALLENGE ━━━");
        System.out.println();
        System.out.println("1. Reverse an array: {1,2,3,4,5} → {5,4,3,2,1}");
        System.out.println("2. Find the second largest element in an array.");
        System.out.println("3. Remove duplicates from {1,3,5,3,7,1,9,5} → {1,3,5,7,9}");
        System.out.println("4. Rotate an array left by 2: {1,2,3,4,5} → {3,4,5,1,2}");
        System.out.println("5. Check if two arrays are equal (without using Arrays.equals).");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: G_Strings.java");
    }

    // ── Helper methods ──

    static void printArray(int[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("}");
    }

    static void doubleAllElements(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] * 2;  // Modifies the ORIGINAL array!
        }
    }
}
