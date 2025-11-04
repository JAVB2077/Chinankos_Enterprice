package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.TownRequest;
import chinanko.chinanko.dto.TownResponse;

public interface TownService {
    TownResponse create(TownRequest request);
    List<TownResponse> findAll(int page, int pageSize);
    TownResponse getById(Integer id);
    TownResponse update(Integer id, TownRequest request);
    TownResponse getTownByName(String name);
    List<TownResponse> getTownsByState(String stateName);
}
