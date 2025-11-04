package chinanko.chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import chinanko.chinanko.model.ProfileUser;

public interface ProfileUserRepository extends JpaRepository<ProfileUser, Long> {

}
