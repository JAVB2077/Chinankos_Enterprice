package Chinanko.Chinanko.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AverageSuggestedPointResponse {
    private Integer idAverage;
    private String polarity;
}
