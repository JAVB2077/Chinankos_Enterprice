package Chinanko.Chinanko.mapper;

import org.springframework.stereotype.Component;

import Chinanko.Chinanko.dto.NotificationProfileUserResponse;
import Chinanko.Chinanko.model.NotificationProfileUser;

@Component
public class NotificationProfileUserMapper {

    public NotificationProfileUserResponse toResponse(NotificationProfileUser n){
        if(n==null) return null;
        NotificationProfileUserResponse r = new NotificationProfileUserResponse();
        r.setId(n.getId());
    if(n.getUser()!=null) r.setUserId(n.getUser().getIdUser());
        if(n.getNotification()!=null) r.setNotificationId(n.getNotification().getId());
        return r;
    }
}
