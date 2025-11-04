package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.EventRequest;
import chinanko.chinanko.dto.EventResponse;

public interface EventService {
    EventResponse create(EventRequest req);
    List<EventResponse> findAll();
    EventResponse getById(Integer id);
    EventResponse update(Integer id, EventRequest req);
}
