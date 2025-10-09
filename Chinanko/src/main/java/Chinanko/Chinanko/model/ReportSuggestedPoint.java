package Chinanko.Chinanko.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "REPORTS_SUGGESTED_POINTS")
public class ReportSuggestedPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_report_suggested_point")
    private Integer idReportSuggestedPoint;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date_report")
    private LocalDateTime dateReport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_type_of_report_point", nullable = false)
    private TypeOfReportPoint typeOfReportPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_suggested_point", nullable = false)
    private SuggestedPoint suggestedPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_user", nullable = false)
    private User user;
}