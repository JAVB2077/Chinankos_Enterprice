package chinanko.chinanko.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.ProfileUserResponse;
import chinanko.chinanko.service.ProfileUserService;

@RestController
@RequestMapping("/api/profiles")
public class ProfileUserController {

    private final ProfileUserService service;

    public ProfileUserController(ProfileUserService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfileUserResponse>> list(){
        return ResponseEntity.ok(service.listAll());
    }
}
