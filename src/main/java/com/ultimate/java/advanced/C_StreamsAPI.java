package com.ultimate.java.advanced;

import java.util.*;
import java.util.stream.*;

/**
 * ╔══════════════════════════════════════════════════════════════════════════════╗
 * ║                       STREAMS API IN JAVA                                  ║
 * ║          Data Pipelines — Filter, Transform, Collect Like a Pro           ║
 * ╚══════════════════════════════════════════════════════════════════════════════╝
 *
 * ┌─────────────────────────────────────────────────────────────────────────────┐
 * │ WHAT IS A STREAM?                                                         │
 * │                                                                           │
 * │  A Stream is a PIPELINE for processing data — filter, transform, collect.│
 * │                                                                           │
 * │  Think: A FACTORY ASSEMBLY LINE 🏭                                       │
 * │    Raw materials → Filter → Shape → Paint → Package → Products          │
 * │                                                                           │
 * │  data.stream()                  ← create the conveyor belt               │
 * │      .filter(x -> x > 0)       ← remove negatives (intermediate)        │
 * │      .map(x -> x * x)          ← square each number (intermediate)      │
 * │      .sorted()                  ← sort them (intermediate)               │
 * │      .collect(Collectors.toList()) ← package the results (terminal)     │
 * │                                                                           │
 * │  IMPORTANT: Streams are LAZY — intermediate ops don't execute until     │
 * │  a terminal operation is called! Think: the factory doesn't start        │
 * │  until someone places an ORDER.                                          │
 * └─────────────────────────────────────────────────────────────────────────────┘
 */
public class C_StreamsAPI {

    record Employee(String name, String department, double salary) {}

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   LESSON 27: Streams API                            ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // PART 1: CREATING STREAMS
        // =====================================================================
        System.out.println("━━━ PART 1: Creating Streams ━━━");
        System.out.println();

        // From collection:
        List<String> names = List.of("Alice", "Bob", "Charlie", "Diana", "Eve");
        Stream<String> nameStream = names.stream();

        // From array:
        int[] arr = {1, 2, 3, 4, 5};
        IntStream arrStream = Arrays.stream(arr);

        // Using Stream.of():
        Stream<String> ofStream = Stream.of("X", "Y", "Z");

        // Generate infinite stream:
        Stream<Double> randoms = Stream.generate(Math::random).limit(3);

        // Stream.iterate:
        Stream<Integer> counting = Stream.iterate(1, n -> n + 1).limit(5);

        // IntStream range:
        IntStream range = IntStream.rangeClosed(1, 5);

        System.out.println("  names.stream() → process elements of a list");
        System.out.println("  Stream.of(\"X\",\"Y\",\"Z\") → from values");
        System.out.println("  Stream.generate(Math::random).limit(3) → infinite, capped at 3");
        System.out.println("  IntStream.rangeClosed(1,5): ");
        IntStream.rangeClosed(1, 5).forEach(n -> System.out.print("    " + n));
        System.out.println();
        System.out.println();

        // =====================================================================
        // PART 2: INTERMEDIATE OPERATIONS (Lazy! Return a stream)
        // =====================================================================
        System.out.println("━━━ PART 2: Intermediate Operations ━━━");
        System.out.println();

        List<Integer> numbers = List.of(5, 3, 8, 1, 9, 2, 7, 4, 6, 3, 5);

        // filter — keep elements that match a condition
        List<Integer> evens = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("  filter(even): " + evens);

        // map — transform each element
        List<Integer> squared = numbers.stream()
            .map(n -> n * n)
            .collect(Collectors.toList());
        System.out.println("  map(n²):      " + squared);

        // distinct — remove duplicates
        List<Integer> unique = numbers.stream()
            .distinct()
            .collect(Collectors.toList());
        System.out.println("  distinct():   " + unique);

        // sorted
        List<Integer> sorted = numbers.stream()
            .sorted()
            .collect(Collectors.toList());
        System.out.println("  sorted():     " + sorted);

        // limit & skip
        List<Integer> topThree = numbers.stream()
            .sorted(Comparator.reverseOrder())
            .limit(3)
            .collect(Collectors.toList());
        System.out.println("  top 3:        " + topThree);

        // peek — debug/inspect (doesn't modify)
        System.out.print("  peek demo:    ");
        numbers.stream()
            .filter(n -> n > 5)
            .peek(n -> System.out.print("[" + n + "] "))
            .count();  // terminal op triggers the pipeline
        System.out.println();

        // Chaining multiple operations
        List<String> result = names.stream()
            .filter(name -> name.length() > 3)    // Keep names longer than 3
            .map(String::toUpperCase)              // Convert to uppercase
            .sorted()                               // Sort alphabetically
            .collect(Collectors.toList());
        System.out.println("  chained:      " + result);
        System.out.println();

        // =====================================================================
        // PART 3: TERMINAL OPERATIONS (Trigger execution!)
        // =====================================================================
        System.out.println("━━━ PART 3: Terminal Operations ━━━");
        System.out.println();

