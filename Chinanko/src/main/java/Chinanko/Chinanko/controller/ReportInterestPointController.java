package Chinanko.Chinanko.controller;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Chinanko.Chinanko.dto.ReportInterestPointRequest;
import Chinanko.Chinanko.dto.ReportInterestPointResponse;
import Chinanko.Chinanko.service.ReportInterestPointService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reports/interest-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE })
public class ReportInterestPointController {
    private final ReportInterestPointService service;

    @PostMapping
    public ResponseEntity<ReportInterestPointResponse> create(@RequestBody ReportInterestPointRequest request) {
        ReportInterestPointResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/reports/interest-points/" + created.getIdReportInterestPoint()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<ReportInterestPointResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportInterestPointResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportInterestPointResponse> update(
            @PathVariable Integer id, 
            @RequestBody ReportInterestPointRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    // Endpoints adicionales específicos para reportes
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReportInterestPointResponse>> getByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(service.getByUser(userId));
    }

    @GetMapping("/interest-point/{interestPointId}")
    public ResponseEntity<List<ReportInterestPointResponse>> getByInterestPoint(@PathVariable Integer interestPointId) {
        return ResponseEntity.ok(service.getByInterestPoint(interestPointId));
    }

    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<ReportInterestPointResponse>> getByType(@PathVariable Integer typeId) {
        return ResponseEntity.ok(service.getByType(typeId));
    }
    
}
