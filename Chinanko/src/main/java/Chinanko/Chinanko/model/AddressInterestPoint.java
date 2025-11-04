package chinanko.chinanko.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESS_INTEREST_POINT")
public class AddressInterestPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_address")
    private Integer idAddressInterest;

    @Column(name = "street")
    private String street;

    @Column(name = "exterior_number")
    private String exteriorNumbre;

    @Column(name = "interior_number")
    private String interiorNumber;

    @Column(name = "neigborhood")
    private String neigborhood;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_interest_point", nullable = false, unique = true)
    private InterestPoint interestPoint;
}