        // collect — most versatile, gather results into a collection
        List<String> list = names.stream().collect(Collectors.toList());
        Set<String> set = names.stream().collect(Collectors.toSet());
        String joined = names.stream().collect(Collectors.joining(", "));
        System.out.println("  toList:   " + list);
        System.out.println("  toSet:    " + set);
        System.out.println("  joining:  " + joined);

        // forEach — perform action on each element
        System.out.print("  forEach:  ");
        names.stream().forEach(n -> System.out.print(n + " "));
        System.out.println();

        // count, min, max, sum, average
        long count = numbers.stream().distinct().count();
        Optional<Integer> max = numbers.stream().max(Integer::compare);
        Optional<Integer> min = numbers.stream().min(Integer::compare);
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        double avg = numbers.stream().mapToInt(Integer::intValue).average().orElse(0);

        System.out.println("  count:    " + count);
        System.out.println("  max:      " + max.orElse(-1));
        System.out.println("  min:      " + min.orElse(-1));
        System.out.println("  sum:      " + sum);
        System.out.printf("  average:  %.2f%n", avg);

        // reduce — combine all elements into one
        int product = numbers.stream()
            .distinct()
            .reduce(1, (a, b) -> a * b);
        System.out.println("  reduce(*): " + product);

        // anyMatch, allMatch, noneMatch
        boolean anyEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        boolean noneNeg = numbers.stream().noneMatch(n -> n < 0);
        System.out.println("  anyMatch(even): " + anyEven);
        System.out.println("  allMatch(>0):   " + allPositive);
        System.out.println("  noneMatch(<0):  " + noneNeg);

        // findFirst, findAny
        Optional<Integer> firstEven = numbers.stream()
            .filter(n -> n % 2 == 0)
            .findFirst();
        System.out.println("  findFirst(even): " + firstEven.orElse(-1));
        System.out.println();

        // =====================================================================
        // PART 4: PRACTICAL EXAMPLES
        // =====================================================================
        System.out.println("━━━ PART 4: Real-World Examples ━━━");
        System.out.println();

        List<Employee> employees = List.of(
            new Employee("Alice", "Engineering", 95000),
            new Employee("Bob", "Marketing", 65000),
            new Employee("Charlie", "Engineering", 110000),
            new Employee("Diana", "HR", 72000),
            new Employee("Eve", "Engineering", 88000),
            new Employee("Frank", "Marketing", 71000),
            new Employee("Grace", "HR", 68000)
        );

        // Average salary by department
        Map<String, Double> avgByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::department,
                Collectors.averagingDouble(Employee::salary)
            ));
        System.out.println("  Avg salary by dept:");
        avgByDept.forEach((dept, salary) ->
            System.out.printf("    %-12s $%.0f%n", dept, salary));
        System.out.println();

        // Top 3 highest paid
        List<String> topEarners = employees.stream()
            .sorted(Comparator.comparingDouble(Employee::salary).reversed())
            .limit(3)
            .map(e -> e.name() + " ($" + e.salary() + ")")
            .collect(Collectors.toList());
        System.out.println("  Top 3 earners: " + topEarners);

        // Count by department
        Map<String, Long> countByDept = employees.stream()
            .collect(Collectors.groupingBy(Employee::department, Collectors.counting()));
        System.out.println("  Count by dept: " + countByDept);

        // Partition: salary above/below 80k
        Map<Boolean, List<Employee>> partition = employees.stream()
            .collect(Collectors.partitioningBy(e -> e.salary() >= 80000));
        System.out.println("  Above 80k: " + partition.get(true).stream()
            .map(Employee::name).collect(Collectors.toList()));
        System.out.println("  Below 80k: " + partition.get(false).stream()
            .map(Employee::name).collect(Collectors.toList()));
        System.out.println();

        // =====================================================================
        // STREAM LIFECYCLE
        // =====================================================================
        System.out.println("━━━ Key Rules ━━━");
        System.out.println();
        System.out.println("  1. Streams are LAZY — nothing happens until a terminal op");
        System.out.println("  2. Streams are ONE-TIME USE — can't reuse after terminal op");
        System.out.println("  3. Streams DON'T modify the source collection");
        System.out.println("  4. Use parallelStream() for parallel processing (be careful!)");
        System.out.println();

        // =====================================================================
        // ⚠️ COMMON MISTAKES
        // =====================================================================
        System.out.println("━━━ ⚠️ COMMON MISTAKES ━━━");
        System.out.println();
        System.out.println("1. Reusing a stream:");
        System.out.println("   Stream<String> s = names.stream();");
        System.out.println("   s.count();  s.forEach(...)  ← 💥 IllegalStateException!");
        System.out.println();
        System.out.println("2. Forgetting terminal operation (nothing happens!):");
        System.out.println("   names.stream().filter(n -> n.length() > 3);  ← NOOP!");
        System.out.println();
        System.out.println("3. Side effects in intermediate operations:");
        System.out.println("   Don't modify external state in map/filter — use forEach instead.");
        System.out.println();
        System.out.println("✅ Lesson complete! Next up: D_OptionalClass.java");
    }
}
