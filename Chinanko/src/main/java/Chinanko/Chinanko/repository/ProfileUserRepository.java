package Chinanko.Chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import Chinanko.Chinanko.model.ProfileUser;

public interface ProfileUserRepository extends JpaRepository<ProfileUser, Long> {

}
