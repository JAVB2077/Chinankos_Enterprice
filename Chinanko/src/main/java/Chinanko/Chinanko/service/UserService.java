package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.UserRequest;
import Chinanko.Chinanko.dto.UserResponse;

public interface UserService {
    UserResponse create(UserRequest request);
    List<UserResponse> findAll();
    UserResponse getById(Integer id);
    UserResponse update(Integer id, UserRequest request);
}
