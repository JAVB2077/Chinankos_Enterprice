package Chinanko.Chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Chinanko.Chinanko.dto.CatalogRequest;
import Chinanko.Chinanko.dto.CatalogResponse;
import Chinanko.Chinanko.model.Catalog;
import Chinanko.Chinanko.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{

    private final CatalogRepository repository;

    @Override
    public CatalogResponse create(CatalogRequest req) {
        Catalog e = new Catalog();
        e.setDescription(req.getDescription());
        Catalog saved = repository.save(e);
        return map(saved);
    }

    @Override
    public List<CatalogResponse> findAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public CatalogResponse getById(Integer id) {
        return repository.findById(id).map(this::map).orElse(null);
    }

    @Override
    public CatalogResponse update(Integer id, CatalogRequest req) {
        return repository.findById(id).map(e -> {
            e.setDescription(req.getDescription());
            Catalog saved = repository.save(e);
            return map(saved);
        }).orElse(null);
    }

    private CatalogResponse map(Catalog e){
        return CatalogResponse.builder()
                .idCatalog(e.getIdCatalog())
                .description(e.getDescription())
                .build();
    }
}
