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

import Chinanko.Chinanko.dto.EventRequest;
import Chinanko.Chinanko.dto.EventResponse;
import Chinanko.Chinanko.service.EventService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class EventController {

    private final EventService service;

    @PostMapping
    public ResponseEntity<EventResponse> create(@RequestBody EventRequest req){
        EventResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/events/" + created.getIdEvent())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public EventResponse getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public EventResponse update(@PathVariable Integer id, @RequestBody EventRequest req){
        return service.update(id, req);
    }
}
