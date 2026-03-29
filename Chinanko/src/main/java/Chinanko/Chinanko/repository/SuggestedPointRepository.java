package Chinanko.Chinanko.repository;

import Chinanko.Chinanko.model.SuggestedPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestedPointRepository extends JpaRepository<SuggestedPoint, Integer> {
}