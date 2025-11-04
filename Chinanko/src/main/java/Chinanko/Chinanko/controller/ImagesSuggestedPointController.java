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

import chinanko.chinanko.dto.ImagesSuggestedPointRequest;
import chinanko.chinanko.dto.ImagesSuggestedPointResponse;
import chinanko.chinanko.service.ImagesSuggestedPointService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/images-suggested-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class ImagesSuggestedPointController {

    private final ImagesSuggestedPointService service;

    @PostMapping
    public ResponseEntity<ImagesSuggestedPointResponse> create(@RequestBody ImagesSuggestedPointRequest req){
        ImagesSuggestedPointResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/images-suggested-points/" + created.getIdImageSuggested())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ImagesSuggestedPointResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ImagesSuggestedPointResponse getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ImagesSuggestedPointResponse update(@PathVariable Integer id, @RequestBody ImagesSuggestedPointRequest req){
        return service.update(id, req);
    }
}
