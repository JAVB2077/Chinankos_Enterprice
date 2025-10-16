package Chinanko.Chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import Chinanko.Chinanko.dto.AddressInterestPointRequest;
import Chinanko.Chinanko.dto.AddressInterestPointResponse;
import Chinanko.Chinanko.model.AddressInterestPoint;
import Chinanko.Chinanko.repository.AddressInterestPointRepository;

@Service
@RequiredArgsConstructor
public class AddressInterestPointServiceImpl implements AddressInterestPointService {

    private final AddressInterestPointRepository repository;

    @Override
    public AddressInterestPointResponse create(AddressInterestPointRequest req) {
        AddressInterestPoint e = new AddressInterestPoint();
        e.setStreet(req.getStreet());
        e.setExteriorNumbre(req.getExteriorNumbre());
        e.setInteriorNumber(req.getInteriorNumber());
        e.setNeigborhood(req.getNeigborhood());
        e.setPostalCode(req.getPostalCode());
        e.setLatitude(req.getLatitude());
        e.setLongitude(req.getLongitude());
        AddressInterestPoint saved = repository.save(e);
        return map(saved);
    }

    @Override
    public List<AddressInterestPointResponse> findAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public AddressInterestPointResponse getById(Integer id) {
        return repository.findById(id).map(this::map).orElse(null);
    }

    @Override
    public AddressInterestPointResponse update(Integer id, AddressInterestPointRequest req) {
        return repository.findById(id).map(e -> {
            e.setStreet(req.getStreet());
            e.setExteriorNumbre(req.getExteriorNumbre());
            e.setInteriorNumber(req.getInteriorNumber());
            e.setNeigborhood(req.getNeigborhood());
            e.setPostalCode(req.getPostalCode());
            e.setLatitude(req.getLatitude());
            e.setLongitude(req.getLongitude());
            AddressInterestPoint saved = repository.save(e);
            return map(saved);
        }).orElse(null);
    }

    private AddressInterestPointResponse map(AddressInterestPoint e) {
        return AddressInterestPointResponse.builder()
                .idAddressInterest(e.getIdAddressInterest())
                .street(e.getStreet())
                .exteriorNumbre(e.getExteriorNumbre())
                .interiorNumber(e.getInteriorNumber())
                .neigborhood(e.getNeigborhood())
                .postalCode(e.getPostalCode())
                .latitude(e.getLatitude())
                .longitude(e.getLongitude())
                .interestPointId(e.getInterestPoint() != null ? e.getInterestPoint().getIdInterestPoint() : null)
                .build();
    }
}
