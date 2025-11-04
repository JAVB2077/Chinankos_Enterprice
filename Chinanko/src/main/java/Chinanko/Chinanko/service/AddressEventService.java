package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.AddressEventRequest;
import chinanko.chinanko.dto.AddressEventResponse;

public interface AddressEventService {
    AddressEventResponse create(AddressEventRequest req);
    List<AddressEventResponse> findAll();
    AddressEventResponse getById(Integer id);
    AddressEventResponse update(Integer id, AddressEventRequest req);
}
