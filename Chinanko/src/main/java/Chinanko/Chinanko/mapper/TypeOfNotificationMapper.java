package Chinanko.Chinanko.mapper;

import org.springframework.stereotype.Component;

import Chinanko.Chinanko.dto.TypeOfNotificationRequest;
import Chinanko.Chinanko.dto.TypeOfNotificationResponse;
import Chinanko.Chinanko.model.TypeOfNotification;

@Component
public class TypeOfNotificationMapper {
    public static TypeOfNotification toEntity(TypeOfNotificationRequest r){
        if(r==null) return null;
        TypeOfNotification t = new TypeOfNotification();
        t.setType(r.getType());
        return t;
    }

    public static TypeOfNotificationResponse toResponse(TypeOfNotification t){
        if(t==null) return null;
        TypeOfNotificationResponse resp = new TypeOfNotificationResponse();
        resp.setId(t.getIdTypeNotification());
        resp.setType(t.getType());
        return resp;
    }
}
