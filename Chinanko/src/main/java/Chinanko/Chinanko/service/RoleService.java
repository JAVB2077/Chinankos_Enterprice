package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.RoleRequest;
import Chinanko.Chinanko.dto.RoleResponse;

public interface RoleService {
    List<RoleResponse> getAll();

    RoleResponse findById(Integer idRole);

    RoleResponse create(RoleRequest request);


    RoleResponse update(Integer idRole, RoleRequest req);
}
