package Chinanko.Chinanko.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CatalogResponse {
    private Integer idCatalog;
    private String products;
    private String description;
}
