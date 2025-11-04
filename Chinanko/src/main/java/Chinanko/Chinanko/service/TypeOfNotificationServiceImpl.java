package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import chinanko.chinanko.dto.TypeOfNotificationRequest;
import chinanko.chinanko.dto.TypeOfNotificationResponse;
import chinanko.chinanko.mapper.TypeOfNotificationMapper;
import chinanko.chinanko.model.TypeOfNotification;
import chinanko.chinanko.repository.TypeOfNotificationRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class TypeOfNotificationServiceImpl implements TypeOfNotificationService{

    private final TypeOfNotificationRepository repository;

  

    @Override
    public TypeOfNotificationResponse create(TypeOfNotificationRequest request) {
        TypeOfNotification t = TypeOfNotificationMapper.toEntity(request);
        TypeOfNotification saved = repository.save(t);
        return TypeOfNotificationMapper.toResponse(saved);
    }
    
    @Override
    public List<TypeOfNotificationResponse> findAll() {
        return repository.findAll().stream().map(TypeOfNotificationMapper::toResponse).collect(Collectors.toList());
    }
    
}
