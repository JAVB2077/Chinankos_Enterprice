package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.InterestPointRequest;
import Chinanko.Chinanko.dto.InterestPointResponse;

public interface InterestPointService {
    InterestPointResponse create(InterestPointRequest req);
    List<InterestPointResponse> findAll();
    InterestPointResponse getById(Integer id);
    InterestPointResponse update(Integer id, InterestPointRequest req);
}
