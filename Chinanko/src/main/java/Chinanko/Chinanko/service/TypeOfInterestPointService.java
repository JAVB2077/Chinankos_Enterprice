package chinanko.chinanko.service;
import java.util.List;

import chinanko.chinanko.dto.TypeOfInterestPointRequest;
import chinanko.chinanko.dto.TypeOfInterestPointResponse;
public interface TypeOfInterestPointService {
    TypeOfInterestPointResponse create(TypeOfInterestPointRequest request);
    List<TypeOfInterestPointResponse> findAll();
    TypeOfInterestPointResponse getById(Integer id);
    TypeOfInterestPointResponse update(Integer id, TypeOfInterestPointRequest request);
}
