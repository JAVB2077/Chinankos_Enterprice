package Chinanko.Chinanko.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddressInterestPointResponse {
    private Integer idAddressInterest;
    private String street;
    private String exteriorNumbre;
    private String interiorNumber;
    private String neigborhood;
    private String postalCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer interestPointId;
}
