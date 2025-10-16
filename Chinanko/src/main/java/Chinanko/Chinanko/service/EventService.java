package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.EventRequest;
import Chinanko.Chinanko.dto.EventResponse;

public interface EventService {
    EventResponse create(EventRequest req);
    List<EventResponse> findAll();
    EventResponse getById(Integer id);
    EventResponse update(Integer id, EventRequest req);
}
