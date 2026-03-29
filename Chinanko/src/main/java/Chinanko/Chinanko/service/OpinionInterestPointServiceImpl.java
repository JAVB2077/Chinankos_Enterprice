package Chinanko.Chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Chinanko.Chinanko.dto.OpinionInterestPointRequest;
import Chinanko.Chinanko.dto.OpinionInterestPointResponse;
import Chinanko.Chinanko.model.OpinionInterestPoint;
import Chinanko.Chinanko.repository.OpinionInterestPointRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpinionInterestPointServiceImpl implements OpinionInterestPointService {

    private final OpinionInterestPointRepository repository;

    @Override
    public OpinionInterestPointResponse create(OpinionInterestPointRequest req) {
        OpinionInterestPoint e = new OpinionInterestPoint();
        e.setOpinion(req.getOpinion());
        e.setPolarity(req.getPolarity());
        OpinionInterestPoint saved = repository.save(e);
        return map(saved);
    }

    @Override
    public List<OpinionInterestPointResponse> findAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public OpinionInterestPointResponse getById(Integer id) {
        return repository.findById(id).map(this::map).orElse(null);
    }

    @Override
    public OpinionInterestPointResponse update(Integer id, OpinionInterestPointRequest req) {
        return repository.findById(id).map(e -> {
            e.setOpinion(req.getOpinion());
            e.setPolarity(req.getPolarity());
            OpinionInterestPoint saved = repository.save(e);
            return map(saved);
        }).orElse(null);
    }

    private OpinionInterestPointResponse map(OpinionInterestPoint e){
        return OpinionInterestPointResponse.builder()
                .idOpinionInterestPoint(e.getIdOpinionInterestPoint())
                .opinion(e.getOpinion())
                .polarity(e.getPolarity())
                .typeOfOpinioId(e.getTypeOfOpinio() != null ? e.getTypeOfOpinio().getIdTypeOpinion() : null)
                .interestPointId(e.getInterestPoint() != null ? e.getInterestPoint().getIdInterestPoint() : null)
                .profileUserId(e.getProfileUser() != null ? e.getProfileUser().getIdProfileUser() : null)
                .build();
    }
}
