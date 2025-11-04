package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import chinanko.chinanko.dto.ImagesSuggestedPointRequest;
import chinanko.chinanko.dto.ImagesSuggestedPointResponse;
import chinanko.chinanko.model.ImagesSuggestedPoint;
import chinanko.chinanko.repository.ImagesSuggestedPointRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImagesSuggestedPointServiceImpl implements ImagesSuggestedPointService {
    private final ImagesSuggestedPointRepository repository;

    @Override
    public ImagesSuggestedPointResponse create(ImagesSuggestedPointRequest req) {
        ImagesSuggestedPoint e = new ImagesSuggestedPoint();
        e.setUrl(req.getUrl());
        ImagesSuggestedPoint saved = repository.save(e);
        return map(saved);
    }

    @Override
    public List<ImagesSuggestedPointResponse> findAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public ImagesSuggestedPointResponse getById(Integer id) {
        return repository.findById(id).map(this::map).orElse(null);
    }

    @Override
    public ImagesSuggestedPointResponse update(Integer id, ImagesSuggestedPointRequest req) {
        return repository.findById(id).map(e -> {
            e.setUrl(req.getUrl());
            ImagesSuggestedPoint saved = repository.save(e);
            return map(saved);
        }).orElse(null);
    }

    private ImagesSuggestedPointResponse map(ImagesSuggestedPoint e){
        return ImagesSuggestedPointResponse.builder()
                .idImageSuggested(e.getIdImageSuggested())
                .url(e.getUrl())
                .suggestedPointId(e.getSuggestedPoint() != null ? e.getSuggestedPoint().getIdSuggestedPoint() : null)
                .typeOfOpinionId(e.getTypeOfOpinion() != null ? e.getTypeOfOpinion().getIdTypeOpinion() : null)
                .build();
    }
}
