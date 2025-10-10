package Chinanko.Chinanko.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AddressEventRequest {
    private String nameTown;
    private String exteriorNumber;
    private String interiorNumbre;
    private String neighborhood;
    private Integer postalCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer eventId; // FK
}
