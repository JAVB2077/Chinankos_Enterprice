package Chinanko.Chinanko.controller;

import java.net.URI;
import java.util.List;

import java.util.Map;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;

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

import Chinanko.Chinanko.dto.RoleRequest;
import Chinanko.Chinanko.dto.RoleResponse;
import Chinanko.Chinanko.service.RoleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class RoleController {

    private final RoleService service;

    @PostMapping
    public ResponseEntity<RoleResponse> create(@RequestBody RoleRequest request) {
        RoleResponse created = service.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/roles/" + created.getIdRol()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> findAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{idRole}")
    /*
    public RoleResponse findById(epathvariab1e Integer idR01e) {
        return service.findById(idR01e);
        */
    public ResponseEntity<?> findById(@PathVariable Integer idRole) {
        try {
            RoleResponse resp = service.findById(idRole);
            return ResponseEntity.ok(resp);
        } catch (EntityNotFoundException ex) {
            Map<String, Object> body = Map.of("status", HttpStatus.NOT_FOUND.value(), "error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    @PutMapping("/{idRole}")
    /*  
    public RoleResponse update(@PathVariab1e Integer idR01e. @RequestBody RoleRequest req) {
            return service. update(idR01e, req); 
            */
    public ResponseEntity<?> update(@PathVariable Integer idRole, @RequestBody RoleRequest req) {
        try {
            RoleResponse updated = service.update(idRole, req);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException ex) {
            Map<String, Object> body = Map.of("status", HttpStatus.NOT_FOUND.value(), "error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }
}
