package com.toae.auth_player.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class OpenApiConfig {

    private final Environment environment;

    @Autowired
    public OpenApiConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(
                new Info()
                        .title(environment.getProperty("springdoc.api.title"))
                        .version(environment.getProperty("springdoc.api.version"))
                        .description(environment.getProperty("springdoc.api.description"))
        );
    }
}
