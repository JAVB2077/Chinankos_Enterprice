package chinanko.chinanko.dto;

import java.util.List;

import chinanko.chinanko.model.Town;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeOfInterestPointResponse {
    private Integer idTypeOfInterestPoint;
    private String nameTypeOfInterestPoint;
    private List<InterestPointResponse> interestPoints;
}
