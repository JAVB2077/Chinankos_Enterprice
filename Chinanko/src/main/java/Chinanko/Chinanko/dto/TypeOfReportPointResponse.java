package chinanko.chinanko.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfReportPointResponse {
    
    private Integer idTypeOfReportPoint;
    private String type;
}