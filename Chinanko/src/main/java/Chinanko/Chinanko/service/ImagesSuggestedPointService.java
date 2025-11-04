package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.ImagesSuggestedPointRequest;
import chinanko.chinanko.dto.ImagesSuggestedPointResponse;

public interface ImagesSuggestedPointService {
    ImagesSuggestedPointResponse create(ImagesSuggestedPointRequest req);
    List<ImagesSuggestedPointResponse> findAll();
    ImagesSuggestedPointResponse getById(Integer id);
    ImagesSuggestedPointResponse update(Integer id, ImagesSuggestedPointRequest req);
}
