package chinanko.chinanko.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chinanko.chinanko.model.State;
import chinanko.chinanko.model.StateOfEvent;

public interface StateOfEventRepository extends JpaRepository<StateOfEvent, Integer> {

}
