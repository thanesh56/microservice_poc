package com.thanesh.employeeapp.employeeservice.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Employee App API")
                        .description("This is a simple POC for Micro-service Architecture")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact().name("Thaneshwar Sahu").email("er.thaneshwarsahu@gmail.com"))
                )
                .externalDocs(new ExternalDocumentation().url("https://github.com/thanesh56/microservice_poc"));
    }
}
