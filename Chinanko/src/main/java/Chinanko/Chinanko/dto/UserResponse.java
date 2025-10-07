package Chinanko.Chinanko.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserResponse {
    private Integer idUser;
    private String nameUser;
    private String email;
}
