package Chinanko.Chinanko.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Chinanko.Chinanko.dto.RoleRequest;
import Chinanko.Chinanko.dto.RoleResponse;
import Chinanko.Chinanko.mapper.RoleMapper;
import Chinanko.Chinanko.model.Role;
import Chinanko.Chinanko.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public RoleResponse create(RoleRequest request) {
        Role created = repository.save(RoleMapper.toEntity(request));
        return RoleMapper.toResponse(created);
    }

    @Override
    public List<RoleResponse> getAll() {
        return repository.findAll().stream()
        .map(RoleMapper::toResponse)
        .toList();
    }
    
    @Override
    public RoleResponse update(Integer idRole, RoleRequest req) {
        Role existing = repository.findById(idRole)
            .orElseThrow(() -> new EntityNotFoundException("rol no encontrado: " + idRole));
        RoleMapper.copyToEntity(req, existing);
        Role saved = repository.save(existing);
        return RoleMapper.toResponse(saved);
        
    }

    @Override
    public RoleResponse findById(Integer idRole) {
        Role r = repository.findById(idRole).orElse(null);
        return RoleMapper.toResponse(r);
    }
}
