package Chinanko.Chinanko.model;

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
@Table(name = "OPINIONS_SEGGESTED_POINTS")
public class OpinionsSuggestedPoints {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_opinion_suggested_point")
    private Integer idOpinionSuggestedPoint;

    @Column(name = "polarity")
    private String polarity;

    @Column(name = "opinion")
    private String opinion;

    //Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_type_of_opinion_suggested")
    private TypesOfOpinionsSuggestedPoint ofOpinionsSuggestedPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_user")
    private ProfileUser profileUser;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_suggested_point")
    private SuggestedPoint suggestedPoint;
}
