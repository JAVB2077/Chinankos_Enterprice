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

import chinanko.chinanko.dto.AddressInterestPointRequest;
import chinanko.chinanko.dto.AddressInterestPointResponse;
import chinanko.chinanko.service.AddressInterestPointService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/address-interest-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class AddressInterestPointController {

    private final AddressInterestPointService service;

    @PostMapping
    public ResponseEntity<AddressInterestPointResponse> create(@RequestBody AddressInterestPointRequest req){
        AddressInterestPointResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/address-interest-points/" + created.getIdAddressInterest())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<AddressInterestPointResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public AddressInterestPointResponse getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public AddressInterestPointResponse update(@PathVariable Integer id, @RequestBody AddressInterestPointRequest req){
        return service.update(id, req);
    }
}
