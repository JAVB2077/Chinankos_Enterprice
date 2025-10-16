package Chinanko.Chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Chinanko.Chinanko.dto.EventRequest;
import Chinanko.Chinanko.dto.EventResponse;
import Chinanko.Chinanko.model.Event;
import Chinanko.Chinanko.repository.EventRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    @Override
    public EventResponse create(EventRequest req) {
        Event e = new Event();
        e.setNameEvent(req.getNameEvent());
        e.setDescription(req.getDescription());
        e.setTimeBegin(req.getTimeBegin());
        e.setTimeEnd(req.getTimeEnd());
        e.setLatitude(req.getLatitude());
        e.setLongitude(req.getLongitude());
        e.setPrice(req.getPrice());
        Event saved = repository.save(e);
        return map(saved);
    }

    @Override
    public List<EventResponse> findAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public EventResponse getById(Integer id) {
        return repository.findById(id).map(this::map).orElse(null);
    }

    @Override
    public EventResponse update(Integer id, EventRequest req) {
        return repository.findById(id).map(e -> {
            e.setNameEvent(req.getNameEvent());
            e.setDescription(req.getDescription());
            e.setTimeBegin(req.getTimeBegin());
            e.setTimeEnd(req.getTimeEnd());
            e.setLatitude(req.getLatitude());
            e.setLongitude(req.getLongitude());
            e.setPrice(req.getPrice());
            Event saved = repository.save(e);
            return map(saved);
        }).orElse(null);
    }

    private EventResponse map(Event e){
        return EventResponse.builder()
                .idEvent(e.getIdEvent())
                .nameEvent(e.getNameEvent())
                .description(e.getDescription())
                .timeBegin(e.getTimeBegin())
                .timeEnd(e.getTimeEnd())
                .latitude(e.getLatitude())
                .longitude(e.getLongitude())
                .price(e.getPrice())
                .townId(e.getTown() != null ? e.getTown().getIdTown() : null)
                .typeOfEventId(e.getTypeOfEvent() != null ? e.getTypeOfEvent().getIdTypeEvent() : null)
                .stateOfEventId(e.getStateOfEvent() != null ? e.getStateOfEvent().getIdStateEvent() : null)
                .build();
    }
}
