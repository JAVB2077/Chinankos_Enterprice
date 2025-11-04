package chinanko.chinanko.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class RoleRequest {
    @NotNull(message = "El nombre es obligatorio")
    @Size(min =3,max = 50, message = "El nombre debe tener Minimo 3 Máximo 50 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre solo puede contener letras y espacios")
    private String name;
}
