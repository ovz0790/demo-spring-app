package net.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class FeatureDto {

    private String id;
    private Long timestamp;
    private Long beginViewingDate;
    private Long endViewingDate;
    private String missionName;
    @JsonIgnore
    private String image;
}
