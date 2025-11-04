package chinanko.chinanko.model;

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
@Table(name = "NOTIFICATIONS_PROFILE_USERS")
public class NotificationProfileUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_notification_profile_user")
    private Integer idNotificactionUser;

    @Column(name = "state", nullable = false)
    private Boolean state = false;

    // --- RELACIONES ---

    // Muchos registros de esta tabla se refieren a un User (usuario receptor)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_user", nullable = false)
    private User user;

    // Muchos registros de esta tabla se refieren a una Notification
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_notification", nullable = false)
    private Notification notification;
}
