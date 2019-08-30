package lectures;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Collectors;

import mockdata.MockData;
import org.junit.Test;

public class Lecture11 {

    @Test
    public void joiningStrings() throws Exception {
        List<String> names = ImmutableList.of("anna", "john", "marcos", "helena", "yasmin");
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            sb.append(name.toUpperCase());
            sb.append(", ");
        }

        System.out.println(sb.substring(0, sb.length() - 2));

    }

    @Test
    public void joiningStringsWithStream() throws Exception {
        List<String> names = ImmutableList.of("anna", "john", "marcos", "helena", "yasmin");
        String collect = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "));
        System.out.println(collect);

    }
}
