package chinanko.chinanko.service;

import org.springframework.stereotype.Service;

import chinanko.chinanko.repository.StateOfEventRepository;
import jakarta.persistence.EntityNotFoundException;
import chinanko.chinanko.dto.StateOfEventResponse;
import chinanko.chinanko.model.StateOfEvent;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import chinanko.chinanko.mapper.StateOfEventMapper;

@Service
@RequiredArgsConstructor
public class StateOfEventServiceImpl implements StateOfEventService {
    private final StateOfEventRepository repository;
    @Override
    public List<StateOfEventResponse> findAll(){
        List<StateOfEvent> states = repository.findAll();
        return states.stream().map(StateOfEventMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public StateOfEventResponse findById(Integer id){
        StateOfEvent existing = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Not found state of event with id: " + id));
        return StateOfEventMapper.toResponse(existing);
    }
}
