package chinanko.chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.TypeOfNotification;

@Repository
public interface TypeOfNotificationRepository extends JpaRepository<TypeOfNotification, Long> {

}
