package Chinanko.Chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import Chinanko.Chinanko.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
