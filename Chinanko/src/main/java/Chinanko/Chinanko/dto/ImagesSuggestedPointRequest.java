package chinanko.chinanko.dto;

import lombok.Data;

@Data
public class ImagesSuggestedPointRequest {
    private String url;
    private Integer suggestedPointId;
    private Integer typeOfOpinionId;
}
