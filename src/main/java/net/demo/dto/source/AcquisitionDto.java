package net.demo.dto.source;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AcquisitionDto {
    private Long beginViewingDate;
    private Long endViewingDate;
    private String missionName;
}
