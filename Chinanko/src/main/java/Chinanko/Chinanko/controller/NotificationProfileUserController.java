package chinanko.chinanko.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.NotificationProfileUserResponse;
import chinanko.chinanko.service.NotificationProfileUserService;

@RestController
@RequestMapping("/api/notification-profiles")
public class NotificationProfileUserController {

    private final NotificationProfileUserService service;

    public NotificationProfileUserController(NotificationProfileUserService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<NotificationProfileUserResponse>> list(){
        return ResponseEntity.ok(service.listAll());
    }
}
