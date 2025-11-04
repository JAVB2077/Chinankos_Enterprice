package chinanko.chinanko.dto;

import java.math.BigDecimal;
import java.util.List;

import chinanko.chinanko.model.Town;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateResponse {
    private Integer idState;
    private String nameState;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private List<TownResponse> towns;
}
