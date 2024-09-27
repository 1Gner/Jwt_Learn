
package com.example.demo.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class swaggerConfig {

    private Contact contato() {
        return new Contact(
                "Seu nome",
                "http://www.seusite.com.br",
                "voce@seusite.com.br");
    }

    @Bean
    public Docket detalheApi() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .build()
                .apiInfo(informacoesApi())
                .consumes(new HashSet<>(Arrays.asList("application/json")))
                .produces(new HashSet<>(Arrays.asList("application/json")));
    }

    private ApiInfo informacoesApi() {
        return new ApiInfoBuilder()
                .title("Title - Rest API")
                .description("API exemplo de uso de Spring Boot REST API")
                .version("1.0")
                .termsOfServiceUrl("Termo de uso: Open Source")
                .license("Licença - Sua Empresa")
                .licenseUrl("http://www.seusite.com.br")
                .contact(contato())
                .build();
    }
}