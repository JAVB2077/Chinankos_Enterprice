package Chinanko.Chinanko.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Chinanko.Chinanko.dto.CatalogRequest;
import Chinanko.Chinanko.dto.CatalogResponse;
import Chinanko.Chinanko.service.CatalogService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/catalogs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class CatalogController {

    private final CatalogService service;

    @PostMapping
    public ResponseEntity<CatalogResponse> create(@RequestBody CatalogRequest req){
        CatalogResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/catalogs/" + created.getIdCatalog())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<CatalogResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public CatalogResponse getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public CatalogResponse update(@PathVariable Integer id, @RequestBody CatalogRequest req){
        return service.update(id, req);
    }
}
