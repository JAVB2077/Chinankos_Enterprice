package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import chinanko.chinanko.dto.AverageSuggestedPointRequest;
import chinanko.chinanko.dto.AverageSuggestedPointResponse;
import chinanko.chinanko.model.AverageSuggestedPoint;
import chinanko.chinanko.repository.AverageSuggestedPointRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AverageSuggestedPointServiceImpl implements AverageSuggestedPointService {
    private final AverageSuggestedPointRepository repository;

    @Override
    public AverageSuggestedPointResponse create(AverageSuggestedPointRequest req) {
        AverageSuggestedPoint e = new AverageSuggestedPoint();
        e.setPolarity(req.getPolarity());
        AverageSuggestedPoint saved = repository.save(e);
        return map(saved);
    }

    @Override
    public List<AverageSuggestedPointResponse> findAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public AverageSuggestedPointResponse getById(Integer id) {
        return repository.findById(id).map(this::map).orElse(null);
    }

    @Override
    public AverageSuggestedPointResponse update(Integer id, AverageSuggestedPointRequest req) {
        return repository.findById(id).map(e -> {
            e.setPolarity(req.getPolarity());
            AverageSuggestedPoint saved = repository.save(e);
            return map(saved);
        }).orElse(null);
    }

    private AverageSuggestedPointResponse map(AverageSuggestedPoint e) {
        return AverageSuggestedPointResponse.builder()
                .idAverage(e.getIdAverage())
                .polarity(e.getPolarity())
                .build();
    }
}
