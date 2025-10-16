package Chinanko.Chinanko.controller;

import Chinanko.Chinanko.dto.StateRequest;
import Chinanko.Chinanko.dto.StateResponse;
import Chinanko.Chinanko.service.StateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StateController.class)
class StateControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    StateService service;

    private static final String BASE = "/api/v1/states";

    @BeforeEach
    void beforeEach() {
        reset(service);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        StateService stateService() {
            return mock(StateService.class);
        }
    }

    /* Helpers DTO */
    private StateResponse resp(int id, String name, String longitude, String latitude) {
        return StateResponse.builder()
                .idState(id)
                .nameState(name)
                .longitude(new BigDecimal(longitude))
                .latitude(new BigDecimal(latitude))
                .towns(Collections.emptyList()) // Default to empty list for simplicity
                .build();
    }

    private StateRequest req(String name, String longitude, String latitude) {
        StateRequest r = new StateRequest();
        r.setNameState(name);
        r.setLongitude(new BigDecimal(longitude));
        r.setLatitude(new BigDecimal(latitude));
        return r;
    }

    @Test
    @DisplayName("GET /api/v1/states → 200 con lista")
    void findAll_Ok() throws Exception {
        when(service.findAll()).thenReturn(List.of(
                resp(1, "Puebla", "-97.8863", "19.5443"),
                resp(2, "Veracruz", "-96.1528", "19.1738")
        ));

        mvc.perform(get(BASE).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idState").value(1))
                .andExpect(jsonPath("$[0].nameState").value("Puebla"))
                .andExpect(jsonPath("$[0].longitude").value(-97.8863))
                .andExpect(jsonPath("$[1].idState").value(2))
                .andExpect(jsonPath("$[1].latitude").value(19.1738));
    }

    @Test
    @DisplayName("GET /api/v1/states → 200 con lista vacía")
    void findAll_empty() throws Exception {
        when(service.findAll()).thenReturn(Collections.emptyList());

        mvc.perform(get(BASE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("GET /{id} existente → 200")
    void getById_ok() throws Exception {
        when(service.getById(10)).thenReturn(resp(10, "Tlaxcala", "-98.1969", "19.3175"));

        mvc.perform(get(BASE + "/{id}", 10))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idState").value(10))
                .andExpect(jsonPath("$.nameState").value("Tlaxcala"))
                .andExpect(jsonPath("$.longitude").value(-98.1969))
                .andExpect(jsonPath("$.latitude").value(19.3175));
    }

    @Test
    @DisplayName("GET /{id} no existente → 404")
    void getById_notFound() throws Exception {
        when(service.getById(888)).thenThrow(new EntityNotFoundException("State not found"));

        mvc.perform(get(BASE + "/{id}", 888))
                .andExpect(status().isNotFound());
        // No se puede verificar el cuerpo del error 404
        // porque el controlador no lo maneja explícitamente con un @ExceptionHandler.
    }

    @Test
    @DisplayName("POST create válido → 201 + Location + body")
    void create_ok() throws Exception {
        StateRequest rq = req("Oaxaca", "-96.7266", "17.0732");
        StateResponse created = resp(45, "Oaxaca", "-96.7266", "17.0732");
        when(service.create(any(StateRequest.class))).thenReturn(created);

        mvc.perform(post(BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(rq)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/v1/states/45"))
                .andExpect(jsonPath("$.idState").value(45))
                .andExpect(jsonPath("$.nameState").value("Oaxaca"))
                .andExpect(jsonPath("$.latitude").value(17.0732));
    }

    @Test
    @DisplayName("PUT update válido → 200 con body actualizado")
    void update_ok() throws Exception {
        StateRequest rq = req("Puebla de Zaragoza", "-98.2062", "19.0414");
        StateResponse updated = resp(77, "Puebla de Zaragoza", "-98.2062", "19.0414");
        when(service.update(eq(77), any(StateRequest.class))).thenReturn(updated);

        mvc.perform(put(BASE + "/{id}", 77)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(rq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idState").value(77))
                .andExpect(jsonPath("$.nameState").value("Puebla de Zaragoza"))
                .andExpect(jsonPath("$.longitude").value(-98.2062));
    }

    @Test
    @DisplayName("PUT update en no existente → 404")
    void update_notFound() throws Exception {
        when(service.update(eq(9999), any(StateRequest.class)))
                .thenThrow(new EntityNotFoundException("State not found"));

        // Create a valid request body, even though the ID won't be found
        StateRequest rq = req("Not Found State", "0.0", "0.0");

        mvc.perform(put(BASE + "/{id}", 9999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(rq)))
                .andExpect(status().isNotFound());
    }
}