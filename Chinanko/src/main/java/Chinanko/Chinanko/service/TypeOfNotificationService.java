package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.TypeOfNotificationRequest;
import chinanko.chinanko.dto.TypeOfNotificationResponse;

public interface TypeOfNotificationService {
    TypeOfNotificationResponse create(TypeOfNotificationRequest request);
    List<TypeOfNotificationResponse> findAll();
}
