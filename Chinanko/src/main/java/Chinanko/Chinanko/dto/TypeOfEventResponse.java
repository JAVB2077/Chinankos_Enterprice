package chinanko.chinanko.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class TypeOfEventResponse {
    private Integer idTypeEvent;
    private String type;
    private List<String> events;
}
