package Chinanko.Chinanko.model;

import java.util.List;

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
@Table(name = "NOTIFICATIONS")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_notification")
    private Integer idNotification;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "state", nullable = false)
    private Boolean state;

    // EL EMISOR: Quién creó esta notificación. (Uno-a-Muchos)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_user_creator") // Esta columna SÍ debe estar en la tabla NOTIFICATIONS
    private User creator;

    @OneToMany(mappedBy = "notification")
    private List<NotificationProfileUser> recipients;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_type_of_notification", nullable = false)
    private TypeOfNotification typeOfNotification;
}