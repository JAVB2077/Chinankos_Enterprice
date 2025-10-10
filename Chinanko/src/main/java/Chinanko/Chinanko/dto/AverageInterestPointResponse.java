package Chinanko.Chinanko.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AverageInterestPointResponse {
    private Integer idAverageInterestedPoint;
    private Integer likes;
    private Integer dislikes;
    private Integer total;
    private Long average;
}
