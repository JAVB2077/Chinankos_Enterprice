package Chinanko.Chinanko.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Chinanko.Chinanko.dto.TypeOfNotificationRequest;
import Chinanko.Chinanko.dto.TypeOfNotificationResponse;
import Chinanko.Chinanko.service.TypeOfNotificationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/types")
public class TypeOfNotificationController {

    private final TypeOfNotificationService service;


    @PostMapping
    public ResponseEntity<TypeOfNotificationResponse> create(@RequestBody TypeOfNotificationRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<TypeOfNotificationResponse>> list(){
        return ResponseEntity.ok(service.findAll());
    }
}
