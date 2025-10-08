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
@Table(name = "TYPES_OF_SUGGESTED_POINTS")
public class TypeOfSuggestedPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_type_of_suggested_point")
    private Integer idTypeSuggested;

    @Column(name = "name_type_of_suggested_point")
    private String name;

    @Column(name = "likes")
    private Integer like;

    @Column(name = "dislikes")
    private Integer dislike;

    @Column(name = "total")
    private Integer total;

    @Column(name = "avarage")
    private Long avarage;

    @OneToMany(mappedBy = "typeOfSuggestedPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SuggestedPoint> suggestedPoint;
}
