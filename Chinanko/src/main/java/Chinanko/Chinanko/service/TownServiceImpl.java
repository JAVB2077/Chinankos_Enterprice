package Chinanko.Chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Chinanko.Chinanko.dto.TownRequest;
import Chinanko.Chinanko.dto.TownResponse;
import Chinanko.Chinanko.mapper.TownMapper;
import Chinanko.Chinanko.model.Town;
import Chinanko.Chinanko.repository.TownRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TownServiceImpl implements TownService{

    private final TownRepository repository;

    @Override
    public TownResponse create(TownRequest request) {
        Town created = repository.save(TownMapper.toEntity(request));
        return TownMapper.toResponse(created);
    }

    @Override
    public List<TownResponse> findAll() {
        return repository.findAll().stream().map(TownMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public TownResponse getById(Integer id) {
        Town t = repository.findById(id).orElse(null);
        return TownMapper.toResponse(t);
    }

    @Override
    public TownResponse update(Integer id, TownRequest request) {
        Town existing = repository.findById(id).orElse(null);
        if(existing == null)
        return null;
        TownMapper.copyToEntity(existing, request);
        Town saved = repository.save(existing);
        return TownMapper.toResponse(saved);
    }
}
