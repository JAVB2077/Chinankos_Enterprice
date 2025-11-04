package chinanko.chinanko.model;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name = "TYPE_OF_INTEREST_POINTS")
public class TypeOfInterestPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_type_of_interest_point")
    private Integer idTypeInterestPoint;

    @Column(name = "name_type_of_interest_point")
    private String nameTypeInterestPoint;

    @OneToMany(mappedBy = "typeOfInterestPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InterestPoint> interestPoints;
}
