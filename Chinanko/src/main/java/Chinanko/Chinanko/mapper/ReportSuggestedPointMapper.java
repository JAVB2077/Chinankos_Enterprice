package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.ReportSuggestedPointRequest;
import chinanko.chinanko.dto.ReportSuggestedPointResponse;
import chinanko.chinanko.model.ReportSuggestedPoint;
import chinanko.chinanko.model.SuggestedPoint;
import chinanko.chinanko.model.TypeOfReportPoint;
import chinanko.chinanko.model.User;

import java.time.LocalDateTime;

@Component
public class ReportSuggestedPointMapper {

    public static ReportSuggestedPoint toEntity(
            ReportSuggestedPointRequest dto,
            TypeOfReportPoint typeOfReportPoint,
            SuggestedPoint suggestedPoint,
            User user) {
        
        if (dto == null)
            return null;
        
        return ReportSuggestedPoint.builder()
                .description(dto.getDescription())
                .dateReport(LocalDateTime.now())
                .typeOfReportPoint(typeOfReportPoint)
                .suggestedPoint(suggestedPoint)
                .user(user)
                .build();
    }

    public static ReportSuggestedPointResponse toResponse(ReportSuggestedPoint entity) {
        if (entity == null)
            return null;
        
        return ReportSuggestedPointResponse.builder()
                .idReportSuggestedPoint(entity.getIdReportSuggestedPoint())
                .description(entity.getDescription())
                .dateReport(entity.getDateReport())
                .idTypeOfReportPoint(entity.getTypeOfReportPoint().getIdTypeOfReportPoint())
                .typeOfReport(entity.getTypeOfReportPoint().getType())
                .idSuggestedPoint(entity.getSuggestedPoint().getIdSuggestedPoint())
                .suggestedPointName(entity.getSuggestedPoint().getName())
                .idUser(entity.getUser().getIdUser())
                .userName(entity.getUser().getNameUser())
                .build();
    }

    public static void copyToEntity(
            ReportSuggestedPointRequest dto,
            ReportSuggestedPoint entity,
            TypeOfReportPoint typeOfReportPoint,
            SuggestedPoint suggestedPoint,
            User user) {
        
        if (dto == null || entity == null)
            return;
        
        entity.setDescription(dto.getDescription());
        entity.setTypeOfReportPoint(typeOfReportPoint);
        entity.setSuggestedPoint(suggestedPoint);
        entity.setUser(user);
    }
}