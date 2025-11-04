package chinanko.chinanko.controller;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import chinanko.chinanko.controller.TownController;
import chinanko.chinanko.dto.TownRequest;
import chinanko.chinanko.dto.TownResponse;
import chinanko.chinanko.service.TownService;
import jakarta.persistence.EntityNotFoundException;

@WebMvcTest(controllers = TownController.class)
class TownControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	TownService service; // Mock inyectado

	private static final String BASE = "/api/v1/towns";

	@BeforeEach
	void beforeEach() {
		reset(service);
	}

	@TestConfiguration
	static class TestConfig {
		@Bean
		TownService townService() {
			return mock(TownService.class);
		}
	}

	/* Helpers DTO */
	// Asumo que TownResponse tiene un builder y estos campos
	private TownResponse resp(int id, String name) {
		return TownResponse.builder()
				.idTown(id)
				.nameTown(name)
				.build();
	}

	// Asumo que TownRequest tiene estos setters
	private TownRequest req(String name, Integer stateId) {
		// Asumo que tu DTO usa .stateId() como en tu archivo
		return TownRequest.builder()
				.nameTown(name)
				.stateId(stateId) 
				.build();
	}

	@Test
	@DisplayName("GET /api/v1/towns → 200 con lista (defaults page=0, pageSize=10)")
	void findAll_Ok() throws Exception {
		when(service.findAll(0, 10)).thenReturn(List.of(resp(1, "Acapulco"), resp(2, "Cancun")));

		mvc.perform(get(BASE).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				// CORREGIDO:
				.andExpect(jsonPath("$[0]['id of the town']").value(1))
				.andExpect(jsonPath("$[0]['name of the town']").value("Acapulco"))
				.andExpect(jsonPath("$[1]['id of the town']").value(2));
	}

	@Test
	@DisplayName("GET /api/v1/towns → 200 con lista vacía")
	void findAll_empty() throws Exception {
		when(service.findAll(0, 10)).thenReturn(Collections.emptyList());

		mvc.perform(get(BASE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	@DisplayName("GET /api/v1/towns (con paginación) → 200")
	void findAll_withParams() throws Exception {
		when(service.findAll(2, 5)).thenReturn(List.of(resp(11, "Puebla")));

		mvc.perform(get(BASE)
				.param("page", "2")
				.param("pageSize", "5")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				// CORREGIDO:
				.andExpect(jsonPath("$[0]['id of the town']").value(11));
	}

	@Test
	@DisplayName("GET /{id} existente → 200")
	void findById_ok() throws Exception {
		when(service.getById(7)).thenReturn(resp(7, "Tijuana"));

		mvc.perform(get(BASE + "/{id}", 7))
				.andExpect(status().isOk())
				// CORREGIDO:
				.andExpect(jsonPath("$['id of the town']").value(7))
				.andExpect(jsonPath("$['name of the town']").value("Tijuana"));
	}

	@Test
	@DisplayName("GET /{id} no existente → 404")
	void findById_notFound() throws Exception {
		when(service.getById(999)).thenThrow(new EntityNotFoundException("Town not found"));

		mvc.perform(get(BASE + "/{id}", 999))
				.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("POST create válido → 201 + Location + body")
	void create_ok() throws Exception {
		TownRequest rq = req("Guadalajara", 1);
		TownResponse created = resp(123, "Guadalajara");
		when(service.create(any(TownRequest.class))).thenReturn(created);

		mvc.perform(post(BASE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(rq)))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", "/api/v1/towns/123"))
				// CORREGIDO:
				.andExpect(jsonPath("$['id of the town']").value(123))
				.andExpect(jsonPath("$['name of the town']").value("Guadalajara"));
	}

	@Test
	@DisplayName("PUT update válido → 200 con body actualizado")
	void update_ok() throws Exception {
		TownRequest rq = req("Monterrey Edit", 2);
		TownResponse updated = resp(55, "Monterrey Edit");
		when(service.update(eq(55), any(TownRequest.class))).thenReturn(updated);

		mvc.perform(put(BASE + "/{id}", 55)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(rq)))
				.andExpect(status().isOk())
				// CORREGIDO:
				.andExpect(jsonPath("$['id of the town']").value(55))
				.andExpect(jsonPath("$['name of the town']").value("Monterrey Edit"));
	}

	@Test
	@DisplayName("PUT update en no existente → 404")
	void update_notFound() throws Exception {
		when(service.update(eq(9999), any(TownRequest.class)))
				.thenThrow(new EntityNotFoundException("Town not found"));

		mvc.perform(put(BASE + "/{id}", 9999)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(req("X", 1))))
				.andExpect(status().isNotFound());
	}

	// --- Tests para endpoints adicionales de TownController ---

	@Test
	@DisplayName("GET /name/{name} existente → 200")
	void findByName_ok() throws Exception {
		when(service.getTownByName("Oaxaca")).thenReturn(resp(42, "Oaxaca"));

		mvc.perform(get(BASE + "/name/{name}", "Oaxaca"))
				.andExpect(status().isOk())
				// CORREGIDO:
				.andExpect(jsonPath("$['id of the town']").value(42))
				.andExpect(jsonPath("$['name of the town']").value("Oaxaca"));
	}

	@Test
	@DisplayName("GET /name/{name} no existente → 404")
	void findByName_notFound() throws Exception {
		when(service.getTownByName("Nowhere")).thenThrow(new EntityNotFoundException("Not found"));

		mvc.perform(get(BASE + "/name/{name}", "Nowhere"))
				.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("GET /nameState/{nameState} → 200 con lista")
	void findByState_ok() throws Exception {
		when(service.getTownsByState("Puebla")).thenReturn(List.of(resp(1, "Teziutlan"), resp(2, "Zacatlan")));

		mvc.perform(get(BASE + "/nameState/{nameState}", "Puebla"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				// CORREGIDO:
				.andExpect(jsonPath("$[0]['name of the town']").value("Teziutlan"));
	}

	@Test
	@DisplayName("GET /nameState/{nameState} → 200 con lista vacía")
	void findByState_empty() throws Exception {
		when(service.getTownsByState("Tlaxcala")).thenReturn(Collections.emptyList());

		mvc.perform(get(BASE + "/nameState/{nameState}", "Tlaxcala"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}
}