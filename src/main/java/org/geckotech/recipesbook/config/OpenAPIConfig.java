package org.geckotech.recipesbook.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Development server");

        Contact contact = new Contact();
        contact.setName("Recipe Book API");
        contact.setEmail("your.email@example.com");
        contact.setUrl("https://www.example.com");

        License license = new License()
            .name("MIT License")
            .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
            .title("Recipe Book API")
            .version("1.0")
            .contact(contact)
            .description("This API exposes endpoints for managing recipes.")
            .license(license);

        return new OpenAPI()
            .info(info)
            .servers(List.of(devServer));
    }
} 