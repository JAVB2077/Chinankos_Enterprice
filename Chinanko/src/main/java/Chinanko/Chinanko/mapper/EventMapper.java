package chinanko.chinanko.mapper;
import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.EventRequest;
import chinanko.chinanko.dto.EventResponse;
import chinanko.chinanko.model.Event;
import chinanko.chinanko.model.StateOfEvent;
import chinanko.chinanko.model.Town;
import chinanko.chinanko.model.TypeOfEvent;

import java.util.Collections;
@Component
public class EventMapper {

    public static Event toEntity(EventRequest r){
        if(r == null) return null;
        return Event.builder()
        .nameEvent(r.getNameEvent())
        .description(r.getDescription())
        .timeBegin(r.getTimeBegin())
        .timeEnd(r.getTimeEnd())
        .price(r.getPrice())
        .typeOfEvent(TypeOfEvent.builder().idTypeEvent(r.getTypeOfEventId()).build())
        .town(Town.builder().idTown(r.getTownId()).build())
        .stateOfEvent(StateOfEvent.builder().idStateEvent(r.getStateOfEventId()).build())
        .build();
    }

    public static EventResponse toResponse(Event e){
        if(e == null) return null;

        return EventResponse.builder()
        .nameEvent(e.getNameEvent())
        .description(e.getDescription())
        .timeBegin(e.getTimeBegin())
        .timeEnd(e.getTimeEnd())
        .price(e.getPrice())
        .typeOfEvent(e.getTypeOfEvent().getType())
        .stateOfEvent(e.getStateOfEvent().getState())
        .townName(e.getTown().getNameTown())
        .build();
    }

    public static void copyToEntity(Event e, EventRequest r){
        if(r == null || e == null) return;
        e.setNameEvent(r.getNameEvent());
        e.setDescription(r.getDescription());
        e.setTimeBegin(r.getTimeBegin());
        e.setTimeEnd(r.getTimeEnd());
        e.setPrice(r.getPrice());
        e.setTypeOfEvent(TypeOfEvent.builder().idTypeEvent(r.getTypeOfEventId()).build());
        e.setTown(Town.builder().idTown(r.getTownId()).build());
        e.setStateOfEvent(StateOfEvent.builder().idStateEvent(r.getStateOfEventId()).build());
    }
}
