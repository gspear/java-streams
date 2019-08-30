package lectures;


import static org.assertj.core.api.Assertions.assertThat;

import beans.Car;
import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import mockdata.MockData;
import org.junit.Test;

public class Lecture7 {

    @Test
    public void count() throws Exception {

        long count = MockData.getCars().stream().count();
        System.out.println("count = " + count);

    }

    @Test
    public void min() throws Exception {
        MockData.getCars().stream()
                .mapToDouble(Car::getPrice)
                .min()
                .ifPresent(System.out::println);

    }

    @Test
    public void max() throws Exception {
        MockData.getCars().stream()
                .mapToDouble(Car::getPrice)
                .max()
                .ifPresent(System.out::println);
    }


    @Test
    public void average() throws Exception {
        MockData.getCars().stream()
                .mapToDouble(Car::getPrice)
                .average()
                .ifPresent(System.out::println);

    }

    @Test
    public void sum() throws Exception {
        List<Car> cars = MockData.getCars();
        double sum = cars.stream()
                .mapToDouble(Car::getPrice)
                .sum();
        BigDecimal bigDecimalSum = BigDecimal.valueOf(sum);
        System.out.println(sum);
        System.out.println(bigDecimalSum);

    }

    @Test
    public void statistics() throws Exception {
        List<Car> cars = MockData.getCars();
        DoubleSummaryStatistics statistics = cars.stream()
                .mapToDouble(Car::getPrice)
                .summaryStatistics();
        System.out.println(statistics);
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getCount());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getSum());
    }

    @Test
    public void statisticsOnYellowCars() throws Exception {
        DoubleSummaryStatistics yellowStats = MockData.getCars().stream()
                .filter(car -> car.getColor().equalsIgnoreCase("yellow"))
                .mapToDouble(Car::getPrice)
                .summaryStatistics();

        System.out.println("yellowStats avg = " + yellowStats.getAverage());
        System.out.println("yellowStats max = " + yellowStats.getMax());
        System.out.println("yellowStats min= " + yellowStats.getMin());

    }


}