package Chinanko.Chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Chinanko.Chinanko.model.NotificationProfileUser;

@Repository
public interface NotificationProfileUserRepository extends JpaRepository<NotificationProfileUser, Long> {

}
