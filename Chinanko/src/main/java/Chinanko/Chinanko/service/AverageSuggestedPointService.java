package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.AverageSuggestedPointRequest;
import chinanko.chinanko.dto.AverageSuggestedPointResponse;

public interface AverageSuggestedPointService {
    AverageSuggestedPointResponse create(AverageSuggestedPointRequest req);
    List<AverageSuggestedPointResponse> findAll();
    AverageSuggestedPointResponse getById(Integer id);
    AverageSuggestedPointResponse update(Integer id, AverageSuggestedPointRequest req);
}
