package lectures;

import beans.Person;
import mockdata.MockData;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class JavaBrain {

    @Test
    public void testone() throws Exception {
        MockData.getPeople()
                .stream()
                .filter(p -> p.getLastName().startsWith("C"))
                .sorted((p1, p2) -> p1.getLastName().compareTo(p2.getLastName()))
                .forEach(System.out::println);


    }
}
