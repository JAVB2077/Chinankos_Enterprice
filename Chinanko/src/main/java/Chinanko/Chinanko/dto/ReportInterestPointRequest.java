package chinanko.chinanko.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportInterestPointRequest {
    
    private String description;
    private Integer idTypeOfReportPoint;
    private Integer idInterestPoint;
    private Integer idUser;
}
