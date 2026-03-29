package Chinanko.Chinanko.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TownResponse {
    private Integer idTown;
    private String nameTown;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String stateName;
}
