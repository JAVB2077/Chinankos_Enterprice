package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.OpinionsSuggestedPointsRequest;
import chinanko.chinanko.dto.OpinionsSuggestedPointsResponse;

public interface OpinionsSuggestedPointsService {
    OpinionsSuggestedPointsResponse create(OpinionsSuggestedPointsRequest req);
    List<OpinionsSuggestedPointsResponse> findAll();
    OpinionsSuggestedPointsResponse getById(Integer id);
    OpinionsSuggestedPointsResponse update(Integer id, OpinionsSuggestedPointsRequest req);
}
