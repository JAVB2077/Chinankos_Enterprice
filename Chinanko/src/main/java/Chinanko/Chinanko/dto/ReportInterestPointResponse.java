package chinanko.chinanko.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportInterestPointResponse {
    
    private Integer idReportInterestPoint;
    private String description;
    private LocalDateTime dateReport;
    private Integer idTypeOfReportPoint;
    private String typeOfReport;
    private Integer idInterestPoint;
    private String interestPointName;
    private Integer idUser;
    private String userName;
}