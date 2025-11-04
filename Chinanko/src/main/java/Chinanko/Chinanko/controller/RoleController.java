package chinanko.chinanko.controller;

import chinanko.chinanko.dto.RoleRequest;
import chinanko.chinanko.dto.RoleResponse;
import chinanko.chinanko.service.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@Tag(name = "Roles", description = "API para la gestión de Roles")
// Tu configuración de CORS es funcional, aunque a menudo se prefiere una configuración global para toda la aplicación.
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class RoleController {

    private final RoleService roleService;

    @Operation(summary = "Crear un nuevo rol")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Rol creado exitosamente", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))
        }),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<RoleResponse> create(@Valid @RequestBody RoleRequest request) {
        RoleResponse createdRole = roleService.create(request);
        
        // Construye la URI del nuevo recurso. Es una forma más robusta y estándar.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdRole.getIdRol())
                .toUri();

        return ResponseEntity.created(location).body(createdRole);
    }

    @Operation(summary = "Obtener todos los roles")
    @ApiResponse(responseCode = "200", description = "Lista de roles encontrados", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))
    })
    @GetMapping
    public ResponseEntity<List<RoleResponse>> findAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @Operation(summary = "Obtener un rol por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol encontrado", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))
        }),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado", content = @Content)
    })
    @GetMapping("/{idRole}")
    public ResponseEntity<RoleResponse> findById(@PathVariable Integer idRole) {
        // Se asume que tu servicio lanza una excepción si no lo encuentra.
        // Dicha excepción debería ser manejada por un @ControllerAdvice global.
        return ResponseEntity.ok(roleService.findById(idRole));
    }

    @Operation(summary = "Actualizar un rol existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol actualizado exitosamente", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))
        }),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado", content = @Content)
    })
    @PutMapping("/{idRole}")
    public ResponseEntity<RoleResponse> update(@PathVariable Integer idRole, @Valid @RequestBody RoleRequest request) {
        return ResponseEntity.ok(roleService.update(idRole, request));
    }
}