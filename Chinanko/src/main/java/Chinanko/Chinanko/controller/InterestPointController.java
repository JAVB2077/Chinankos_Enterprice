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

import chinanko.chinanko.dto.InterestPointRequest;
import chinanko.chinanko.dto.InterestPointResponse;
import chinanko.chinanko.service.InterestPointService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/interest-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class InterestPointController {

    private final InterestPointService service;

    @PostMapping
    public ResponseEntity<InterestPointResponse> create(@RequestBody InterestPointRequest req){
        InterestPointResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/interest-points/" + created.getIdInterestPoint())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<InterestPointResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public InterestPointResponse getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public InterestPointResponse update(@PathVariable Integer id, @RequestBody InterestPointRequest req){
        return service.update(id, req);
    }
}
