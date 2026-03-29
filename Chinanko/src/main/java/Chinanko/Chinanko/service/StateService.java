package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.StateRequest;
import Chinanko.Chinanko.dto.StateResponse;

public interface StateService {
    StateResponse create(StateRequest request);
    List<StateResponse> findAll();
    StateResponse getById(Integer id);
    StateResponse update(Integer id, StateRequest request);
}
