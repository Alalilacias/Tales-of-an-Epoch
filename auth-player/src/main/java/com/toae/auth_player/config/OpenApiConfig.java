package com.toae.auth_player.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${springdoc.api.title}")
    private String apiTitle;

    @Value("${springdoc.api.version}")
    private String apiVersion;

    @Value("${springdoc.api.description}")
    private String apiDescription;

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(
                new Info()
                        .title(apiTitle)
                        .version(apiVersion)
                        .description(apiDescription)
        );
    }
}

