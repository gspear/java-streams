package lectures;

import beans.Subscriber;
import com.cisco.services.rpil.wpil.model.compass.subscriber.AvailabilityGroup;
import com.cisco.services.rpil.wpil.model.compass.subscriber.AvailabilityGroup.Scope;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.Assert;

public class TestSubscriber {

  static Predicate<String> myCriteria = s->s.equalsIgnoreCase("TVE");
  public static void testAVGroupsForTVE(String subscriberJson, List<String> catalogsForTVE) throws Exception{
    catalogsForTVE.stream()
        .anyMatch(myCriteria);
    ObjectMapper objectMapper = new ObjectMapper();
    Subscriber subscriber = objectMapper.readValue(subscriberJson, Subscriber.class);
    List<AvailabilityGroup> availabilityGroups = subscriber.getAvailabilityGroups();
    List<AvailabilityGroup> tveAvGrps = availabilityGroups.stream()
        .filter(a -> catalogsForTVE.contains(a.getCatalog()))
        .collect(Collectors.toList());
    boolean b = tveAvGrps.stream()
        .anyMatch(a -> a.getScope() != Scope.STATION);
    Assert.assertFalse(b);



  }




}
