package chinanko.chinanko.dto;

import lombok.Data;

@Data
public class AverageInterestPointRequest {
    private Integer likes;
    private Integer dislikes;
    private Integer total;
    private Integer idInterestPoint;
    private float average;
}
