package chinanko.chinanko.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OpinionInterestPointRequest {
    private String opinion;
    private BigDecimal polarity;
    private Integer typeOfOpinioId;
    private Integer interestPointId;
    private Integer profileUserId;
}
