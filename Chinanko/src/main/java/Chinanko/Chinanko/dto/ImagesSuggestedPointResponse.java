package chinanko.chinanko.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ImagesSuggestedPointResponse {
    private Integer idImageSuggested;
    private String url;
    private Integer suggestedPointId;
    private Integer typeOfOpinionId;
}
