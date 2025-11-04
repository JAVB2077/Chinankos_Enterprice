package chinanko.chinanko.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import chinanko.chinanko.dto.TypeOfInterestPointRequest;
import chinanko.chinanko.dto.TypeOfInterestPointResponse;
import chinanko.chinanko.mapper.TypeOfInterestPointMapper;
import chinanko.chinanko.model.TypeOfInterestPoint;
import chinanko.chinanko.repository.TypeOfInterestPointRepository;
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
