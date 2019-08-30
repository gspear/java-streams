package lectures;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import beans.Device;
import beans.Person;
import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import mockdata.MockData;
import org.junit.Test;

public class FlatMapTest {

    @Test
    public void testFlatMaps() throws Exception {
        ImmutableList<Person> shilppark = MockData.getPeople("shilppark.json");
        ImmutableList<Person> bandhu = MockData.getPeople("bandhu.json");
        Stream.of(shilppark, bandhu)
                .flatMap(society -> society.stream())
                .map(person -> person.getEmail())
                .distinct()
                .sorted(Comparator.comparing(s -> s.length()))
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    @Test
    public void testAddUp() {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        Integer integer = numbers.reduce((x1, x2) -> x1 + x2)
                .get();
        System.out.println("integer = " + integer);

    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/pranaami/sample.txt");

        Map<Object, Long> collect = Files.lines(path)
                .flatMap(s -> Arrays.stream(s.trim().split("\\s")))
                .map(s -> s.toLowerCase())
                .filter(s -> s.length() > 0)
                .map(s -> new SimpleEntry(s, 1))
                .collect(groupingBy(SimpleEntry::getKey, counting()));

//    Map<String, Long> wordCount =
//        Files.lines(path).
//        flatMap(line -> Arrays.stream(line.trim().split("\\s")))
//        .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim())
//       .filter(word -> word.length() > 0)
//        .map(word -> new SimpleEntry<>(word, 1))
//        .collect(groupingBy(SimpleEntry::getKey, counting()));
//
//
//    wordCount.forEach((k, v) -> System.out.println(String.format("%s ==>> %d", k, v)));

    }

    @Test
    public void testActiveDvrDevices() throws Exception {
        ImmutableList<Device> devices = MockData.getDevices();
        Predicate<Device> activeDvrDevices = d -> d.getStatus().equalsIgnoreCase("Active")
                && d.getDeviceType().equalsIgnoreCase("DVRVirtualRecorder")
                && d.isHasDvr();
        devices.stream()
                .filter(activeDvrDevices)
                .forEach(System.out::println);
    }

    @Test
    public void testGroupActiveDevices() throws Exception {
        ImmutableList<Device> devices =
                MockData.getDevices();

//    Map<String, List<Device>> active =

        Map<String, Set<String>> active = devices.stream()
                .filter(d -> d.getStatus().equalsIgnoreCase("Active"))
                .collect(groupingBy(d -> d.getDeviceType(), mapping(Device::getSerialNumber, toSet())));

        active.forEach((k, v) -> {
            System.out.println(k);
            v.stream().forEach(System.out::println);
        });
//
// active.forEach((k,v) -> {
//      System.out.println("*** " + k +"*** Devices Count: " +v.size());
//      v.stream()
//          .forEach(System.out::println);
//    });
//
//    active.values()
//        .stream()
//        .flatMap(d -> d.stream())
//        .forEach(System.out::println);
    }

    @Test
    public void testPartitionDevicesByhasDvr() throws Exception {
        Map<Boolean, List<Device>> dvr = MockData.getDevices()
                .stream()
                .filter(d -> d.getStatus().equalsIgnoreCase("Active"))
                .collect(partitioningBy(d -> d.isHasDvr()));

        List<Device> devicesWithDvr = dvr.get(Boolean.TRUE);
        List<Device> devicesWithoutDvr = dvr.get(Boolean.FALSE);
        System.out.println("*** Devices With DVR ***");
        devicesWithDvr.stream()
                .forEach(System.out::println);

    }
}
