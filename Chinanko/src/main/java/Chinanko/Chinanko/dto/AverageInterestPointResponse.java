package chinanko.chinanko.dto;

import chinanko.chinanko.model.InterestPoint;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AverageInterestPointResponse {
    private Integer idAverageInterestedPoint;
    private Integer likes;
    private Integer dislikes;
    private Integer total;
    private InterestPoint interestPoint;
    private float average;
}
