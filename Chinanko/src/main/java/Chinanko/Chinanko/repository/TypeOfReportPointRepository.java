package Chinanko.Chinanko.repository;

import Chinanko.Chinanko.model.TypeOfReportPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfReportPointRepository extends JpaRepository<TypeOfReportPoint, Integer> {
}