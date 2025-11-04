package chinanko.chinanko.mapper;
import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.TypeOfEventResponse;
import chinanko.chinanko.dto.TypeOfEventtRequest;
import chinanko.chinanko.model.TypeOfEvent;

import java.util.Collections;
@Component
public class TypeOfEventMapper {
    public static TypeOfEvent toEntity(TypeOfEventtRequest r){
        if(r == null) return null;
        return TypeOfEvent.builder()
        .type(r.getType()).build();
    }

    public static TypeOfEventResponse toResponse(TypeOfEvent t){
        if(t == null) return null;
        if(t.getEvents() == null)
        t.setEvents(Collections.emptyList());
        return TypeOfEventResponse.builder()
            .idTypeEvent(t.getIdTypeEvent())
            .type(t.getType())
            .events(t.getEvents().stream().map(e -> new EventMapper().toResponse(e).getNameEvent()).toList())
            .build();
    }

    public static void copyToEntity(TypeOfEvent t, TypeOfEventtRequest r){
        if(r == null || t == null) return;
        t.setType(r.getType());
        if(t.getEvents() == null)
            t.setEvents(Collections.emptyList());
    }
}
