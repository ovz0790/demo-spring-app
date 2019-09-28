package net.demo.dto.source;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FeatureDataDto {

    private FeaturePropertiesDto properties;

}
