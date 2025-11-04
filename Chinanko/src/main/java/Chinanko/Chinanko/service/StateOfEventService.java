package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.StateOfEventResponse;
public interface StateOfEventService {
    List<StateOfEventResponse> findAll();
    StateOfEventResponse findById(Integer id);
}
