package Chinanko.Chinanko.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class UserRequest {
    @NotBlank
    @Size(max = 50)
    private String nameUser;
    @NotBlank
    @Size(max = 50)
    private String email;
    @NotBlank
    @Size(max = 50)
    private String password;
}
