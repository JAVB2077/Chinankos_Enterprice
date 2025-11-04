package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import chinanko.chinanko.dto.PictureProductRequest;
import chinanko.chinanko.dto.PictureProductResponse;
import chinanko.chinanko.model.PictureProduct;
import chinanko.chinanko.repository.PictureProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PictureProductServiceImpl implements PictureProductService {
    private final PictureProductRepository repository;

    @Override
    public PictureProductResponse create(PictureProductRequest req) {
        PictureProduct e = new PictureProduct();
        e.setUrl(req.getUrl());
        PictureProduct saved = repository.save(e);
        return map(saved);
    }

    @Override
    public List<PictureProductResponse> findAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public PictureProductResponse getById(Integer id) {
        return repository.findById(id).map(this::map).orElse(null);
    }

    @Override
    public PictureProductResponse update(Integer id, PictureProductRequest req) {
        return repository.findById(id).map(e -> {
            e.setUrl(req.getUrl());
            PictureProduct saved = repository.save(e);
            return map(saved);
        }).orElse(null);
    }

    private PictureProductResponse map(PictureProduct e){
        return PictureProductResponse.builder()
                .idPictureProduct(e.getIdPictureProduct())
                .url(e.getUrl())
                .productId(e.getProduct() != null ? e.getProduct().getIdProduct() : null)
                .build();
    }
}
