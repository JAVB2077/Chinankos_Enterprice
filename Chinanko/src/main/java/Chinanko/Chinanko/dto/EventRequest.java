package Chinanko.Chinanko.dto;

import java.math.BigDecimal;
import java.sql.Time;

import lombok.Data;

@Data
public class EventRequest {
    private String nameEvent;
    private String description;
    private Time timeBegin;
    private Time timeEnd;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Long price;
    private Integer townId;
    private Integer typeOfEventId;
    private Integer stateOfEventId;
}
