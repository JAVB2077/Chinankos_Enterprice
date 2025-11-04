package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.NotificationProfileUserResponse;

public interface NotificationProfileUserService {
    List<NotificationProfileUserResponse> listAll();
}
