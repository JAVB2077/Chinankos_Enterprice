package Chinanko.Chinanko.mapper;

import org.springframework.stereotype.Component;

import Chinanko.Chinanko.dto.ProfileUserResponse;
import Chinanko.Chinanko.model.ProfileUser;

@Component
public class ProfileUserMapper {

    public static ProfileUserResponse toResponse(ProfileUser p){
        if(p==null) 
        return null;
        ProfileUserResponse r = new ProfileUserResponse();
        r.setIdProfileUser(p.getIdProfileUser());
        r.setFirstName(p.getFirstName());
        r.setLastName(p.getLastName());
        r.setBornDate(p.getBornDate());
        if(p.getUser()!=null) r.setUserId(p.getUser().getIdUser());
        if(p.getRole()!=null) r.setRoleId(p.getRole().getIdRol());
        if(p.getTown()!=null) r.setTownId(p.getTown().getIdTown());
        return r;
    }

    public static ProfileUser toEntity(ProfileUser p){
        if(p==null) 
        return null;
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
