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

import Chinanko.Chinanko.dto.OpinionsSuggestedPointsRequest;
import Chinanko.Chinanko.dto.OpinionsSuggestedPointsResponse;
import Chinanko.Chinanko.service.OpinionsSuggestedPointsService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/opinions-suggested-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class OpinionsSuggestedPointsController {

    private final OpinionsSuggestedPointsService service;

    @PostMapping
    public ResponseEntity<OpinionsSuggestedPointsResponse> create(@RequestBody OpinionsSuggestedPointsRequest req){
        OpinionsSuggestedPointsResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/opinions-suggested-points/" + created.getIdOpinionSuggestedPoint())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<OpinionsSuggestedPointsResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public OpinionsSuggestedPointsResponse getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public OpinionsSuggestedPointsResponse update(@PathVariable Integer id, @RequestBody OpinionsSuggestedPointsRequest req){
        return service.update(id, req);
    }
}
