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
@Table(name = "TYPES_OF_REPORTS_EVENTS")
public class TypeOfReportsEvents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_reports_events")
    private Integer idReportEvent;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "typeOfReportsEvents", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReportOfEvent> reportOfEvents;
}
