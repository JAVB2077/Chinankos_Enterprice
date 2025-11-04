package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import chinanko.chinanko.dto.NotificationResponse;
import chinanko.chinanko.mapper.NotificationMapper;
import chinanko.chinanko.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository repository;
    private final NotificationMapper mapper;

    public NotificationServiceImpl(NotificationRepository repository, NotificationMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<NotificationResponse> listAll() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }
}
