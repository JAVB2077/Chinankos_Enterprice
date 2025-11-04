package chinanko.chinanko.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OpinionInterestPointResponse {
    private Integer idOpinionInterestPoint;
    private String opinion;
    private BigDecimal polarity;
    private Integer typeOfOpinioId;
    private Integer interestPointId;
    private Integer profileUserId;
}
