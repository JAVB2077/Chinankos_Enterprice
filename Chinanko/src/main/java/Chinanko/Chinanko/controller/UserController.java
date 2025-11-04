package chinanko.chinanko.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.UserRequest;
import chinanko.chinanko.dto.UserResponse;
import chinanko.chinanko.model.User;
import chinanko.chinanko.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "List of registered students.", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))) })
    public List<UserResponse> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    @Operation(summary = "Get all students with pagination")
    public List<UserResponse> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        if (page < 0 || pageSize < 0 || (page == 0 && pageSize == 0)) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page and pageSize cannot be negative and cannot both be 0.");
        }
        return service.findAll(page, pageSize);
    }

    @GetMapping("/{idUser}")
    public UserResponse findById(@PathVariable Integer controlNumber) {
        return service.findById(controlNumber);
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest req) {
        UserResponse created = service.create(req);
        return ResponseEntity
                .created(URI.create("/api/v1/students/" + created.getIdUser()))
                .body(created);
    }

    @PutMapping("/{idUser}")
    public UserResponse update(@PathVariable Integer idUser, @Valid @RequestBody UserRequest req) {
        return service.update(idUser, req);
    }

    @Operation(summary = "Get all user by name")
    @GetMapping("/search/{nameUser}")
    public List<UserResponse> getUserByName(@PathVariable String nameUser) {
        return service.getUserByName(nameUser);
    }

    @Operation(summary = "Get all user by email")
    @GetMapping("/search/{email}")
    public List<UserResponse> getUserByEmail(@PathVariable String email) {
        return service.getUserByEmail(email);
    }

}
