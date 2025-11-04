package chinanko.chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import chinanko.chinanko.model.Event;

public interface EventRepository extends JpaRepository<Event, Integer>{

}
