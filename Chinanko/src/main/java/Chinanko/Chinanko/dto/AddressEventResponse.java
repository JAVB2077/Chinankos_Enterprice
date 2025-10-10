package Chinanko.Chinanko.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddressEventResponse {
    private Integer idAddresEvent;
    private String nameTown;
    private String exteriorNumber;
    private String interiorNumbre;
    private String neighborhood;
    private Integer postalCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer eventId;
}
