package lectures;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import beans.Person;

import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.function.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import mockdata.MockData;
import org.assertj.core.util.Lists;
import org.junit.Test;

//"CNTRL + ~" change color, style, keymap etc
// "CMD" +, For preference changes

public class Lecture1 {

    public static final Predicate<Person> NOT_GMAILERS = p -> !p.getEmail().endsWith("gmail.com");
    static Predicate<Person> minor = person -> person.getAge() <= 18;
    static Predicate<Person> male = person -> person.getGender().equalsIgnoreCase("MALE");

    @Test
    public void imperativeApproach() throws IOException {
        List<Person> people = MockData.getPeople();
        List<Person> peopleUnder18 = Lists.newArrayList();
        List<Person> first10PeopleUnder18 = new ArrayList<>(10);
        for (Person p : people) {
            if (p.getAge() <= 18) {
                peopleUnder18.add(p);
            }
        }
        System.out.println("peopleUnder18 = " + peopleUnder18);
        // 1. Find people aged less or equal 18
        // 2. Then change implementation to find first 10 people
        int i = 0;
        for (Person p : people) {
            if (p.getAge().intValue() <= 18) {
                if (i >= 10) {
                    break;
                }
                i++;
                first10PeopleUnder18.add(p);
            }
        }
        System.out.println("first10PeopleUnder18 = " + first10PeopleUnder18);

    }

    @Test
    public void declarativeApproachUsingStreams() throws Exception {

        MockData.getPeople()
                .stream()
                .filter(minor.and(male))
                .limit(10)
                .map(person -> Person.builder().age(person.getAge()).gender(person.getGender()))
                .collect(toList())
                .forEach(System.out::println);

    }

    @Test
    public void MinMaxAge() throws Exception {
        int max = MockData.getPeople()
                .stream()
                .mapToInt(p -> p.getAge())
                .max().getAsInt();

        double avg = MockData.getPeople()
                .stream()
                .mapToInt(p -> p.getAge())
                .average()
                .getAsDouble();

        int min = MockData.getPeople()
                .stream()
                .mapToInt(p -> p.getAge())
                .min()
                .getAsInt();

        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println("Avg: " + avg);

        List<Person> people = MockData.getPeople();
        Stream<Person> stream = people.stream();
    }

    @Test
    public void addAge() throws Exception {
        int age = MockData.getPeople()
                .stream()
                .mapToInt(p -> p.getAge())
                .sum();
        System.out.println("age = " + age);
    }

    @Test
    public void filterLongEmailers() throws Exception {
        MockData.getPeople()
                .stream()
                .filter(p -> p.getEmail().length() > 32)
                .forEach(System.out::println);
    }

    @Test
    public void filterNonGmailers() throws Exception {
        MockData.getPeople()
                .stream()
                .filter(NOT_GMAILERS)
                .limit(10)
                .map(p -> p.getEmail())
                .forEach(System.out::println);
    }

    @Test
    public void filterGmailers() throws Exception {
        MockData.getPeople()
                .stream()
                .filter(person -> person.getEmail().contains(".com"))
                .filter(person -> person.getFirstName().startsWith("A"))
                .filter(person -> {
                    int age = person.getAge();
                    return age <= 45 && age >= 17;
                })
                .filter(person -> person.getGender().equalsIgnoreCase("Female"))
                .forEach(System.out::println);

    }

    public long summation(int n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0, (a, b) -> a + b);

    }

    public long factorial(int n) {
        if (n > 20) {
            throw new IllegalArgumentException(n + " is out of range");
        }
        return LongStream.rangeClosed(2, n).reduce(1, (a, b) -> a * b);
    }

    public static List<Integer> generateFibonacci(int series) {
        return Stream.iterate(new int[]{0, 1}, s -> new int[]{s[1], s[0] + s[1]})
                .limit(series)
                .map(n -> n[0])
                .collect(toList());
    }

    @Test
    public void testFactorial() throws Exception {
        System.out.println("factorial = " + factorial(5));
        System.out.println("Summation =" + summation(5));
    }

    @Test
    public void testFibonacci() throws Exception {
        System.out.println("Fibonnaci = " + generateFibonacci(10));
    }

    @Test
    public void testListOfPrimes() throws Exception {
        IntStream.rangeClosed(3, 30)
                .filter(Lecture1::isPrime)
                .forEach(System.out::println);
    }

    private static boolean isPrime(int number) {
        IntPredicate isDivisible = index -> number % index == 0;
        return number > 1 && IntStream.range(2, number - 1).noneMatch(isDivisible);
    }

    @Test
    public void celciusToFahrenheit() throws Exception {
        IntFunction<String> convertToFehrenheit = c -> "" + c + " " + (32 + (9 * c / 5));
        IntStream.rangeClosed(-50, 50)
                .mapToObj(convertToFehrenheit)
                .forEach(System.out::println);

        IntStream.rangeClosed(-50, 50)
                .mapToDouble(c -> 32 + (9 * c / 5))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        String s = "Hello World\nPranav";
        String[] split = s.split("");
        Object[] objects = IntStream.rangeClosed(1, split.length)
                .mapToObj(i -> split[split.length - i])
                .toArray();
        Arrays.stream(objects).forEach(System.out::println);

    }


    @Test
    public void testFlatMap() throws Exception {
        List<String> strings = Arrays.asList("Hello", "World", "Pranav");
        Map<Object, Long> collect = strings.stream()
                .flatMap(s -> Arrays.stream(s.split("")))
                .map(c -> new SimpleEntry(c, 1))
                .collect(groupingBy(SimpleEntry::getKey, counting()));


        Map result = collect.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        result.forEach((k, v) -> System.out.println(k + " " + v));


    }

    @Test
    public void testFunction() throws Exception {
        Function<List<String>, List<String>> nullRemoverFunction = list -> list.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Function<List<String>, List<String>> actualFunction = list -> list.stream()
                .filter(s -> s.length() > 3)
                .collect(toList());

        Function<List<String>, List<String>> x = nullRemoverFunction.andThen(actualFunction).andThen(nullRemoverFunction);

        String[] sarray = {"hi", "how", null, "are", "askfsfl", null, "sdfsdf"};
        List<String> strings = Arrays.asList(sarray);

        List<String> apply = x.apply(strings);
        System.out.println("apply = " + apply);
    }
}
