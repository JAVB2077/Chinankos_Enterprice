package Chinanko.Chinanko.dto;

import java.util.List;

import Chinanko.Chinanko.model.User;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoleResponse {
    private Integer idRol;
    private String nameRol;
}
