
package chinanko.chinanko.model;

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
@Table(name = "AVERAGE_INTEREST_POINT")
public class AverageInterestPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_average_interest_point")
    private Integer idAverageInterestedPoint;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "dislikes")
    private Integer dislikes;

    @Column(name = "total")
    private Integer total;

    @Column(name = "average")
    private float  average;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_interest_point", nullable = false) // ¡Esta es la clave!
    private InterestPoint interestPoint;
}
