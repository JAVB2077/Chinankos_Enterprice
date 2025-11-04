package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.InterestPointRequest;
import chinanko.chinanko.dto.InterestPointResponse;

public interface InterestPointService {
    InterestPointResponse create(InterestPointRequest req);
    List<InterestPointResponse> findAll();
    InterestPointResponse getById(Integer id);
    InterestPointResponse update(Integer id, InterestPointRequest req);
}
