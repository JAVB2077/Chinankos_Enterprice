package Chinanko.Chinanko.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import Chinanko.Chinanko.dto.RoleRequest;
import Chinanko.Chinanko.dto.RoleResponse;
import Chinanko.Chinanko.service.RoleService;
import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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

@WebMvcTest(controllers = RoleController.class)
class RoleControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	RoleService service;

	private static final String BASE = "/api/v1/roles";

	@BeforeEach
	void beforeEach() {
		reset(service);
	}

	@TestConfiguration
	static class TestConfig {
		@Bean
		RoleService roleService() {
			return mock(RoleService.class);
		}
	}

	/* Helpers DTO */
	private RoleResponse resp(int id, String name) {
		return RoleResponse.builder()
				.idRol(id)
				.nameRol(name)
				.build();
	}

	private RoleRequest req(String name) {
		RoleRequest r = new RoleRequest();
		r.setNameRol(name);
		return r;
	}

	@Test
	@DisplayName("GET /api/v1/roles → 200 con lista")
	void findAll_Ok() throws Exception {
		when(service.getAll()).thenReturn(List.of(resp(1, "Admin"), resp(2, "User")));

		mvc.perform(get(BASE).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].idRol").value(1))
				.andExpect(jsonPath("$[0].nameRol").value("Admin"))
				.andExpect(jsonPath("$[1].idRol").value(2));
	}

	@Test
	@DisplayName("GET /api/v1/roles → 200 con lista vacía")
	void findAll_empty() throws Exception {
		when(service.getAll()).thenReturn(Collections.emptyList());

		mvc.perform(get(BASE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	@DisplayName("GET /{id} existente → 200")
	void findById_ok() throws Exception {
		when(service.findById(7)).thenReturn(resp(7, "Manager"));

		mvc.perform(get(BASE + "/{id}", 7))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.idRol").value(7))
				.andExpect(jsonPath("$.nameRol").value("Manager"));
	}

	@Test
	@DisplayName("GET /{id} no existente → 404")
	void findById_notFound() throws Exception {
		when(service.findById(999)).thenThrow(new EntityNotFoundException("Role not found"));

		mvc.perform(get(BASE + "/{id}", 999))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value(404));
	}

	@Test
	@DisplayName("POST create válido → 201 + Location + body")
	void create_ok() throws Exception {
		RoleRequest rq = req("Operator");
		RoleResponse created = resp(123, "Operator");
		when(service.create(any(RoleRequest.class))).thenReturn(created);

		mvc.perform(post(BASE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(rq)))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", "/api/v1/roles/123"))
				.andExpect(jsonPath("$.idRol").value(123))
				.andExpect(jsonPath("$.nameRol").value("Operator"));
	}

	@Test
	@DisplayName("PUT update válido → 200 con body actualizado")
	void update_ok() throws Exception {
		RoleRequest rq = req("Role Edited");
		RoleResponse updated = resp(55, "Role Edited");
		when(service.update(eq(55), any(RoleRequest.class))).thenReturn(updated);

		mvc.perform(put(BASE + "/{id}", 55)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(rq)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.idRol").value(55))
				.andExpect(jsonPath("$.nameRol").value("Role Edited"));
	}

	@Test
	@DisplayName("PUT update en no existente → 404")
	void update_notFound() throws Exception {
		when(service.update(eq(9999), any(RoleRequest.class)))
				.thenThrow(new EntityNotFoundException("Role not found"));

		mvc.perform(put(BASE + "/{id}", 9999)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(req("X"))))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value(404));
	}
}
