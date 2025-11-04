package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.RoleRequest;
import chinanko.chinanko.dto.RoleResponse;

public interface RoleService {
    List<RoleResponse> getAll();

    RoleResponse findById(Integer idRole);

    RoleResponse create(RoleRequest request);


    RoleResponse update(Integer idRole, RoleRequest req);
}
