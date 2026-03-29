package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.AverageInterestPointRequest;
import Chinanko.Chinanko.dto.AverageInterestPointResponse;

public interface AverageInterestPointService {
    AverageInterestPointResponse create(AverageInterestPointRequest req);
    List<AverageInterestPointResponse> findAll();
    AverageInterestPointResponse getById(Integer id);
    AverageInterestPointResponse update(Integer id, AverageInterestPointRequest req);
}
