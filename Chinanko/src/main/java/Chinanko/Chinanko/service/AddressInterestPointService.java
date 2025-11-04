package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.AddressInterestPointRequest;
import chinanko.chinanko.dto.AddressInterestPointResponse;

public interface AddressInterestPointService {
    AddressInterestPointResponse create(AddressInterestPointRequest req);
    List<AddressInterestPointResponse> findAll();
    AddressInterestPointResponse getById(Integer id);
    AddressInterestPointResponse update(Integer id, AddressInterestPointRequest req);
}
