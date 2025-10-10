package Chinanko.Chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import Chinanko.Chinanko.dto.AddressEventRequest;
import Chinanko.Chinanko.dto.AddressEventResponse;
import Chinanko.Chinanko.model.AddressEvent;
import Chinanko.Chinanko.repository.AddressEventRepository;

@Service
@RequiredArgsConstructor
public class AddressEventServiceImpl implements AddressEventService {

    private final AddressEventRepository repository;

    @Override
    public AddressEventResponse create(AddressEventRequest req) {
        AddressEvent entity = new AddressEvent();
        entity.setNameTown(req.getNameTown());
        entity.setExteriorNumber(req.getExteriorNumber());
        entity.setInteriorNumbre(req.getInteriorNumbre());
        entity.setNeighborhood(req.getNeighborhood());
        entity.setPostalCode(req.getPostalCode());
        entity.setLatitude(req.getLatitude());
        entity.setLongitude(req.getLongitude());
        // Relations are not auto-populated in this scaffold. Set event via service later if needed.
        AddressEvent saved = repository.save(entity);
        return map(saved);
    }

    @Override
    public List<AddressEventResponse> findAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public AddressEventResponse getById(Integer id) {
        return repository.findById(id).map(this::map).orElse(null);
    }

    @Override
    public AddressEventResponse update(Integer id, AddressEventRequest req) {
        return repository.findById(id).map(entity -> {
            entity.setNameTown(req.getNameTown());
            entity.setExteriorNumber(req.getExteriorNumber());
            entity.setInteriorNumbre(req.getInteriorNumbre());
            entity.setNeighborhood(req.getNeighborhood());
            entity.setPostalCode(req.getPostalCode());
            entity.setLatitude(req.getLatitude());
            entity.setLongitude(req.getLongitude());
            // Relations are not auto-populated in this scaffold. Set event via service later if needed.
            AddressEvent saved = repository.save(entity);
            return map(saved);
        }).orElse(null);
    }

    private AddressEventResponse map(AddressEvent e) {
        return AddressEventResponse.builder()
                .idAddresEvent(e.getIdAddresEvent())
                .nameTown(e.getNameTown())
                .exteriorNumber(e.getExteriorNumber())
                .interiorNumbre(e.getInteriorNumbre())
                .neighborhood(e.getNeighborhood())
                .postalCode(e.getPostalCode())
                .latitude(e.getLatitude())
                .longitude(e.getLongitude())
                .eventId(e.getEvent() != null ? e.getEvent().getIdEvent() : null)
                .build();
    }
}
