package Chinanko.Chinanko.repository;

import Chinanko.Chinanko.model.InterestPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestPointRepository extends JpaRepository<InterestPoint, Integer> {
}
