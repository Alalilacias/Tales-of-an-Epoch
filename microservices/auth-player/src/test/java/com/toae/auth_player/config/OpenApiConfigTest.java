package com.toae.auth_player.config;

import org.junit.jupiter.api.Test;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest(classes = OpenApiConfig.class)
class OpenApiConfigTest {

    @Mock
    private OpenApiConfig config;

    @Value("${springdoc.api.title}")
    private String apiTitle;

    @Value("${springdoc.api.version}")
    private String apiVersion;

    @Value("${springdoc.api.description}")
    private String apiDescription;

    @BeforeEach
    void setUp() {
        when(config.customOpenApi()).thenReturn(new OpenAPI().info(new Info()
                .title(apiTitle)
                .version(apiVersion)
                .description(apiDescription)));
    }

    @Test
    void customOpenApi() {
        OpenAPI openAPI = config.customOpenApi();

        assertEquals(apiTitle, openAPI.getInfo().getTitle());
        assertEquals(apiVersion, openAPI.getInfo().getVersion());
        assertEquals(apiDescription, openAPI.getInfo().getDescription());
    }
}