package lectures;


import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class Lecture10 {

    private static final List<ArrayList<String>> arrayListOfNames = Lists.newArrayList(
            Lists.newArrayList("Mariam", "Alex", "Ismail"),
            Lists.newArrayList("John", "Alesha", "Andre"),
            Lists.newArrayList("Susy", "Ali")
    );

    @Before
    public void setUp() {
        System.out.println(arrayListOfNames);
    }

    @Test
    public void withoutFlatMap() throws Exception {
//    [Mariam, Alex, Ismail, John, Alesha, Andre, Susy, Ali]

//    List<String> flatList = new ArrayList<>();
//    for (List<String> s : arrayListOfNames) {
//      for (String sname : s) {
//        flatList.add(sname);
//      }
//    }
//    System.out.println("flatList = " + flatList);
        List<String> flatList = new ArrayList<>();
        arrayListOfNames.stream()
                .forEach(list -> list.stream().forEach(s -> flatList.add(s)));
        System.out.println("flatList = " + flatList);
    }

    @Test
    public void withFlatMap() throws Exception {
//   [Mariam, Alex, Ismail, John, Alesha, Andre, Susy, Ali]

        List<String> collect = arrayListOfNames.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(collect);


    }

}

