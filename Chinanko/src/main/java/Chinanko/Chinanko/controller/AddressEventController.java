package chinanko.chinanko.controller;

import java.net.URI;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.AddressEventRequest;
import chinanko.chinanko.dto.AddressEventResponse;
import chinanko.chinanko.service.AddressEventService;

import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/address-events")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class AddressEventController {

    private final AddressEventService service;

    @PostMapping
    public ResponseEntity<AddressEventResponse> create(@RequestBody AddressEventRequest req){
        AddressEventResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/address-events/" + created.getIdAddresEvent())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<AddressEventResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public AddressEventResponse getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public AddressEventResponse update(@PathVariable Integer id, @RequestBody AddressEventRequest req){
        return service.update(id, req);
    }
}
