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

import chinanko.chinanko.dto.AverageInterestPointRequest;
import chinanko.chinanko.dto.AverageInterestPointResponse;
import chinanko.chinanko.service.AverageInterestPointService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/average-interest-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class AverageInterestPointController {

    private final AverageInterestPointService service;

    @PostMapping
    public ResponseEntity<AverageInterestPointResponse> create(@RequestBody AverageInterestPointRequest req){
        AverageInterestPointResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/average-interest-points/" + created.getIdAverageInterestedPoint())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<AverageInterestPointResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public AverageInterestPointResponse getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public AverageInterestPointResponse update(@PathVariable Integer id, @RequestBody AverageInterestPointRequest req){
        return service.update(id, req);
    }
}
