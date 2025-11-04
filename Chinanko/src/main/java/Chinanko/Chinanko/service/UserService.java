package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.UserRequest;
import chinanko.chinanko.dto.UserResponse;

public interface UserService {
    List<UserResponse> findAll();

    UserResponse findById(Integer idUser);

    UserResponse create(UserRequest request);

    UserResponse update(Integer idUser, UserRequest request);

    public List<UserResponse> getUserByName(String name);

    public List<UserResponse> getUserByEmail(String email);

    public List<UserResponse> findAll(int page, int pageSize);
}
