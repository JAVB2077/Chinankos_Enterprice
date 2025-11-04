package chinanko.chinanko.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoleResponse {
    private Integer idRol;
    private String name;
}