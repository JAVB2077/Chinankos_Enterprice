package chinanko.chinanko.controller;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import chinanko.chinanko.dto.UserRequest;
import chinanko.chinanko.dto.UserResponse;
import chinanko.chinanko.service.UserService;
import jakarta.persistence.EntityNotFoundException;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    UserService service;

    private static final String BASE = "/api/v1/users";

    @BeforeEach
    void beforeEach() {
        reset(service);
    }

    /*
     * ===========================
     * Config de test: mock beans
     * ===========================
     */
    @TestConfiguration
    static class TestConfig {
        @Bean
        UserService studentService() {
            return mock(UserService.class);
        }
    }

    /*
     * ===========================
     * Helpers DTO
     * ===========================
     */
    private UserResponse resp(Integer cn, String nameUser, String email) {
        UserResponse r = new UserResponse();
        r.setIdUser(cn);
        r.setNameUser(nameUser);
        r.setEmail(email);
        return r;
    }

    private UserRequest req(String nameUser, String email) {
        UserRequest r = new UserRequest();
        r.setNameUser(nameUser);
        r.setEmail(email);
        return r;
    }

    /*
     * ===========================
     * GET /api/v1/users
     * ===========================
     */

    @Test
    @DisplayName("GET /api/v1/users → 200 con lista")
    void findAll_Ok() throws Exception {
        when(service.findAll()).thenReturn(List.of(resp(1, "Ana", "ana@gmail.com"), resp(2, "Luis", "luis@gmail.com")));

        mvc.perform(get(BASE).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].['idUser']").value(1))
                .andExpect(jsonPath("$[0].nameUser").value("Ana"))
                .andExpect(jsonPath("$[1].['idUser']").value(2));
    }

    @Test
    @DisplayName("GET /api/v1/users → 200 con lista vacía")
    void findAll_empty() throws Exception {
        when(service.findAll()).thenReturn(Collections.emptyList());

        mvc.perform(get(BASE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    /*
     * ==========================================
     * GET /api/v1/users/pagination?page=&pageSize=
     * ==========================================
     */

    @ParameterizedTest(name = "GET /pagination?page={0}&pageSize={1} → 200")
    @CsvSource({
            "0,10",
            "1,1",
            "2,50",
            "5,5"
    })
    @DisplayName("GET paginado: parámetros válidos")
    void pagination_ok(int page, int size) throws Exception {
        when(service.findAll(page, size)).thenReturn(List.of(resp(100, "Eva", "Pérez")));

        mvc.perform(get(BASE + "/pagination")
                .queryParam("page", String.valueOf(page))
                .queryParam("pageSize", String.valueOf(size))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].['idUser']").value(100))
                .andExpect(jsonPath("$[0].name").value("Eva"));
    }

    @ParameterizedTest(name = "GET /pagination?page={0}&pageSize={1} inválidos → 400")
    @CsvSource({
            "-1,10",
            "0,0",
            "0,-5",
            "-3,-3"
    })
    @DisplayName("GET paginado: parámetros inválidos → 400")
    void pagination_badRequest(int page, int size) throws Exception {
        when(service.findAll(page, size)).thenThrow(new IllegalArgumentException("Invalid paging params"));

        mvc.perform(get(BASE + "/pagination")
                .queryParam("page", String.valueOf(page))
                .queryParam("pageSize", String.valueOf(size)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error", containsStringIgnoringCase("invalid")));
    }

    /*
     * ======================================
     * GET /api/v1/users/{id}
     * ======================================
     */

    @Test
    @DisplayName("GET /{id} existente → 200")
    void findById_ok() throws Exception {
        when(service.findById(7)).thenReturn(resp(7, "Arturo", "arturo@gmail.com"));

        mvc.perform(get(BASE + "/{id}", 7))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['idUser']").value(7))
                .andExpect(jsonPath("$.name").value("Arturo"));
    }

    @Test
    @DisplayName("GET /{id} no existente → 404")
    void findById_notFound() throws Exception {
        when(service.findById(999)).thenThrow(new EntityNotFoundException("User not found"));

        mvc.perform(get(BASE + "/{id}", 999))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    @DisplayName("GET /{id} no numérico → 400 (binding)")
    void findById_badPath() throws Exception {
        // Doc: Conversión fallida de path variable → 400
        mvc.perform(get(BASE + "/abc"))
                .andExpect(status().isBadRequest());
    }

    /*
     * ==============================
     * POST /api/v1/users
     * ==============================
     */

    @Test
    @DisplayName("POST create válido → 201 + Location + body")
    void create_ok() throws Exception {
        UserRequest rq = req("Arturo", "arturo@gmail.com");
        UserResponse created = resp(1234, "Ana", "ana@gmail.com");
        when(service.create(any(UserRequest.class))).thenReturn(created);

        mvc.perform(post(BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(rq)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/v1/users/1234"))
                .andExpect(jsonPath("$.['Control number']").value(1234))
                .andExpect(jsonPath("$.name").value("María"))
                .andExpect(jsonPath("$.lastname").value("Pérez"));
    }

    @Test
    @DisplayName("POST create inválido → 400 por @Valid")
    void create_invalidBody() throws Exception {
        mvc.perform(post(BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    /*
     * ==================================
     * PUT /api/v1/students/{id}
     * ==================================
     */

    @Test
    @DisplayName("PUT update válido → 200 con body actualizado")
    void update_ok() throws Exception {
        UserRequest rq = req("Name Edited", "german@gmail.com");
        UserResponse updated = resp(55, "Name Edited", "German");
        when(service.update(eq(55), any(UserRequest.class))).thenReturn(updated);

        mvc.perform(put(BASE + "/{id}", 55)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(rq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['Control number']").value(55))
                .andExpect(jsonPath("$.name").value("Name Edited"));
    }

    @Test
    @DisplayName("PUT update en no existente → 404")
    void update_notFound() throws Exception {
        // Doc: Servicio indica que no existe → 404
        when(service.update(eq(9999), any(UserRequest.class)))
                .thenThrow(new EntityNotFoundException("User not found"));

        mvc.perform(put(BASE + "/{id}", 9999)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req("X", "juan@gmail.com"))))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    @DisplayName("PUT update con body inválido → 400 por @Valid")
    void update_invalidBody() throws Exception {
        // Doc: Falla de validación → 400
        mvc.perform(put(BASE + "/{id}", 10)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    /*
     * =============================================
     * GET /api/v1/users/search/{name}
     * =============================================
     */

    @Test
    @DisplayName("GET search con resultados → 200 y lista")
    void search_ok() throws Exception {
        // Doc: Búsqueda por nombre del usuario(case-insensitive parcial)
        when(service.getUserByName("ana"))
                .thenReturn(List.of(resp(1, "Ana", "ana@gmail.com"), resp(3, "Juan", "juan@gmail.com")));

        mvc.perform(get(BASE + "/search/{nameUser}", "ana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", containsStringIgnoringCase("ana")));
    }

    @Test
    @DisplayName("GET search sin resultados → 200 y []")
    void search_empty() throws Exception {
        // Doc: Sin coincidencias → 200 y arreglo vacío
        when(service.getUserByName("zzz")).thenReturn(Collections.emptyList());

        mvc.perform(get(BASE + "/search/{nameUser}", "zzz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    /*
     * =============================================
     * GET /api/v1/users/search/{email}
     * =============================================
     */

    @Test
    @DisplayName("GET search con resultados → 200 y lista")
    void search_ok_Email() throws Exception {
        when(service.getUserByEmail("ana@gmail.com"))
                .thenReturn(List.of(resp(1, "Ana", "ana@gmail.com"), resp(3, "Juan", "juan@gmail.com")));

        mvc.perform(get(BASE + "/search/{email}", "ana@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].email", containsStringIgnoringCase("ana@gmail.com")));
    }

    @Test
    @DisplayName("GET search sin resultados → 200 y []")
    void search_empty_Email() throws Exception {
        when(service.getUserByName("zzz")).thenReturn(Collections.emptyList());

        mvc.perform(get(BASE + "/search/{email}", "zzz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    /*
     * =====================================
     * Headers: CORS / Content Negotiation
     * =====================================
     */

    @Test
    @DisplayName("CORS: Access-Control-Allow-Origin")
    void cors_header_present() throws Exception {
        when(service.findAll()).thenReturn(Collections.emptyList());

        mvc.perform(get(BASE).header("Origin", "https://tu-frontend.com").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "https://tu-frontend.com"));
    }

    @Test
    @DisplayName("Content negotiation: Accept JSON → application/json")
    void contentNegotiation_json() throws Exception {
        when(service.findAll()).thenReturn(List.of(resp(1, "A", "Pérez")));

        mvc.perform(get(BASE).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
