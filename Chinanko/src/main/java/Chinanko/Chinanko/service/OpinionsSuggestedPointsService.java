package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.OpinionsSuggestedPointsRequest;
import Chinanko.Chinanko.dto.OpinionsSuggestedPointsResponse;

public interface OpinionsSuggestedPointsService {
    OpinionsSuggestedPointsResponse create(OpinionsSuggestedPointsRequest req);
    List<OpinionsSuggestedPointsResponse> findAll();
    OpinionsSuggestedPointsResponse getById(Integer id);
    OpinionsSuggestedPointsResponse update(Integer id, OpinionsSuggestedPointsRequest req);
}
