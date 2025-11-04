package chinanko.chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
