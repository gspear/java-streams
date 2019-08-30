package lectures;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

public class Lecture3 {

    @Test
    public void min() throws Exception {
        final List<Integer> numbers = ImmutableList.of(1, 2, 3, 100, 23, 93, 99);
        Integer min = numbers.stream().min(Comparator.naturalOrder()).get();
        assertThat(min).isEqualTo(1);

    }

    @Test
    public void max() throws Exception {
        List<String> of = ImmutableList.of("ss", "dfd");
        final List<Integer> numbers = ImmutableList.of(1, 2, 3, 100, 23, 93, 99);
        Integer max = numbers.stream().max(Comparator.naturalOrder()).get();
        assertThat(max).isEqualTo(100);
    }

    @Test
    public void givenList_whenParitioningIntoSublistsUsingPartitionBy_thenCorrect() {
        List<Integer> intList = ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8);

        Map<Boolean, List<Integer>> groups =
                intList.stream().collect(Collectors.partitioningBy(s -> s > 6));
        List<List<Integer>> subSets = new ArrayList<List<Integer>>(groups.values());

        List<Integer> lastPartition = subSets.get(1);
        List<Integer> expectedLastPartition = ImmutableList.of(7, 8);
        assert (subSets.size() == 2);
        assertThat(lastPartition.size() == expectedLastPartition.size());
    }

    @Test
    public void givenList_whenParitioningIntoNSublists_thenCorrect() {
        List<Integer> intList = ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8);
        // List<List<Integer>> subSets = ListUtils.partition(intList, 3);

//    List<Integer> lastPartition = subSets.get(2);
//    List<Integer> expectedLastPartition = ImmutableList.of(7, 8);
//    assertThat(subSets.size(), equalTo(3));
//    assertThat(lastPartition, equalTo(expectedLastPartition));
    }
}
