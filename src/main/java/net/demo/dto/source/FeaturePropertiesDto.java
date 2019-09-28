package net.demo.dto.source;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FeaturePropertiesDto {

    private String id;
    private Long timestamp;
    private AcquisitionDto acquisition;
    private String quicklook;

}
