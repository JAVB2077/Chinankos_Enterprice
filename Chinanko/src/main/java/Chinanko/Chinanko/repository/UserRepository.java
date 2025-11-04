package chinanko.chinanko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chinanko.chinanko.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM students WHERE LOWER(nameUser) = LOWER(:nameUser);", nativeQuery = true)
    List<User> getUserByName(@Param("nameUser") String nameUser);

    @Query(value = "SELECT * FROM students WHERE LOWER(email) = LOWER(:email);", nativeQuery = true)
    List<User> getUserByEmail(@Param("email") String nameUser);
}