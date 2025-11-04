package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.NotificationResponse;
import chinanko.chinanko.model.Notification;

@Component
public class NotificationMapper {

    public NotificationResponse toResponse(Notification n){
        if(n==null) return null;
        NotificationResponse r = new NotificationResponse();
        // model uses idNotification as field
        r.setId(n.getIdNotification());
        if(n.getCreator()!=null) r.setCreatorId(n.getCreator().getIdUser());
        if(n.getTypeOfNotification()!=null) r.setTypeOfNotificationId(n.getTypeOfNotification().getIdTypeNotification());
        return r;
    }
}
