package chinanko.chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chinanko.chinanko.model.State;

public interface StateRepository extends JpaRepository<State, Integer> {
    
    @Query(value = "SELECT S.* FROM STATES AS S  WHERE S.name_state = :stateName", nativeQuery = true)
    State getStateByName(@Param("stateName") String stateName);

}
