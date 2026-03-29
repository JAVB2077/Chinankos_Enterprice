package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.PictureProductRequest;
import Chinanko.Chinanko.dto.PictureProductResponse;

public interface PictureProductService {
    PictureProductResponse create(PictureProductRequest req);
    List<PictureProductResponse> findAll();
    PictureProductResponse getById(Integer id);
    PictureProductResponse update(Integer id, PictureProductRequest req);
}
