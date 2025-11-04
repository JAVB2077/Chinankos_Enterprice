package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.StateRequest;
import chinanko.chinanko.dto.StateResponse;

public interface StateService {
    StateResponse create(StateRequest request);
    List<StateResponse> findAll();
    StateResponse getById(Integer id);
    StateResponse update(Integer id, StateRequest request);
    StateResponse getByName(String statename);
}
