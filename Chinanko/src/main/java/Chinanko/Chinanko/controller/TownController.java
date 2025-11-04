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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.TownRequest;
import chinanko.chinanko.dto.TownResponse;
import chinanko.chinanko.service.TownService;

import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag; // <-- Importa la anotación

@RestController
@RequestMapping("/api/v1/towns")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT })
@Tag(name = "Town Management", 
     description = "APIs for managing towns. Controller Author: Antony Daniel Muñoz Leal")
public class TownController {

    // Injects the Town service
    private final TownService service;

    @PostMapping // Handles POST requests
    @Operation(summary = "Create a new town")
    public ResponseEntity<TownResponse> create(@Validated @RequestBody TownRequest request) {
        // Creates a new Town
        TownResponse created = service.create(request);
        
        // Returns 201 Created status with the new resource URI
        return ResponseEntity.created(URI.create("/api/v1/towns/" + created.getIdTown()))
                .body(created);
    }

    @GetMapping // Handles GET requests
    @Operation(summary = "Get all towns with pagination")
    public List<TownResponse> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        // Returns a list of all towns
        return service.findAll(page, pageSize);
    }

    @GetMapping("/{idTown}") // Handles GET requests by ID
    @Operation(summary = "Get a town by its ID")
    public TownResponse getById(@PathVariable Integer idTown) {
        // Finds a Town by its ID
        return service.getById(idTown);
    }

    @GetMapping("/name/{nameTown}") // Handles GET requests by name
    @Operation(summary = "Get a town by its name")
    public TownResponse getTownByName(@PathVariable String nameTown) {
        // Finds a Town by its name
        return service.getTownByName(nameTown);
    }

    @GetMapping("/nameState/{nameState}") // Handles GET requests by state name
    @Operation(summary = "Get towns by state name")
    public List<TownResponse> getToensByState(@PathVariable String nameState) {
        // Finds all towns in a specific state
        return service.getTownsByState(nameState);
    }

    @PutMapping("/{idTown}") // Handles PUT requests by ID
    @Operation(summary = "Update a town by its ID")
    public TownResponse update(@Validated @PathVariable Integer idTown, @RequestBody TownRequest request) {
        // Updates an existing Town
        return service.update(idTown, request);
    }
}