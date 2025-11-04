package chinanko.chinanko.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import chinanko.chinanko.dto.TownRequest;
import chinanko.chinanko.dto.TownResponse;
import chinanko.chinanko.service.TownService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TownGraphqlController {

    private final TownService service;

    // --- RESOLVERS PARA QUERIES (Lecturas) ---
    
    @QueryMapping
    public TownResponse townById(@Argument Integer idTown) {
        return service.getById(idTown);
    }

    @QueryMapping
    public List<TownResponse> allTowns(
            @Argument(name = "page") int page, 
            @Argument(name = "pageSize") int pageSize) {
        
        return service.findAll(page, pageSize);
    }

    @QueryMapping
    public TownResponse townByName(@Argument String nameTown) {
        return service.getTownByName(nameTown);
    }

    @QueryMapping
    public List<TownResponse> townsByState(@Argument String nameState) {
        return service.getTownsByState(nameState);
    }

    // --- RESOLVERS PARA MUTATIONS (Escrituras) ---

    /**
     * Se enlaza con "createTown(input: TownInput!): Town" del schema.
     * Spring mapea automáticamente 'TownInput' a la clase 'TownRequest'.
     */
    @MutationMapping
    public TownResponse createTown(@Argument(name = "input") TownRequest request) {
        // Ya no se necesita el método mapInputToRequest
        return service.create(request);
    }

    /**
     * Se enlaza con "updateTown(idTown: ID!, input: TownInput!): Town" del schema.
     */
    @MutationMapping
    public TownResponse updateTown(
            @Argument Integer idTown, 
            @Argument(name = "input") TownRequest request) {
        
        // Ya no se necesita el método mapInputToRequest
        return service.update(idTown, request);
    }
    
    // El método 'mapInputToRequest' ya no es necesario y se elimina.
}