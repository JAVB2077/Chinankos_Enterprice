package Chinanko.Chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Chinanko.Chinanko.dto.UserRequest;
import Chinanko.Chinanko.dto.UserResponse;
import Chinanko.Chinanko.mapper.UserMapper;
import Chinanko.Chinanko.model.User;
import Chinanko.Chinanko.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserResponse create(UserRequest request) {
        User created = userRepository.save(UserMapper.toEntity(request));
        return UserMapper.toResponse(created);
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(UserMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse getById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponse update(Integer id, UserRequest request) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setNameUser(request.getNameUser());
        existingUser.setEmail(request.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.toResponse(updatedUser);
    }
}
