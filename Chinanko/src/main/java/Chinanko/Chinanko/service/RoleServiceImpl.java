package chinanko.chinanko.service;

import java.util.List;

import org.springframework.stereotype.Service;

import chinanko.chinanko.dto.RoleRequest;
import chinanko.chinanko.dto.RoleResponse;
import chinanko.chinanko.mapper.RoleMapper;
import chinanko.chinanko.model.Role;
import chinanko.chinanko.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
public class RoleServiceImpl implements RoleService { // Implementación de la interfaz RoleService para la gestión de roles.
    private final RoleRepository repository;

    // Constructor injection
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all roles from the database.
     * @return A list of RoleResponse objects.
     */
    @Transactional(readOnly = true)
    public List<RoleResponse> getAll() {
        return repository.findAll().stream()
                .map(RoleMapper::toResponse)
                .toList();
    }

    /**
     * Finds a single role by its ID.
     * @param id The ID of the role to find.
     * @return The found RoleResponse.
     * @throws EntityNotFoundException if no role with the given ID is found.
     */
    @Transactional(readOnly = true)
    public RoleResponse findById(Integer idRole) {
        Role role = repository.findById(idRole)
                //.orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + idRole));
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado con ID: " + idRole));
        return RoleMapper.toResponse(role);
    }

    /**
     * Creates a new role after validating that its name is unique.
     * @param request The request DTO containing the role's data.
     * @return The created RoleResponse.
     * @throws IllegalArgumentException if a role with the same name already exists.
     */
    @Transactional
    public RoleResponse create(RoleRequest request) {
        // Validation: Ensure no other role has the same name before creating.
        repository.findByName(request.getName()).ifPresent(r -> {
            //throw new IllegalArgumentException("A role with the name '" + request.getName() + "' already exists.");
            throw new IllegalArgumentException("Ya existe un rol con el nombre: " + request.getName());
        });

        Role newRole = RoleMapper.toEntity(request);
        Role savedRole = repository.save(newRole);
        return RoleMapper.toResponse(savedRole);
    }

    /**
     * Updates an existing role.
     * @param id The ID of the role to update.
     * @param request The request DTO with the new data.
     * @return The updated RoleResponse.
     * @throws EntityNotFoundException if the role to update is not found.
     * @throws IllegalArgumentException if the new name is already taken by another role.
     */
    @Transactional
    public RoleResponse update(Integer idRole, RoleRequest request) {
        Role existingRole = repository.findById(idRole)
                //.orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + idRole));
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado con ID: " + idRole));

        // Validation: If the name is being changed, check uniqueness
        if (!Objects.equals(existingRole.getNameRol(), request.getName())) {
            repository.findByName(request.getName()).ifPresent(r -> {
                //throw new IllegalArgumentException("The name '" + request.getName() + "' is already in use by another role.");
                throw new IllegalArgumentException("El nombre '" + request.getName() + "' ya está en uso por otro rol.");
            });
        }

        RoleMapper.copyToEntity(request, existingRole);
        Role updatedRole = repository.save(existingRole);
        return RoleMapper.toResponse(updatedRole);
    }
    
}