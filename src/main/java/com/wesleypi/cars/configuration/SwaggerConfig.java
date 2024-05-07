package com.wesleypi.cars.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI carsOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Desafio")
                        .description("API para prova BHUT")
                        .version("1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("documentação BHUT API")
                        .url("http://api-test.bhut.com.br:3000/docs/"))
                .addServersItem(new Server()
                        .url("http://localhost:8080/api")
                        .description("")
                );
    }

}
