package Chinanko.Chinanko.dto;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProfileUserRequest {
    @NotBlank
    @Size(max = 50)
    private String firstName;
    @NotBlank
    @Size(max = 50)
    private String lastName;
    @NotBlank
    private LocalDate bornDate;
    @NotBlank
    private Integer userId;
    @NotBlank
    private Integer roleId;
    @NotBlank
    private Integer townId;

}
