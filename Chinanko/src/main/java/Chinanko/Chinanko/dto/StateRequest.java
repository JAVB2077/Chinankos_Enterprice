package chinanko.chinanko.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StateRequest {
    @NotBlank
    @Size(max = 50)
    private String nameState;
    @NotBlank
    private BigDecimal longitude;
    @NotBlank
    private BigDecimal latitude;
}
