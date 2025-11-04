package chinanko.chinanko.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TypeOfInterestPointRequest {
    @NotBlank
    @Size(max = 50)
    private String nameTypeOfInterestPoint;
}
