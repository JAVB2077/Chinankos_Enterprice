package Chinanko.Chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Chinanko.Chinanko.dto.TypeOfNotificationRequest;
import Chinanko.Chinanko.dto.TypeOfNotificationResponse;
import Chinanko.Chinanko.mapper.TypeOfNotificationMapper;
import Chinanko.Chinanko.model.TypeOfNotification;
import Chinanko.Chinanko.repository.TypeOfNotificationRepository;

@Service
public class TypeOfNotificationServiceImpl implements TypeOfNotificationService{

    private final TypeOfNotificationRepository repository;
    private final TypeOfNotificationMapper mapper;

    public TypeOfNotificationServiceImpl(TypeOfNotificationRepository repository, TypeOfNotificationMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TypeOfNotificationResponse create(TypeOfNotificationRequest request) {
        TypeOfNotification t = mapper.toEntity(request);
        TypeOfNotification saved = repository.save(t);
        return mapper.toResponse(saved);
    }

    @Override
    public List<TypeOfNotificationResponse> listAll() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }
}
