package Chinanko.Chinanko.service;
import java.util.List;

import Chinanko.Chinanko.dto.TypeOfInterestPointResponse;
import Chinanko.Chinanko.dto.TypeOfInterestPointRequest;
public interface TypeOfInterestPointService {
    TypeOfInterestPointResponse create(TypeOfInterestPointRequest request);
    List<TypeOfInterestPointResponse> findAll();
    TypeOfInterestPointResponse getById(Integer id);
    TypeOfInterestPointResponse update(Integer id, TypeOfInterestPointRequest request);
}
