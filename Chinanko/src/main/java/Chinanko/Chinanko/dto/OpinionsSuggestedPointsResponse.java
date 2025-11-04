package chinanko.chinanko.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OpinionsSuggestedPointsResponse {
    private Integer idOpinionSuggestedPoint;
    private String polarity;
    private String opinion;
    private Integer typeOfOpinionsSuggestedPointId;
    private Integer profileUserId;
    private Integer suggestedPointId;
}
