package chinanko.chinanko.dto;

import java.math.BigDecimal;
import java.sql.Time;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EventResponse {
    private Integer idEvent;
    private String nameEvent;
    private String description;
    private Time timeBegin;
    private Time timeEnd;
    private float price;
    private String townName;
    private String typeOfEvent;
    private String stateOfEvent;
}
