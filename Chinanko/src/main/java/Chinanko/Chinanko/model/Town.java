package chinanko.chinanko.model;

import java.math.BigDecimal;
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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok: auto-generates getters, setters, toString, etc.
@Entity // JPA: Marks this class as a database entity.
@Builder // Lombok: Implements the builder design pattern.
@NoArgsConstructor // Lombok: Creates an empty constructor.
@AllArgsConstructor // Lombok: Creates a constructor with all fields.
@Table(name = "TOWNS") // Maps this entity to the "TOWNS" table in the DB.
public class Town {

    @Id // JPA: Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB generates the ID value.
    @Column(name = "pk_id_town") // Maps this field to the "pk_id_town" column.
    private Integer idTown;

    @Column(name = "name_town") // Maps to the "name_town" column.
    private String nameTown;

    @Column(name = "longitude") // Maps to the "longitude" column.
    private BigDecimal longitude; // Use BigDecimal for precise coordinates.

    @Column(name = "latitude") // Maps to the "latitude" column.
    private BigDecimal latitude;

    // --- Relationships ---

    @ManyToOne(fetch = FetchType.LAZY) // A Town belongs to one State.
    @JoinColumn(name = "fk_id_state") // This is the foreign key column in the TOWNS table.
    private State state;

    // One Town can have many Suggested Points.
    @OneToMany(mappedBy = "town", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // "mappedBy" means the 'town' field in SuggestedPoint owns the relationship.
    // "CascadeType.ALL" means operations (save, delete) on Town cascade to its points.
    private List<SuggestedPoint> suggestedPoint;

    // One Town can have many Interest Points.
    @OneToMany(mappedBy = "town", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InterestPoint> interestPoint;
}