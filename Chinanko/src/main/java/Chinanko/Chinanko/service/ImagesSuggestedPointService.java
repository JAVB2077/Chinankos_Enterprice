package Chinanko.Chinanko.service;

import java.util.List;

import Chinanko.Chinanko.dto.ImagesSuggestedPointRequest;
import Chinanko.Chinanko.dto.ImagesSuggestedPointResponse;

public interface ImagesSuggestedPointService {
    ImagesSuggestedPointResponse create(ImagesSuggestedPointRequest req);
    List<ImagesSuggestedPointResponse> findAll();
    ImagesSuggestedPointResponse getById(Integer id);
    ImagesSuggestedPointResponse update(Integer id, ImagesSuggestedPointRequest req);
}
