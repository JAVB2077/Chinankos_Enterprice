package chinanko.chinanko.dto;

import lombok.Data;

@Data
public class NotificationResponse {
    private Integer id;
    private Integer creatorId;
    private Integer typeOfNotificationId;
}
