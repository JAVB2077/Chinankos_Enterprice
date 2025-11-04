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

import chinanko.chinanko.dto.AverageSuggestedPointRequest;
import chinanko.chinanko.dto.AverageSuggestedPointResponse;
import chinanko.chinanko.service.AverageSuggestedPointService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/average-suggested-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class AverageSuggestedPointController {

    private final AverageSuggestedPointService service;

    @PostMapping
    public ResponseEntity<AverageSuggestedPointResponse> create(@RequestBody AverageSuggestedPointRequest req){
        AverageSuggestedPointResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/average-suggested-points/" + created.getIdAverage())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<AverageSuggestedPointResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public AverageSuggestedPointResponse getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public AverageSuggestedPointResponse update(@PathVariable Integer id, @RequestBody AverageSuggestedPointRequest req){
        return service.update(id, req);
    }
}
