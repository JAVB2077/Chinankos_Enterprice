package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.TypeOfReportPointRequest;
import chinanko.chinanko.dto.TypeOfReportPointResponse;
import chinanko.chinanko.model.TypeOfReportPoint;

@Component
public class TypeOfReportPointMapper {

    public static TypeOfReportPoint toEntity(TypeOfReportPointRequest dto) {
        if (dto == null)
            return null;
        return TypeOfReportPoint.builder()
                .type(dto.getType())
                .build();
    }

    public static TypeOfReportPointResponse toResponse(TypeOfReportPoint entity) {
        if (entity == null)
            return null;
        return TypeOfReportPointResponse.builder()
                .idTypeOfReportPoint(entity.getIdTypeOfReportPoint())
                .type(entity.getType())
                .build();
    }

    public static void copyToEntity(TypeOfReportPointRequest dto, TypeOfReportPoint entity) {
        if (dto == null || entity == null)
            return;
        entity.setType(dto.getType());
    }
}