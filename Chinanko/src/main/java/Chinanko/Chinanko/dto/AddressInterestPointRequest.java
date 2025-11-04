package chinanko.chinanko.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AddressInterestPointRequest {
    private String street;
    private String exteriorNumbre;
    private String interiorNumber;
    private String neigborhood;
    private String postalCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer interestPointId;
}
