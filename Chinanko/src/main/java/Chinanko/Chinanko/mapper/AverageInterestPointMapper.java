package Chinanko.Chinanko.mapper;

import org.springframework.stereotype.Component;

import Chinanko.Chinanko.dto.AverageInterestPointRequest;
import Chinanko.Chinanko.dto.AverageInterestPointResponse;
import Chinanko.Chinanko.model.AverageInterestPoint;
import Chinanko.Chinanko.model.InterestPoint;

@Component
public class AverageInterestPointMapper {

    
    public AverageInterestPoint toEntity(AverageInterestPointRequest r) {
        if (r == null) {
            return null;
        }

        InterestPoint interestPoint = null;
        if (r.getIdInterestPoint() != null) {
            interestPoint = new InterestPoint();
            interestPoint.setIdInterestPoint(r.getIdInterestPoint());
        }

        AverageInterestPoint entity = new AverageInterestPoint();
        entity.setLikes(r.getLikes());
        entity.setDislikes(r.getDislikes());
        entity.setTotal(r.getTotal());
        entity.setAverage(r.getAverage());
        entity.setInterestPoint(interestPoint);

        return entity;
    }

   
    public AverageInterestPointResponse toResponse(AverageInterestPoint s) {
        if (s == null) {
            return null;
        }

        return AverageInterestPointResponse.builder()
            .idAverageInterestedPoint(s.getIdAverageInterestedPoint())
            .likes(s.getLikes())
            .dislikes(s.getDislikes())
            .total(s.getTotal())
            .interestPoint(s.getInterestPoint()) // Se puede devolver el objeto completo o solo el ID
            .average(s.getAverage())
            .build();
    }
    public void copyToEntity(AverageInterestPoint s, AverageInterestPointRequest r) {
        if (r == null || s == null) {
            return;
        }
        
        s.setLikes(r.getLikes());
        s.setDislikes(r.getDislikes());
        s.setTotal(r.getTotal());
        s.setAverage(r.getAverage());

        if (r.getIdInterestPoint() != null && (s.getInterestPoint() == null || !s.getInterestPoint().getIdInterestPoint().equals(r.getIdInterestPoint()))) {
            InterestPoint interestPoint = new InterestPoint();
            interestPoint.setIdInterestPoint(r.getIdInterestPoint());
            s.setInterestPoint(interestPoint);
        }
    }
}
