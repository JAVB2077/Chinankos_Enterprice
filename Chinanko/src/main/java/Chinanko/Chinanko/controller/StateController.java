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

import Chinanko.Chinanko.dto.StateRequest;
import Chinanko.Chinanko.dto.StateResponse;
import Chinanko.Chinanko.service.StateService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/states")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT })

public class StateController {

    private final StateService service;

    @PostMapping
    public ResponseEntity<StateResponse> create(@RequestBody StateRequest request) {
        StateResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/states/" + created.getIdState()))
                .body(created);
    }

    @GetMapping
    public List<StateResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{idState}")
    public StateResponse getById(@PathVariable Integer idState) {
        return service.getById(idState);
    }

    @PutMapping("/{idState}")
    public StateResponse update(@PathVariable Integer idState, @RequestBody StateRequest request) {
        return service.update(idState, request);
    }
}
