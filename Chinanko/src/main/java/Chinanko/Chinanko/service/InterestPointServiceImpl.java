package Chinanko.Chinanko.service;

import Chinanko.Chinanko.dto.InterestPointRequest;
import Chinanko.Chinanko.dto.InterestPointResponse;
import Chinanko.Chinanko.model.AverageInterestPoint;
import Chinanko.Chinanko.model.Catalog;
import Chinanko.Chinanko.model.InterestPoint;
import Chinanko.Chinanko.model.Town;
import Chinanko.Chinanko.model.TypeOfInterestPoint;
import Chinanko.Chinanko.repository.CatalogRepository;
import Chinanko.Chinanko.repository.InterestPointRepository;
import Chinanko.Chinanko.repository.TownRepository;
import Chinanko.Chinanko.repository.TypeOfInterestPointRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterestPointServiceImpl implements InterestPointService {

    private final InterestPointRepository interestPointRepository;
    private final TownRepository townRepository;
    private final TypeOfInterestPointRepository typeOfInterestPointRepository;
    private final CatalogRepository catalogRepository;
    @Override
    public InterestPointResponse create(InterestPointRequest req) {
        Town town = townRepository.findById(req.getIdTown())
                .orElseThrow(() -> new EntityNotFoundException("Town not found with id: " + req.getIdTown()));
        
        TypeOfInterestPoint type = typeOfInterestPointRepository.findById(req.getIdTypeOfInterestPoint())
                .orElseThrow(() -> new EntityNotFoundException("Type of Interest Point not found with id: " + req.getIdTypeOfInterestPoint()));
        

        InterestPoint interestPoint = new InterestPoint();
        interestPoint.setNameInterest(req.getNameInterest());
        interestPoint.setDescription(req.getDescription());
        interestPoint.setTown(town);
        interestPoint.setTypeOfInterestPoint(type);

        
        InterestPoint saved = interestPointRepository.save(interestPoint);
        return mapToResponse(saved);
    }

    @Override
    public List<InterestPointResponse> findAll() {
        return interestPointRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public InterestPointResponse getById(Integer id) {
        return interestPointRepository.findById(id).map(this::mapToResponse)
                .orElseThrow(() -> new EntityNotFoundException("Interest Point not found with id: " + id));
    }

    @Override
    public InterestPointResponse update(Integer id, InterestPointRequest req) {
        InterestPoint existingPoint = interestPointRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Interest Point not found with id: " + id));

        Town town = townRepository.findById(req.getIdTown())
                .orElseThrow(() -> new EntityNotFoundException("Town not found with id: " + req.getIdTown()));
        
        TypeOfInterestPoint type = typeOfInterestPointRepository.findById(req.getIdTypeOfInterestPoint())
                .orElseThrow(() -> new EntityNotFoundException("Type of Interest Point not found with id: " + req.getIdTypeOfInterestPoint()));


        existingPoint.setNameInterest(req.getNameInterest());
        existingPoint.setDescription(req.getDescription());
        existingPoint.setTown(town);
        existingPoint.setTypeOfInterestPoint(type);

        InterestPoint saved = interestPointRepository.save(existingPoint);
        return mapToResponse(saved);
    }

    private InterestPointResponse mapToResponse(InterestPoint entity) {
        return InterestPointResponse.builder()
                .idInterestPoint(entity.getIdInterestPoint())
                .nameInterest(entity.getNameInterest())
                .description(entity.getDescription())
                .typeOfInterestPointId(entity.getTypeOfInterestPoint() != null ? entity.getTypeOfInterestPoint().getIdTypeInterestPoint() : null)
                .townId(entity.getTown() != null ? entity.getTown().getIdTown() : null)
                .build();
    }
}