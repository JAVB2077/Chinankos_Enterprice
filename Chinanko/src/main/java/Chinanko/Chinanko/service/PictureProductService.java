package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.PictureProductRequest;
import chinanko.chinanko.dto.PictureProductResponse;

public interface PictureProductService {
    PictureProductResponse create(PictureProductRequest req);
    List<PictureProductResponse> findAll();
    PictureProductResponse getById(Integer id);
    PictureProductResponse update(Integer id, PictureProductRequest req);
}
