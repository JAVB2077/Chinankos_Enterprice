package Chinanko.Chinanko.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESS_EVENTS")
public class AddressEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_address_event")
    private Integer idAddresEvent;

    @Column(name = "street")
    private String nameTown;

    @Column(name = "exterior_number")
    private String exteriorNumber;

    @Column(name = "interior_numbre")
    private String interiorNumbre;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "postal_code")
    private Integer postalCode;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_event")
    private Event event;
}
