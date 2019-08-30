package beans;

import com.cisco.services.rpil.wpil.model.compass.subscriber.AvailabilityGroup;
import com.cisco.services.rpil.wpil.model.compass.subscriber.FeatureEntitlement;
import com.cisco.services.rpil.wpil.model.xbo.XboStatus;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@AllArgsConstructor
@Builder(builderClassName = "_Builder", toBuilder = true)
@Getter
@Setter(AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subscriber {

  private String id;
  private String xboAccountId;

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();

  private String adZone;

  @Singular
  private List<AvailabilityGroup> availabilityGroups;

  @Singular
  private List<String> contentEntitlements;

  @Singular
  private List<FeatureEntitlement> featureEntitlements;

  private String recorderManager;

  @Singular
  private List<String> serviceEntitlements;

  private XboStatus status;

  private String timeZone;

  @Builder.Default
  private Boolean transactionHold = Boolean.FALSE;

  @Builder.Default
  private Boolean upsellHold = Boolean.FALSE;

  // Object versioning
  private Long added;
  private Long updated;
  private Integer version;

  public Subscriber() {
    this.availabilityGroups = Collections.emptyList();
    this.contentEntitlements = Collections.emptyList();
    this.featureEntitlements = Collections.emptyList();
    this.serviceEntitlements = Collections.emptyList();
  }

  // Copy constructor
  public Subscriber(final beans.Subscriber other) {
    this.xboAccountId = other.xboAccountId;
    this.id = other.id;
    this.timeZone = other.timeZone;
    this.transactionHold = other.transactionHold;
    this.upsellHold = other.upsellHold;
    this.recorderManager = other.recorderManager;
    this.adZone = other.adZone;
    this.additionalProperties = other.additionalProperties;

    this.availabilityGroups = new ArrayList<>(other.availabilityGroups);
    this.contentEntitlements = new ArrayList<>(other.contentEntitlements);
    this.serviceEntitlements = new ArrayList<>(other.serviceEntitlements);
    this.featureEntitlements = new ArrayList<>(other.featureEntitlements);

    // this.version = other.version;
    // this.updated = other.updated;
    // this.added = other.added;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperty() {
    return additionalProperties;
  }

  // NOTE: Comcast's API requires passing in null array.
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public List<AvailabilityGroup> getAvailabilityGroups() {
    if (availabilityGroups == null) {
      return null;
    }

    return availabilityGroups.isEmpty()
        ? availabilityGroups
        : Collections.unmodifiableList(availabilityGroups);
  }

  // NOTE: Comcast's API requires passing in null array.
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public List<String> getContentEntitlements() {
    if (contentEntitlements == null) {
      return null;
    }

    return contentEntitlements.isEmpty()
        ? contentEntitlements
        : Collections.unmodifiableList(contentEntitlements);
  }

  @JsonIgnore
  public boolean getCreditEligibility() {
    return this.transactionHold != null ? !this.transactionHold : true;
  }

  // NOTE: Comcast's API requires passing in null array.
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public List<FeatureEntitlement> getFeatureEntitlements() {
    if (featureEntitlements == null) {
      return null;
    }

    return featureEntitlements.isEmpty()
        ? featureEntitlements
        : Collections.unmodifiableList(featureEntitlements);
  }

  // NOTE: Comcast's API requires passing in null array.
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public List<String> getServiceEntitlements() {
    if (serviceEntitlements == null) {
      return null;
    }

    return serviceEntitlements.isEmpty()
        ? serviceEntitlements
        : Collections.unmodifiableList(serviceEntitlements);
  }

  @JsonIgnore
  public boolean getUpsellEligibility() {
    return this.upsellHold != null ? !this.upsellHold : true;
  }

  public void removeAllAvailabilityGroups() {
    this.availabilityGroups = Collections.emptyList();
  }

  public void removeAllContentEntitlements() {
    this.contentEntitlements = Collections.emptyList();
  }

  public void removeAllFeatureEntitlements() {
    this.featureEntitlements = Collections.emptyList();
  }

  public void removeAllServiceEntitlements() {
    this.serviceEntitlements = Collections.emptyList();
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  public void updateAvailabilityGroups(List<AvailabilityGroup> groups) {

    this.availabilityGroups = groups.isEmpty()
        ? Collections.emptyList()
        : groups;
  }

  public void updateContentEntitlements(List<String> entitlements) {

    this.contentEntitlements = entitlements.isEmpty()
        ? Collections.emptyList()
        : entitlements;
  }

  public static class _Builder {
    private Map<String, Object> additionalProperties = new HashMap<>();
  }
}

