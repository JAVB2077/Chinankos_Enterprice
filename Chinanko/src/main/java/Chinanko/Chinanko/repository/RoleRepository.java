package chinanko.chinanko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // Busca un role por nombre para validaciones de unicidad
    Optional<Role> findByName(String name);

}
