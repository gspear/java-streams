package lectures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import beans.Car;
import mockdata.MockData;
import org.junit.Test;

public class Lecture13 {
    @Test
    public void intermediateAndTerminalOperations() throws Exception {
        System.out.println(
                MockData.getCars()
                        .stream()
                        .filter(car -> {
                            System.out.println("filter car " + car);
                            return car.getPrice() < 10000;
                        })
                        .map(car -> {
                            System.out.println("mapping car " + car);
                            return car.getPrice();
                        })
                        .map(price -> {
                            System.out.println("mapping price " + price);
                            return price + (price * .14);
                        })
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void updateCarPrices() throws Exception {
        List<Car.CarBuilder> modifiedCars = MockData.getCars()
                .stream()
                .filter(car -> car.getPrice() < 10000)
                .map(car -> Car.builder().id(car.getId()).price(car.getPrice() * 1.14))
                .collect(Collectors.toList());

        modifiedCars.stream().forEach(System.out::println);


    }
}
