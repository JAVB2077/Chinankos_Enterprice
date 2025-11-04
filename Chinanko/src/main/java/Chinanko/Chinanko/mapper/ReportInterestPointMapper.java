package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.ReportInterestPointRequest;
import chinanko.chinanko.dto.ReportInterestPointResponse;
import chinanko.chinanko.model.InterestPoint;
import chinanko.chinanko.model.ReportInterestPoint;
import chinanko.chinanko.model.TypeOfReportPoint;
import chinanko.chinanko.model.User;

import java.time.LocalDateTime;

@Component
public class ReportInterestPointMapper {

    public static ReportInterestPoint toEntity(
            ReportInterestPointRequest dto,
            TypeOfReportPoint typeOfReportPoint,
            InterestPoint interestPoint,
            User user) {
        
        if (dto == null)
            return null;
        
        return ReportInterestPoint.builder()
                .description(dto.getDescription())
                .dateReport(LocalDateTime.now())
                .typeOfReportPoint(typeOfReportPoint)
                .interestPoint(interestPoint)
                .user(user)
                .build();
    }

    public static ReportInterestPointResponse toResponse(ReportInterestPoint entity) {
        if (entity == null)
            return null;
        
        return ReportInterestPointResponse.builder()
                .idReportInterestPoint(entity.getIdReportInterestPoint())
                .description(entity.getDescription())
                .dateReport(entity.getDateReport())
                .idTypeOfReportPoint(entity.getTypeOfReportPoint().getIdTypeOfReportPoint())
                .typeOfReport(entity.getTypeOfReportPoint().getType())
                .idInterestPoint(entity.getInterestPoint().getIdInterestPoint())
                .interestPointName(entity.getInterestPoint().getNameInterest())
                .idUser(entity.getUser().getIdUser())
                .userName(entity.getUser().getNameUser())
                .build();
    }

    public static void copyToEntity(
            ReportInterestPointRequest dto,
            ReportInterestPoint entity,
            TypeOfReportPoint typeOfReportPoint,
            InterestPoint interestPoint,
            User user) {
        
        if (dto == null || entity == null)
            return;
        
        entity.setDescription(dto.getDescription());
        entity.setTypeOfReportPoint(typeOfReportPoint);
        entity.setInterestPoint(interestPoint);
        entity.setUser(user);
    }
}