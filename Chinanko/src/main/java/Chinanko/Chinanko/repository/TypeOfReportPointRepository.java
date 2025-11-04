package chinanko.chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.TypeOfReportPoint;

@Repository
public interface TypeOfReportPointRepository extends JpaRepository<TypeOfReportPoint, Integer> {
}