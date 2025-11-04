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

import chinanko.chinanko.dto.StateOfEventResponse;
import chinanko.chinanko.service.StateOfEventService;

import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag; // <-- Importa la anotación

@RestController
@RequestMapping("/api/v1/statesOfEvents")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,})
@Tag(name = "States of events Management", 
     description = "APIs for managing states of events. Controller Author: Antony Daniel Muñoz Leal")
public class StateOfEventController {
    private final StateOfEventService service;

    @GetMapping
    @Operation(summary = "Get all states of events")
    public List<StateOfEventResponse> getAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a state of event by its ID")
    public StateOfEventResponse getById(@PathVariable Integer id){
        return service.findById(id);
    }
}
