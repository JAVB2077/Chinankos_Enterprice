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

import chinanko.chinanko.dto.TypeOfEventResponse;
import chinanko.chinanko.dto.TypeOfEventtRequest;
import chinanko.chinanko.service.TypeOfEventServiceImpl;

import org.springframework.validation.annotation.Validated;

import chinanko.chinanko.service.TypeOfEventService;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag; // <-- Importa la anotación

@RestController
@RequestMapping("/api/v1/typeOfEvents")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET})
@Tag(name = "Type of events Management", 
     description = "APIs for managing types of events. Controller Author: Antony Daniel Muñoz Leal")
public class TypeOfEventController {
    private final TypeOfEventService service;

    @GetMapping
    public List<TypeOfEventResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public TypeOfEventResponse getById(Integer id){
        return service.getById(id);
    }
}
