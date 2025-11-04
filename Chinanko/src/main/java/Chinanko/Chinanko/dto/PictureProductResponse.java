package chinanko.chinanko.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PictureProductResponse {
    private Integer idPictureProduct;
    private String url;
    private Integer productId;
}
