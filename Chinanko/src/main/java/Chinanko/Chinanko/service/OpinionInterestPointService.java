package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.OpinionInterestPointRequest;
import Chinanko.Chinanko.dto.OpinionInterestPointResponse;

public interface OpinionInterestPointService {
    OpinionInterestPointResponse create(OpinionInterestPointRequest req);
    List<OpinionInterestPointResponse> findAll();
    OpinionInterestPointResponse getById(Integer id);
    OpinionInterestPointResponse update(Integer id, OpinionInterestPointRequest req);
}
