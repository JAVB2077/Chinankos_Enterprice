package chinanko.chinanko.service;
import java.util.List;

import chinanko.chinanko.dto.TypeOfEventResponse;

public interface TypeOfEventService {
    List<TypeOfEventResponse> findAll();
    TypeOfEventResponse getById(Integer id);
}
