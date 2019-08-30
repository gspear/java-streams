package exercises;

import java.util.stream.Stream;

public class Cube {
    private int side;

    public Cube(int s) {
        side = s;

    }

    public int calcVolume() {
        return side * side * side;
    }

    public int doubleIt(int x) {
        return 2 * x;
    }

    public static void main(String[] args) {
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }
}
