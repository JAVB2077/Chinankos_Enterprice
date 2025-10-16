package Chinanko.Chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Chinanko.Chinanko.dto.OpinionsSuggestedPointsRequest;
import Chinanko.Chinanko.dto.OpinionsSuggestedPointsResponse;
import Chinanko.Chinanko.model.OpinionsSuggestedPoints;
import Chinanko.Chinanko.repository.OpinionsSuggestedPointsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpinionsSuggestedPointsServiceImpl implements OpinionsSuggestedPointsService {
    private final OpinionsSuggestedPointsRepository repository;

    @Override
    public OpinionsSuggestedPointsResponse create(OpinionsSuggestedPointsRequest req) {
        OpinionsSuggestedPoints e = new OpinionsSuggestedPoints();
        e.setPolarity(req.getPolarity());
        e.setOpinion(req.getOpinion());
        OpinionsSuggestedPoints saved = repository.save(e);
        return map(saved);
    }

    @Override
    public List<OpinionsSuggestedPointsResponse> findAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public OpinionsSuggestedPointsResponse getById(Integer id) {
        return repository.findById(id).map(this::map).orElse(null);
    }

    @Override
    public OpinionsSuggestedPointsResponse update(Integer id, OpinionsSuggestedPointsRequest req) {
        return repository.findById(id).map(e -> {
            e.setPolarity(req.getPolarity());
            e.setOpinion(req.getOpinion());
            OpinionsSuggestedPoints saved = repository.save(e);
            return map(saved);
        }).orElse(null);
    }

    private OpinionsSuggestedPointsResponse map(OpinionsSuggestedPoints e){
        return OpinionsSuggestedPointsResponse.builder()
                .idOpinionSuggestedPoint(e.getIdOpinionSuggestedPoint())
                .polarity(e.getPolarity())
                .opinion(e.getOpinion())
                .typeOfOpinionsSuggestedPointId(e.getOfOpinionsSuggestedPoint() != null ? e.getOfOpinionsSuggestedPoint().getIdTypeOpinion() : null)
                .profileUserId(e.getProfileUser() != null ? e.getProfileUser().getIdProfileUser() : null)
                .suggestedPointId(e.getSuggestedPoint() != null ? e.getSuggestedPoint().getIdSuggestedPoint() : null)
                .build();
    }
}
