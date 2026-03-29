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
@Table(name = "STATES_SUGGESTED_POINT")
public class StateSuggestedPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_state_suggested_point")
    private Integer idStateSuggested;

    @Column(name = "state", nullable = false)
    private Boolean state = false;

    @OneToMany(mappedBy = "stateSuggestedPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SuggestedPoint> suggestedPoint;
}
