package Chinanko.Chinanko.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class InterestPointResponse {
    private Integer idInterestPoint;
    private String nameInterest;
    private String description;
    private Integer averageInterestPointId;
    private Integer typeOfInterestPointId;
    private Integer catalogId;
    private Integer townId;
}
