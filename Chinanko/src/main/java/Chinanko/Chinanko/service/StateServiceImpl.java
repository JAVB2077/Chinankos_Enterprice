package Chinanko.Chinanko.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Chinanko.Chinanko.dto.StateRequest;
import Chinanko.Chinanko.dto.StateResponse;
import Chinanko.Chinanko.mapper.StateMapper;
import Chinanko.Chinanko.model.State;
import Chinanko.Chinanko.repository.StateRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService{

    private final StateRepository repository;


    @Override
    public StateResponse create(StateRequest request) {
        State created = repository.save(StateMapper.toEntity(request));
        return StateMapper.toResponse(created);
    }

    @Override
    public List<StateResponse> findAll() {
        return repository.findAll().stream().map(StateMapper::toResponse).toList();
    }

    @Override
    public StateResponse getById(Integer id) {
        State s = repository.findById(id).orElse(null);
        return StateMapper.toResponse(s);
    }

    @Override
    public StateResponse update(Integer id, StateRequest request) {
        State existing = repository.findById(id).orElse(null);
        if(existing == null) 
        return null;
        StateMapper.copyToEntity(existing, request);
        State saved = repository.save(existing);
        return StateMapper.toResponse(saved);
    }
}
