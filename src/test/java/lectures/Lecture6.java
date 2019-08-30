package lectures;


import beans.Car;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import mockdata.MockData;
import org.junit.Test;

public class Lecture6 {

    final Predicate<Integer> numberBetween5And10 = n -> n > 5 && n < 10;

    @Test
    public void findAny() throws Exception {

        Stream<Integer> boxed = IntStream.rangeClosed(1, 10).boxed();
        boxed
                .filter(numberBetween5And10)
                .findAny()
                .ifPresent(System.out::println);

    }

    @Test
    public void findFirst() throws Exception {
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.stream(numbers)
                .filter(numberBetween5And10)
                .findFirst()
                .ifPresent(System.out::println);

    }

    @Test
    public void findFirstCheapCar() throws Exception {
        Car firstCheapCar = MockData.getCars().stream()
                .filter(car -> car.getPrice() < 5500)
                .findFirst()
                .orElse(new Car(0, "dummy", "dummy", "dummy", 1900, 0D));

        System.out.println("firstCheapCar = " + firstCheapCar);

    }
}

