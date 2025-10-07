package Chinanko.Chinanko.mapper;

import org.springframework.stereotype.Component;

import Chinanko.Chinanko.dto.RoleRequest;
import Chinanko.Chinanko.dto.RoleResponse;
import Chinanko.Chinanko.model.Role;

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
