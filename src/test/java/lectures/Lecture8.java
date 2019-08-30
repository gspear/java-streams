package lectures;


import beans.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import mockdata.MockData;
import org.assertj.core.util.Lists;
import org.junit.Test;

public class Lecture8 {

    @Test
    public void groupByMake() throws Exception {
        Map<String, List<Car>> groupByMake = MockData.getCars().stream()
                .collect(Collectors.groupingBy(Car::getMake));

//    groupByMake.forEach( (make, cars) -> {
//      System.out.println("make = " + make);
//      cars.forEach(System.out::println);
//    });

        groupByMake.forEach((make, cars) -> {
            System.out.println(make + " : " + cars.size() + " cars, starting from " + cars.stream().mapToDouble(Car::getPrice).min().getAsDouble());
        });

    }


    @Test
    public void groupByColor() throws Exception {
        MockData.getCars()
                .stream()
                .collect(Collectors.groupingBy(Car::getColor))
                .forEach((color, cars) -> {
                    System.out.println("color = " + color);
                    cars.stream().forEach(System.out::println);
                });
    }

    @Test
    public void groupingAndCountingCars() throws Exception {
        Map<String, Long> collect = MockData.getCars()
                .stream()
                .limit(100)
                .collect(Collectors.groupingBy(Car::getColor, Collectors.counting()));

        collect.forEach((k, v) -> System.out.println(k + " >" + v));

    }

    @Test
    public void groupingAndCounting() throws Exception {
        ArrayList<String> names = Lists
                .newArrayList(
                        "John",
                        "John",
                        "Mariam",
                        "Alex",
                        "Mohammado",
                        "Mohammado",
                        "Vincent",
                        "Alex",
                        "Alex"
                );

        Map<String, Long> collect = names.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        collect.forEach((String s, Long l) -> System.out.println(s + " > " + l));


    }

}