package Chinanko.Chinanko.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Chinanko.Chinanko.dto.NotificationResponse;
import Chinanko.Chinanko.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> list(){
        return ResponseEntity.ok(service.listAll());
    }
}
