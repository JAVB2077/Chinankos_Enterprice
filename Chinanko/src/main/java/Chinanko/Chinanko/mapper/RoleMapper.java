package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.RoleRequest;
import chinanko.chinanko.dto.RoleResponse;
import chinanko.chinanko.model.Role;

@Component
public class RoleMapper {

    public static Role toEntity(RoleRequest dto) {
        if (dto == null)
            return null;
        return Role.builder()
                .nameRol(dto.getNameRol())
                .build();
    }

    public static RoleResponse toResponse(Role r) {
        if (r == null)
            return null;
        return RoleResponse.builder()
                .idRol(r.getIdRol())
                .nameRol(r.getNameRol())
                .build();
    }

    public static void copyToEntity(RoleRequest dto, Role entity) {
        if (dto == null || entity == null)
            return;
        entity.setNameRol(dto.getNameRol());
    }
}
