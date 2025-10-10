package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.AddressInterestPointRequest;
import Chinanko.Chinanko.dto.AddressInterestPointResponse;

public interface AddressInterestPointService {
    AddressInterestPointResponse create(AddressInterestPointRequest req);
    List<AddressInterestPointResponse> findAll();
    AddressInterestPointResponse getById(Integer id);
    AddressInterestPointResponse update(Integer id, AddressInterestPointRequest req);
}
