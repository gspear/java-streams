package lectures;

import beans.Person;
import com.google.common.collect.ImmutableList;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;

import mockdata.MockData;
import org.junit.Test;

public class Lecture2 {

    @Test
    public void range() throws Exception {

    }

    @Test
    public void findAgeRange() throws Exception {
        IntSummaryStatistics intSummaryStatistics = MockData.getPeople().stream()
                .mapToInt(person -> person.getAge())
                .summaryStatistics();
        System.out.println("intSummaryStatistics.getMin() = " + intSummaryStatistics.getMin());
        System.out.println("intSummaryStatistics.getMax() = " + intSummaryStatistics.getMax());
        System.out.println("intSummaryStatistics.getAverage() = " + intSummaryStatistics.getAverage());


    }

    @Test
    public void rangeIteratingLists() throws Exception {
        List<Person> people = MockData.getPeople();
        IntStream.range(0, people.size())
                .mapToObj(i -> people.get(i))
                .forEach(p -> {
                    System.out.println(p.getFirstName() + " " + p.getLastName());
                });

        //IntStream.range(0, people.size()).mapToObj(i -> people.get(i)).forEach(System.out::println);

    }

    @Test
    public void intStreamIterate() throws Exception {
        IntStream.iterate(0, operand -> operand + 1)
                .filter(number -> number % 2 == 0)
                .limit(20)
                .forEach(System.out::println);


    }


}
