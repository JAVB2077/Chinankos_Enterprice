package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.CatalogRequest;
import Chinanko.Chinanko.dto.CatalogResponse;

public interface CatalogService {
    CatalogResponse create(CatalogRequest req);
    List<CatalogResponse> findAll();
    CatalogResponse getById(Integer id);
    CatalogResponse update(Integer id, CatalogRequest req);
}
