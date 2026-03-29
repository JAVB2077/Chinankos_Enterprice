package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.AddressEventRequest;
import Chinanko.Chinanko.dto.AddressEventResponse;

public interface AddressEventService {
    AddressEventResponse create(AddressEventRequest req);
    List<AddressEventResponse> findAll();
    AddressEventResponse getById(Integer id);
    AddressEventResponse update(Integer id, AddressEventRequest req);
}
