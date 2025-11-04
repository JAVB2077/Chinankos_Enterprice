package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import chinanko.chinanko.dto.AverageInterestPointRequest;
import chinanko.chinanko.dto.AverageInterestPointResponse;
import chinanko.chinanko.model.AverageInterestPoint;
import chinanko.chinanko.repository.AverageInterestPointRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AverageInterestPointServiceImpl implements AverageInterestPointService {
    private final AverageInterestPointRepository repository;

    @Override
    public AverageInterestPointResponse create(AverageInterestPointRequest req) {
        AverageInterestPoint e = new AverageInterestPoint();
        e.setLikes(req.getLikes());
        e.setDislikes(req.getDislikes());
        e.setTotal(req.getTotal());
        e.setAverage(req.getAverage());
        AverageInterestPoint saved = repository.save(e);
        return map(saved);
    }

    @Override
    public List<AverageInterestPointResponse> findAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public AverageInterestPointResponse getById(Integer id) {
        return repository.findById(id).map(this::map).orElse(null);
    }

    @Override
    public AverageInterestPointResponse update(Integer id, AverageInterestPointRequest req) {
        return repository.findById(id).map(e -> {
            e.setLikes(req.getLikes());
            e.setDislikes(req.getDislikes());
            e.setTotal(req.getTotal());
            e.setAverage(req.getAverage());
            AverageInterestPoint saved = repository.save(e);
            return map(saved);
        }).orElse(null);
    }

    private AverageInterestPointResponse map(AverageInterestPoint e) {
        return AverageInterestPointResponse.builder()
                .idAverageInterestedPoint(e.getIdAverageInterestedPoint())
                .likes(e.getLikes())
                .dislikes(e.getDislikes())
                .total(e.getTotal())
                .average(e.getAverage())
                .build();
    }
}
