package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.TownRequest;
import Chinanko.Chinanko.dto.TownResponse;

public interface TownService {
    TownResponse create(TownRequest request);
    List<TownResponse> findAll();
    TownResponse getById(Integer id);
    TownResponse update(Integer id, TownRequest request);
}
