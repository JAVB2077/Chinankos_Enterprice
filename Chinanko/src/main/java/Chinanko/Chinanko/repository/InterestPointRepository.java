package Chinanko.Chinanko.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

import Chinanko.Chinanko.model.InterestPoint;

public interface InterestPointRepository extends JpaRepository<InterestPoint, Integer>{

}
=======
import Chinanko.Chinanko.model.InterestPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestPointRepository extends JpaRepository<InterestPoint, Integer> {
}
>>>>>>> dad39a84c5372090d0c62abad6e5e19f464d044f
