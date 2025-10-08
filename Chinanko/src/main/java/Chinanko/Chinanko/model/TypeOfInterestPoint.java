package Chinanko.Chinanko.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TYPE_OF_INTEREST_POINTS")
public class TypeOfInterestPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_type_interes_point")
    private Integer idTypeInteresPoint;

    @Column(name = "name_type_of_insterest_point")
    private String nameTypeInteresPoint;

    @OneToMany(mappedBy = "typeOfInterestPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InterestPoint> interestPoints;
}
