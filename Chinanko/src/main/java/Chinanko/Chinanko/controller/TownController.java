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

import Chinanko.Chinanko.dto.TownRequest;
import Chinanko.Chinanko.dto.TownResponse;
import Chinanko.Chinanko.service.TownService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/towns")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT })
public class TownController {

    private final TownService service;

    @PostMapping
    public ResponseEntity<TownResponse> create(@RequestBody TownRequest request){
        TownResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/towns/" + created.getIdTown()))
                .body(created);
    }

    @GetMapping
    public List<TownResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{idTown}")
    public TownResponse getById(@PathVariable Integer idTown) {
        return service.getById(idTown);
    }

    @PutMapping("/{idTown}")
    public TownResponse update(@PathVariable Integer idTown, @RequestBody TownRequest request) {
        return service.update(idTown, request);
    }
}
