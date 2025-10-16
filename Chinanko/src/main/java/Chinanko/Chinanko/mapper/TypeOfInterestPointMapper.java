package Chinanko.Chinanko.mapper;
import org.springframework.stereotype.Component;

import Chinanko.Chinanko.dto.TypeOfInterestPointResponse;
import Chinanko.Chinanko.dto.TypeOfInterestPointRequest;
import Chinanko.Chinanko.model.TypeOfInterestPoint;

@Component
public class TypeOfInterestPointMapper {
    public static TypeOfInterestPoint toEntity(TypeOfInterestPointRequest r){
        if(r==null) return null;
        return TypeOfInterestPoint.builder()
            .nameTypeInterestPoint(r.getNameTypeOfInterestPoint())
            .build();
    }

    public static TypeOfInterestPointResponse toResponse(TypeOfInterestPoint t){
        if(t==null) return null;
        if(t.getInterestPoints()==null) 
        t.setInterestPoints(java.util.Collections.emptyList());
        return TypeOfInterestPointResponse.builder()
            .idTypeOfInterestPoint(t.getIdTypeInterestPoint())
            .nameTypeOfInterestPoint(t.getNameTypeInterestPoint())
            .interestPoints(t.getInterestPoints().stream().map(i -> new InterestPointMapper().toResponse(i)).toList())
            .build();
    }

    public static void copyToEntity(TypeOfInterestPoint t, TypeOfInterestPointRequest r){
        if(r==null || t==null) return;
        t.setNameTypeInterestPoint(r.getNameTypeOfInterestPoint());
        if(t.getInterestPoints()==null) 
            t.setInterestPoints(java.util.Collections.emptyList());
            
        
    }
}
