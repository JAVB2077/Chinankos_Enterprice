package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.AverageSuggestedPointRequest;
import Chinanko.Chinanko.dto.AverageSuggestedPointResponse;

public interface AverageSuggestedPointService {
    AverageSuggestedPointResponse create(AverageSuggestedPointRequest req);
    List<AverageSuggestedPointResponse> findAll();
    AverageSuggestedPointResponse getById(Integer id);
    AverageSuggestedPointResponse update(Integer id, AverageSuggestedPointRequest req);
}
