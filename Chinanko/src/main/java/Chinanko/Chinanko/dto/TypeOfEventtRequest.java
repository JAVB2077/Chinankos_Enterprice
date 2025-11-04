package chinanko.chinanko.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TypeOfEventtRequest {
    private String type;
}
