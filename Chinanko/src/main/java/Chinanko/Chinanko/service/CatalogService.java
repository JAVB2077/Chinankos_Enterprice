package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.CatalogRequest;
import chinanko.chinanko.dto.CatalogResponse;

public interface CatalogService {
    CatalogResponse create(CatalogRequest req);
    List<CatalogResponse> findAll();
    CatalogResponse getById(Integer id);
    CatalogResponse update(Integer id, CatalogRequest req);
}
