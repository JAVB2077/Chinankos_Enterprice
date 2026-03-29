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

import Chinanko.Chinanko.dto.TypeOfInterestPointResponse;
import Chinanko.Chinanko.dto.TypeOfInterestPointRequest;
import Chinanko.Chinanko.service.TypeOfInterestPointService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/typesOfInterestPoint")
@RequiredArgsConstructor
public class TypeOfInterestPointController {
    private final TypeOfInterestPointService service;

    @PostMapping
    public ResponseEntity<TypeOfInterestPointResponse> create(@RequestBody TypeOfInterestPointRequest request){
        TypeOfInterestPointResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/typesOfInterestPoint/" + created.getIdTypeOfInterestPoint()))
                .body(created);
    }

    @GetMapping
    public List<TypeOfInterestPointResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{idTypeOfInterestPoint}")
    public TypeOfInterestPointResponse getById(@PathVariable Integer idTypeOfInterestPoint) {
        return service.getById(idTypeOfInterestPoint);
    }

    @PutMapping("/{idTypeOfInterestPoint}")
    public TypeOfInterestPointResponse update(@PathVariable Integer idTypeOfInterestPoint, @RequestBody TypeOfInterestPointRequest request) {
        return service.update(idTypeOfInterestPoint, request);
    }
    
}
