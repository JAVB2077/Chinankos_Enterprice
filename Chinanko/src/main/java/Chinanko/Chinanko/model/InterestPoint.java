package Chinanko.Chinanko.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INTEREST_POINT")
public class InterestPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_interest_point")
    private Integer idInterestPoint;

    @Column(name = "nameInterest")
    private String nameInterest;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "interestPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OpinionInterestPoint> opinionInterestPoint;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_avarage_interest_points", nullable = false, unique = true)
    private AvarageInterestPoint avarageInterestPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_type_of_point")
    private TypeOfInterestPoint typeOfInterestPoint;

    @OneToOne(mappedBy = "interestPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private List<AddressInterestPoint> addressInterestPoint;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_catalog", nullable = false, unique = true)
    private Catalog catalog;
}
