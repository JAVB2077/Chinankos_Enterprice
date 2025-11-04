package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.NotificationProfileUserResponse;
import chinanko.chinanko.model.NotificationProfileUser;

@Component
public class NotificationProfileUserMapper {

    public NotificationProfileUserResponse toResponse(NotificationProfileUser n){
        if(n==null) return null;
        NotificationProfileUserResponse r = new NotificationProfileUserResponse();
        r.setId(n.getIdNotificactionUser());
        if(n.getUser()!=null) r.setUserId(n.getUser().getIdUser());
        if(n.getNotification()!=null) r.setNotificationId(n.getNotification().getIdNotification());
        return r;
    }
}
