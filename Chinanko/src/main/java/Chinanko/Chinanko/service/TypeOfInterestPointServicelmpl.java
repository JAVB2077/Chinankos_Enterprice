package Chinanko.Chinanko.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Chinanko.Chinanko.dto.TypeOfInterestPointResponse;
import Chinanko.Chinanko.dto.TypeOfInterestPointRequest;
import Chinanko.Chinanko.mapper.TypeOfInterestPointMapper;
import Chinanko.Chinanko.model.TypeOfInterestPoint;
import Chinanko.Chinanko.repository.TypeOfInterestPointRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeOfInterestPointServicelmpl implements TypeOfInterestPointService {
    private final TypeOfInterestPointRepository repository;

    @Override
    public TypeOfInterestPointResponse create(TypeOfInterestPointRequest request) {
        TypeOfInterestPoint created = repository.save(TypeOfInterestPointMapper.toEntity(request));
        return TypeOfInterestPointMapper.toResponse(created);
    }

    @Override
    public List<TypeOfInterestPointResponse> findAll() {
        return repository.findAll().stream().map(TypeOfInterestPointMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public TypeOfInterestPointResponse getById(Integer id) {
        TypeOfInterestPoint t = repository.findById(id).orElse(null);
        return TypeOfInterestPointMapper.toResponse(t);
    }

    @Override
    public TypeOfInterestPointResponse update(Integer id, TypeOfInterestPointRequest request) {
        TypeOfInterestPoint existing = repository.findById(id).orElse(null);
        if(existing == null)
        return null;
        TypeOfInterestPointMapper.copyToEntity(existing, request);
        TypeOfInterestPoint saved = repository.save(existing);
        return TypeOfInterestPointMapper.toResponse(saved);
    }
}
