package chinanko.chinanko.dto;

import lombok.Data;

@Data 
public class InterestPointRequest {
    private String nameInterest;
    private String description;
    private Integer idTypeOfInterestPoint;
    private Integer idTown;
}
