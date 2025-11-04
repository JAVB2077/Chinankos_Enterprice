package chinanko.chinanko.controller;

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

import chinanko.chinanko.dto.PictureProductRequest;
import chinanko.chinanko.dto.PictureProductResponse;
import chinanko.chinanko.service.PictureProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/picture-products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class PictureProductController {

    private final PictureProductService service;

    @PostMapping
    public ResponseEntity<PictureProductResponse> create(@RequestBody PictureProductRequest req){
        PictureProductResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/picture-products/" + created.getIdPictureProduct())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<PictureProductResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public PictureProductResponse getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public PictureProductResponse update(@PathVariable Integer id, @RequestBody PictureProductRequest req){
        return service.update(id, req);
    }
}
