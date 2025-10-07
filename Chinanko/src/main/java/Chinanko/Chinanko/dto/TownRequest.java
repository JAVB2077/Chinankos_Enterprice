package Chinanko.Chinanko.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TownRequest {
    @NotBlank
    @Size(max = 50)
    private String nameTown;
    @NotBlank
    @Size(max = 50)
    private BigDecimal longitude;
    @NotBlank
    @Size(max = 50)
    private BigDecimal latitude;
    @NotBlank
    private Integer stateId;
}
