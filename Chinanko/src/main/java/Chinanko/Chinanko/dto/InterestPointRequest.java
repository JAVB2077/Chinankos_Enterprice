package Chinanko.Chinanko.dto;

import lombok.Data;

@Data
public class InterestPointRequest {
    private String nameInterest;
    private String description;
    private Integer averageInterestPointId;
    private Integer typeOfInterestPointId;
    private Integer catalogId;
    private Integer townId;
}
