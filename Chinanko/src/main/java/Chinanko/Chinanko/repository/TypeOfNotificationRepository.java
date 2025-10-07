package Chinanko.Chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Chinanko.Chinanko.model.TypeOfNotification;

@Repository
public interface TypeOfNotificationRepository extends JpaRepository<TypeOfNotification, Long> {

}
