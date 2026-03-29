package Chinanko.Chinanko.dto;

import lombok.Builder;
import lombok.Value;

@Value 
@Builder 
public class InterestPointResponse {

    private final Integer idInterestPoint;
    private final String nameInterest;
    private final String description;

    private final Integer typeOfInterestPointId;
    private final Integer townId;

}