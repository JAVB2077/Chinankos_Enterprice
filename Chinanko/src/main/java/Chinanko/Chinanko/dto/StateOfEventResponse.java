package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StateOfEventResponse {
    @JsonProperty("Id of the state of the event")
    private Integer IdStateOfEvent;
    private String state;
}
