package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import chinanko.chinanko.dto.NotificationProfileUserResponse;
import chinanko.chinanko.mapper.NotificationProfileUserMapper;
import chinanko.chinanko.repository.NotificationProfileUserRepository;

@Service
public class NotificationProfileUserServiceImpl implements NotificationProfileUserService{

    private final NotificationProfileUserRepository repository;
    private final NotificationProfileUserMapper mapper;

    public NotificationProfileUserServiceImpl(NotificationProfileUserRepository repository, NotificationProfileUserMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<NotificationProfileUserResponse> listAll() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }
}
