package Chinanko.Chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Chinanko.Chinanko.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
