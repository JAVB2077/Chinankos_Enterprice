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
@Table(name = "OPINIONS_INTEREST_POINT")
public class OpinionInterestPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_interest_point")
    private Integer idOpinionInterestPoint;

    @Column(name = "opinion")
    private String opinion;

    @Column(name = "polarity")
    private BigDecimal polarity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_type_of_opinion")
    private TypeOfOpinio TypeOfOpinio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_interest_point")
    private InterestPoint interestPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_user")
    private ProfileUser profileUser;
}
