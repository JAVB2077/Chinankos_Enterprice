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

import Chinanko.Chinanko.dto.OpinionInterestPointRequest;
import Chinanko.Chinanko.dto.OpinionInterestPointResponse;
import Chinanko.Chinanko.service.OpinionInterestPointService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/opinion-interest-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class OpinionInterestPointController {

    private final OpinionInterestPointService service;

    @PostMapping
    public ResponseEntity<OpinionInterestPointResponse> create(@RequestBody OpinionInterestPointRequest req){
        OpinionInterestPointResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/opinion-interest-points/" + created.getIdOpinionInterestPoint())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<OpinionInterestPointResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public OpinionInterestPointResponse getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public OpinionInterestPointResponse update(@PathVariable Integer id, @RequestBody OpinionInterestPointRequest req){
        return service.update(id, req);
    }
}
