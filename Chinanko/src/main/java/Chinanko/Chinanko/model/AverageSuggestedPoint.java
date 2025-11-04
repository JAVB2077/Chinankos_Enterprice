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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AVERAGE_SUGGESTED_POINTS")
public class AverageSuggestedPoint {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_average_suggested_point")
    private Integer idAverage;

    @Column(name = "polarity")
    private String polarity;

    @OneToMany(mappedBy = "averageSuggestedPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SuggestedPoint> suggestedPoint;
}
