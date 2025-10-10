package Chinanko.Chinanko.dto;

import lombok.Data;

@Data
public class OpinionsSuggestedPointsRequest {
    private String polarity;
    private String opinion;
    private Integer typeOfOpinionsSuggestedPointId;
    private Integer profileUserId;
    private Integer suggestedPointId;
}
