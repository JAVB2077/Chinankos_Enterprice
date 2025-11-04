package chinanko.chinanko.mapper;

import chinanko.chinanko.dto.UserRequest;
import chinanko.chinanko.dto.UserResponse;
import chinanko.chinanko.model.User;

public class UserMapper {

    public static User toEntity(UserRequest dto) {
        if (dto == null)
            return null;
        return User.builder()
                .nameUser(dto.getNameUser())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public static UserResponse toResponse(User user) {
        if (user == null)
            return null;
        return UserResponse.builder()
                .idUser(user.getIdUser())
                .nameUser(user.getNameUser())
                .email(user.getEmail())
                .build();
    }

    public static void copyToEntity(UserRequest dto, User entity) {
        if (dto == null || entity == null)
            return;
        entity.setNameUser(dto.getNameUser());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
    }
}
