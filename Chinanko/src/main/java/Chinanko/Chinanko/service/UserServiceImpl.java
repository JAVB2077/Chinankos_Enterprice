package Chinanko.Chinanko.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import Chinanko.Chinanko.dto.UserRequest;
import Chinanko.Chinanko.dto.UserResponse;
import Chinanko.Chinanko.mapper.UserMapper;
import Chinanko.Chinanko.model.User;
import Chinanko.Chinanko.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> findAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<User> users = userRepository.findAll(pageReq);
        return users.getContent().stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse findById(Integer idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponse create(UserRequest request) {
        User created = userRepository.save(UserMapper.toEntity(request));
        return UserMapper.toResponse(created);
    }

    @Override
    public UserResponse update(Integer id, UserRequest request) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setNameUser(request.getNameUser());
        existingUser.setEmail(request.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.toResponse(updatedUser);
    }

    @Override
    public List<UserResponse> getUserByName(String nameUser) {
        return userRepository.getUserByName(nameUser).stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email).stream()
                .map(UserMapper::toResponse)
                .toList();
    }
}
