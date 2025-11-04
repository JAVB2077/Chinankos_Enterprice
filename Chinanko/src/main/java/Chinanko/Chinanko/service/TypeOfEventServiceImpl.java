package chinanko.chinanko.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import chinanko.chinanko.dto.TypeOfEventResponse;
import chinanko.chinanko.exception.EntityNotFoundException;
import chinanko.chinanko.mapper.TypeOfEventMapper;
import chinanko.chinanko.model.TypeOfEvent;
import chinanko.chinanko.repository.TypeOfEventRepository;
import lombok.RequiredArgsConstructor;
@Service 
@RequiredArgsConstructor
public class TypeOfEventServiceImpl implements TypeOfEventService {
    private final TypeOfEventRepository repository;
    @Override
    public TypeOfEventResponse getById(Integer id) {
        TypeOfEvent existing = repository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Type of event not found with id: " + id));
        return TypeOfEventMapper.toResponse(existing);
    }

    @Override
    public java.util.List<TypeOfEventResponse> findAll() {
        List<TypeOfEvent> typesOfEvents = repository.findAll();
        return typesOfEvents.stream().map(TypeOfEventMapper::toResponse).collect(Collectors.toList());
    }


}
