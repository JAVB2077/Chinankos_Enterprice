package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.AverageInterestPointRequest;
import chinanko.chinanko.dto.AverageInterestPointResponse;

public interface AverageInterestPointService {
    AverageInterestPointResponse create(AverageInterestPointRequest req);
    List<AverageInterestPointResponse> findAll();
    AverageInterestPointResponse getById(Integer id);
    AverageInterestPointResponse update(Integer id, AverageInterestPointRequest req);
}
