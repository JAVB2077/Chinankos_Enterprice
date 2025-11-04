package chinanko.chinanko.exception;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException; // El que pediste agregar
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException; // El que ya tenías
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException; // Importante para @Valid
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice; // Mejor que @ControllerAdvice para REST
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;

@Order(Ordered.HIGHEST_PRECEDENCE) // Se asegura que este manejador se ejecute primero
@RestControllerAdvice // Combina @ControllerAdvice y @ResponseBody
public class RestExceptionHandler implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ModelAttribute
    public void logRequest(HttpServletRequest req) {
        log.debug("➡  Incoming Request: {} {}", req.getMethod(), req.getRequestURI());
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true; // Aplica a todas las respuestas
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        
        // Loguea la respuesta antes de que se escriba
        if (request instanceof ServletServerHttpRequest req && response instanceof ServletServerHttpResponse res) {
            var method = req.getServletRequest().getMethod();
            var uri = req.getServletRequest().getRequestURI();
            var status = res.getServletResponse().getStatus();
            log.debug("✅ Completed [{} {}] with status {} | bodyType=[{}]",
                    method, uri, status, body != null ? body.getClass().getSimpleName() : "null");
        }
        return body;
    }

    // --- MÉTODOS DE AYUDA (Tomado del ejemplo) ---

    /**
     * Crea el cuerpo base de la respuesta de error.
     */
    private Map<String, Object> base(HttpStatus status, String message, HttpServletRequest req) {
        Map<String, Object> body = new LinkedHashMap<>(); // LinkedHashMap preserva el orden de inserción
        body.put("timestamp", Instant.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        if (req != null) {
            body.put("path", req.getRequestURI());
        }
        return body;
    }

    /**
     * Construye la ResponseEntity final y loguea la advertencia.
     */
    private ResponseEntity<Map<String, Object>> respond(HttpStatus status, String message, HttpServletRequest req) {
        log.debug("⚠  Responding with status {} {}", status.value(), status.getReasonPhrase());
        return ResponseEntity.status(status).body(base(status, message, req));
    }

    // --- MANEJADORES DE EXCEPCIONES ---

    /**
     * Manejador para 404 Not Found (El que tú tenías)
     * Se activa cuando tu servicio lanza EntityNotFoundException.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(EntityNotFoundException ex, HttpServletRequest req) {
        log.debug("Entity not found", ex);
        return respond(HttpStatus.NOT_FOUND, ex.getMessage(), req);
    }

    /**
     * Manejador para 400 Bad Request (El que tú tenías)
     * Útil para validaciones manuales en el servicio.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(IllegalArgumentException ex, HttpServletRequest req) {
        log.debug("Illegal argument", ex);
        return respond(HttpStatus.BAD_REQUEST, ex.getMessage(), req);
    }

    /**
     * Manejador para 400 Bad Request - JSON Malformado (El que tú tenías)
     * Se activa si el JSON de entrada está roto o tiene tipos incorrectos.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,Object>> handleMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest req) {
        Throwable cause = ex.getMostSpecificCause();
        Map<String,Object> details = new LinkedHashMap<>();
        String msg = "JSON mal formado o incompatible";

        if (cause instanceof com.fasterxml.jackson.core.JsonParseException jp) {
            com.fasterxml.jackson.core.JsonLocation loc = jp.getLocation();
            details.put("line", loc.getLineNr());
            details.put("column", loc.getColumnNr());
            msg = "JSON malformado: " + jp.getOriginalMessage();
        } else if (cause instanceof com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException upe) {
            details.put("field", upe.getPropertyName());
            msg = "Propiedad JSON desconocida: '" + upe.getPropertyName() + "'";
        } else if (cause instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException ife) {
            String field = ife.getPath().stream().map(ref -> ref.getFieldName()).filter(Objects::nonNull).collect(Collectors.joining("."));
            details.put("field", field);
            details.put("rejectedValue", ife.getValue());
            details.put("expectedType", ife.getTargetType().getSimpleName());
            msg = "Tipo inválido para '" + field + "'";
        } else if (cause instanceof com.fasterxml.jackson.databind.JsonMappingException jme) {
            String path = jme.getPath().stream().map(ref -> ref.getFieldName()).filter(Objects::nonNull).collect(Collectors.joining("."));
            if (!path.isEmpty()) details.put("field", path);
            msg = "Error al mapear JSON: " + jme.getOriginalMessage();
        }

        Map<String,Object> body = base(HttpStatus.BAD_REQUEST, msg, req);
        body.put("details", details);
        // Añade un ejemplo de payload esperado para ayudar al cliente
        Map<String,Object> example = new LinkedHashMap<>();
        example.put("name", "Administrador");
        body.put("example", example);
        // optionally include a short exception class when debug enabled
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /**
     * Manejador para 409 Conflict (El que pediste agregar)
     * Se activa por violaciones de constraints (ej. @Column(unique=true)).
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConflict(DataIntegrityViolationException ex, HttpServletRequest req) {
        String msg = "Conflicto con los datos. Es posible que un valor ya exista.";
        // Intenta dar un mensaje más específico si es una violación de unicidad
        Throwable cause = ex.getMostSpecificCause();
        String c = cause.getMessage();
        if (c.contains("23505") || c.toLowerCase().contains("duplicate") || c.toLowerCase().contains("unique")) {
            msg = "Ya existe un registro con ese valor único.";
        }
        log.debug("Data integrity violation: {}", c, ex);
        return respond(HttpStatus.CONFLICT, msg, req);
    }

    /**
     * ¡MUY IMPORTANTE! Manejador para 400 Bad Request - Validación de @Valid
     * Se activa cuando fallan las anotaciones de validación en tu RoleRequest.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String,Object> body = base(HttpStatus.BAD_REQUEST, "Validación fallida", req);
        Map<String, Object> fieldErrors = new LinkedHashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            Map<String,Object> info = new LinkedHashMap<>();
            info.put("message", fe.getDefaultMessage());
            info.put("rejectedValue", fe.getRejectedValue());
            fieldErrors.put(fe.getField(), info);
        }
        body.put("fieldErrors", fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /**
     * Manejador Genérico para 500 Internal Server Error
     * Atrapa cualquier otra excepción no manejada.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex, HttpServletRequest req) {
        log.error("Unhandled exception", ex); // Loguea esto como ERROR
        return respond(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", req);
    }

    @Configuration
    public class JacksonConfig {
        @Bean
        public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
            return builder -> builder.featuresToEnable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }
    }
}
