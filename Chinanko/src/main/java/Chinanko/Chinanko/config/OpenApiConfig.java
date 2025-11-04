package chinanko.chinanko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Chinanko API")
                        .version("1.0.0")
                        .description("API para Chinanko - turismo de pueblos mágicos")
                        .contact(new Contact().name("Chinanko Team").email("support@chinanko.example"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );
    }

}
