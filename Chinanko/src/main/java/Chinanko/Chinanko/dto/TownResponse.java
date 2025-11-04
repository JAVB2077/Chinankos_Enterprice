package chinanko.chinanko.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TownResponse {
    @JsonProperty("id of the town")
    private Integer idTown;
    @JsonProperty("name of the town")
    private String nameTown;
    private BigDecimal longitude;
    private BigDecimal latitude;
    @JsonProperty("name of the state")
    private String stateName;
}
