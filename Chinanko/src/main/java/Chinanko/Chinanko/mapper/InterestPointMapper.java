package Chinanko.Chinanko.mapper;

import org.springframework.stereotype.Component;

import Chinanko.Chinanko.dto.InterestPointRequest;
import Chinanko.Chinanko.dto.InterestPointResponse;
import Chinanko.Chinanko.model.AverageInterestPoint;
import Chinanko.Chinanko.model.InterestPoint;
import Chinanko.Chinanko.model.Town;
import Chinanko.Chinanko.model.TypeOfInterestPoint;

@Component
public class InterestPointMapper {

    public InterestPoint toEntity(InterestPointRequest r) {
        if (r == null) return null;

        TypeOfInterestPoint typeOfPoint = null;
        if (r.getIdTypeOfInterestPoint() != null) {
            typeOfPoint = new TypeOfInterestPoint();
            typeOfPoint.setIdTypeInterestPoint(r.getIdTypeOfInterestPoint());;
        }

        Town town = null;
        if (r.getIdTown() != null) {
            town = new Town();
            town.setIdTown(r.getIdTown());
        }

        
        return InterestPoint.builder()
            .nameInterest(r.getNameInterest())
            .description(r.getDescription())
            .typeOfInterestPoint(typeOfPoint)
            .town(town)
            .build();
    }

    public InterestPointResponse toResponse(InterestPoint s) {
        if (s == null) return null;

        return InterestPointResponse.builder()
            .idInterestPoint(s.getIdInterestPoint())
            .nameInterest(s.getNameInterest()) 
            .description(s.getDescription())
            
            .typeOfInterestPointId(s.getTypeOfInterestPoint() != null ? s.getTypeOfInterestPoint().getIdTypeInterestPoint() : null)
            .townId(s.getTown() != null ? s.getTown().getIdTown() : null)
            .build();
    }


    public void copyToEntity(InterestPoint s, InterestPointRequest r) {
        if (r == null || s == null) return;
        
        s.setNameInterest(r.getNameInterest());
        s.setDescription(r.getDescription());
        
    }
}