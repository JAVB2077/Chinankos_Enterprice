package Chinanko.Chinanko.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Chinanko.Chinanko.dto.UserRequest;
import Chinanko.Chinanko.dto.UserResponse;
import Chinanko.Chinanko.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class UserController {

    private final UserService service;


    @PostMapping
    public UserResponse create(@RequestBody UserRequest request){
        return service.create(request);
    }

    @GetMapping
    public List<UserResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{idUser}")
    public UserResponse getById(@PathVariable Integer idUser){
        return service.getById(idUser);
    }

    @PutMapping("/{idUser}")
    public UserResponse update(@PathVariable Integer idUser, @RequestBody UserRequest request){
        return service.update(idUser, request);
    }


}
