package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.NotificationResponse;

public interface NotificationService {
    List<NotificationResponse> listAll();
}
