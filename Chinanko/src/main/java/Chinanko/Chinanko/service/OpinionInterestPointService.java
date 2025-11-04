package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.OpinionInterestPointRequest;
import chinanko.chinanko.dto.OpinionInterestPointResponse;

public interface OpinionInterestPointService {
    OpinionInterestPointResponse create(OpinionInterestPointRequest req);
    List<OpinionInterestPointResponse> findAll();
    OpinionInterestPointResponse getById(Integer id);
    OpinionInterestPointResponse update(Integer id, OpinionInterestPointRequest req);
}
