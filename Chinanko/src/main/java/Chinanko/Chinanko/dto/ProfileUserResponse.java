package chinanko.chinanko.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ProfileUserResponse {
    private String firstName;
    private String lastName;
    private LocalDate bornDate;
    private UserResponse user;
    private RoleResponse role;
    private TownResponse town;
}
