package com.car.system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        final Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        final Contact contact = new Contact();
        contact.setEmail("nighturbex@protonmail.com");
        contact.setName("David");
        contact.setUrl("https://www.linkedin.com/in/d%C3%A1vid-vass-aa716b1b6/");

        final License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        final Info info = new Info()
                .title("Car System API")
                .version("1.0")
                .contact(contact)
                .description("This API simulates the car mechanism.")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
