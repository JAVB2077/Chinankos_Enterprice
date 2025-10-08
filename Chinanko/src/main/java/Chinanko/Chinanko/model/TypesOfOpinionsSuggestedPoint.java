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
@Table(name = "TYPES_OF_OPINIONS_SEGGESTED_POINTS")
public class TypesOfOpinionsSuggestedPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_type_of_opinion")
    private Integer idTypeOpinion;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "typeOfOpinionsSuggestedPoints", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OpinionsSuggestedPoints> type_of_opinions_suggested_points;
}
