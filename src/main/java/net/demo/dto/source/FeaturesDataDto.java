package net.demo.dto.source;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FeaturesDataDto {

    private List<FeatureDataDto> features;

}
