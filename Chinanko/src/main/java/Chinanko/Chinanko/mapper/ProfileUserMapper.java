package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.ProfileUserResponse;
import chinanko.chinanko.dto.RoleResponse;
import chinanko.chinanko.dto.TownResponse;
import chinanko.chinanko.dto.UserResponse;
import chinanko.chinanko.model.ProfileUser;

@Component
public class ProfileUserMapper {
    public ProfileUserResponse toResponse(ProfileUser p){
        if(p==null) return null;
        // build nested DTOs if present
        UserResponse userResp = (p.getUser()!=null) ? UserMapper.toResponse(p.getUser()) : null;
        RoleResponse roleResp = (p.getRole()!=null) ? RoleMapper.toResponse(p.getRole()) : null;
        TownResponse townResp = (p.getTown()!=null) ? TownMapper.toResponse(p.getTown()) : null;
        return ProfileUserResponse.builder()
            .firstName(p.getFirstName())
            .lastName(p.getLastName())
            .bornDate(p.getBornDate())
            .user(userResp)
            .role(roleResp)
            .town(townResp)
            .build();
    }

    public ProfileUser toEntity(ProfileUser p){
        if(p==null) return null;
        ProfileUser r = new ProfileUser();
        r.setIdProfileUser(p.getIdProfileUser());
        r.setFirstName(p.getFirstName());
        r.setLastName(p.getLastName());
        r.setBornDate(p.getBornDate());
        r.setUser(p.getUser());
        r.setRole(p.getRole());
        r.setTown(p.getTown());
        return r;
    }
}
