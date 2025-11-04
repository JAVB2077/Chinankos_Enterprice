package chinanko.chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.SuggestedPoint;

@Repository
public interface SuggestedPointRepository extends JpaRepository<SuggestedPoint, Integer> {
}