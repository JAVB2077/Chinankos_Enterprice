package chinanko.chinanko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chinanko.chinanko.model.Town;

public interface TownRepository extends JpaRepository<Town, Integer> {
    @Query(value = "SELECT T.* FROM TOWNS AS T JOIN STATES AS S ON T.fk_id_state = S.pk_id_state WHERE S.name_state = :stateName", nativeQuery = true)
    List<Town> getTownsByState(@Param("stateName") String stateName);

    @Query(value = "SELECT * FROM TOWNS WHERE name_town = :nameTown", nativeQuery = true)
    Town getTownByName(@Param("nameTown") String nameTown);
}
