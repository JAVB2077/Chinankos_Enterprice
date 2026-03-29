package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.TypeOfNotificationRequest;
import Chinanko.Chinanko.dto.TypeOfNotificationResponse;

public interface TypeOfNotificationService {
    TypeOfNotificationResponse create(TypeOfNotificationRequest request);
    List<TypeOfNotificationResponse> findAll();
}
