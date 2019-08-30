package beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Device {
    String sourceId;
    String serialNumber;
    String deviceType;
    String serviceAccountUri;
    String status;
    boolean hasDvr;
}

