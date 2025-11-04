package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.NotificationResponse;

public interface NotificationService {
    List<NotificationResponse> listAll();
}
