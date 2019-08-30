package beans;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Car {

    Integer id;
    String make;
    String model;
    String color;
    Integer year;
    Double price;
}
