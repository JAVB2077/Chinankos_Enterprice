package Chinanko.Chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Chinanko.Chinanko.dto.InterestPointRequest;
import Chinanko.Chinanko.dto.InterestPointResponse;
import Chinanko.Chinanko.model.InterestPoint;
import Chinanko.Chinanko.repository.InterestPointRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterestPointServiceImpl implements InterestPointService {

    private final InterestPointRepository repository;

    @Override
    public InterestPointResponse create(InterestPointRequest req) {
        InterestPoint e = new InterestPoint();
        e.setNameInterest(req.getNameInterest());
        e.setDescription(req.getDescription());
        InterestPoint saved = repository.save(e);
        return map(saved);
    }

    @Override
    public List<InterestPointResponse> findAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public InterestPointResponse getById(Integer id) {
        return repository.findById(id).map(this::map).orElse(null);
    }

    @Override
    public InterestPointResponse update(Integer id, InterestPointRequest req) {
        return repository.findById(id).map(e -> {
            e.setNameInterest(req.getNameInterest());
            e.setDescription(req.getDescription());
            InterestPoint saved = repository.save(e);
            return map(saved);
        }).orElse(null);
    }

    private InterestPointResponse map(InterestPoint e){
        return InterestPointResponse.builder()
                .idInterestPoint(e.getIdInterestPoint())
                .nameInterest(e.getNameInterest())
                .description(e.getDescription())
                .averageInterestPointId(e.getAverageInterestPoint() != null ? e.getAverageInterestPoint().getIdAverageInterestedPoint() : null)
                .typeOfInterestPointId(e.getTypeOfInterestPoint() != null ? e.getTypeOfInterestPoint().getIdTypeInteresPoint() : null)
                .catalogId(e.getCatalog() != null ? e.getCatalog().getIdCatalog() : null)
                .townId(e.getTown() != null ? e.getTown().getIdTown() : null)
                .build();
    }
}
