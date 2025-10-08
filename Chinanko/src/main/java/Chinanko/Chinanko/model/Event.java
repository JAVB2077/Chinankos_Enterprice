package Chinanko.Chinanko.model;

import java.math.BigDecimal;
import java.sql.Time;
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EVENTS")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_event")
    private Integer idEvent;

    @Column(name = "name_event")
    private String nameEvent;

    @Column(name = "description")
    private String description;

    @Column(name = "timeBegin")
    private Time timeBegin;

    @Column(name = "timeEnd")
    private Time timeEnd;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "price")
    private Long price;

    //Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_town")
    private Town town;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_type_of_event")
    private TypeOfEvent typeOfEvent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_state_of_event")
    private StateOfEvent stateOfEvent;

    @OneToMany(mappedBy = "Event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AddressEvent> addressEvent;
}
