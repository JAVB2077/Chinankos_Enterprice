package Chinanko.Chinanko.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportSuggestedPointRequest {
    
    private String description;
    private Integer idTypeOfReportPoint;
    private Integer idSuggestedPoint;
    private Integer idUser;
}