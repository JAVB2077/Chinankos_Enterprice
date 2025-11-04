package chinanko.chinanko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.TypeOfReportPointRequest;
import chinanko.chinanko.dto.TypeOfReportPointResponse;
import chinanko.chinanko.mapper.TypeOfReportPointMapper;
import chinanko.chinanko.model.TypeOfReportPoint;
import chinanko.chinanko.repository.TypeOfReportPointRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeOfReportPointService {

    private final TypeOfReportPointRepository repository;

    @Transactional
    public TypeOfReportPointResponse create(TypeOfReportPointRequest request) {
        TypeOfReportPoint typeOfReportPoint = TypeOfReportPointMapper.toEntity(request);
        TypeOfReportPoint saved = repository.save(typeOfReportPoint);
        return TypeOfReportPointMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<TypeOfReportPointResponse> findAll() {
        return repository.findAll().stream()
                .map(TypeOfReportPointMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TypeOfReportPointResponse getById(Integer id) {
        TypeOfReportPoint typeOfReportPoint = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type of Report Point not found with id: " + id));
        return TypeOfReportPointMapper.toResponse(typeOfReportPoint);
    }

    @Transactional
    public TypeOfReportPointResponse update(Integer id, TypeOfReportPointRequest request) {
        TypeOfReportPoint typeOfReportPoint = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type of Report Point not found with id: " + id));
        
        TypeOfReportPointMapper.copyToEntity(request, typeOfReportPoint);
        TypeOfReportPoint updated = repository.save(typeOfReportPoint);
        
        return TypeOfReportPointMapper.toResponse(updated);
    }
}