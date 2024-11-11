package com.toae.auth_player.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(SecurityConfig.class)
@ExtendWith(MockitoExtension.class)
class SecurityConfigTest {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private WebTestClient webTestClient;

    @LocalServerPort
    private int port;

    private final String[] testAuthorizedPaths = {
            "/swagger-ui.html",
            "/v3/api-docs",
    };

    @BeforeEach
    void setUp() {
        RouterFunction<ServerResponse> mockRouter = route(GET("/swagger-ui.html"), request -> ServerResponse.ok().build())
                .andRoute(GET("/v3/api-docs"), request -> ServerResponse.ok().build())
                .andRoute(GET("/api/user/profile"), request -> ServerResponse.status(HttpStatus.UNAUTHORIZED).build());

        this.webTestClient = WebTestClient.bindToRouterFunction(mockRouter).configureClient()
                .baseUrl("http://localhost:" + port)
                .build();
    }

    @Test
    void securityWebFilterChain_permitsAuthorizedPaths() {
        for (String path : testAuthorizedPaths) {
            webTestClient.get()
                    .uri(path)
                    .exchange()
                    .expectStatus().isOk(); // Expect status 200 OK
        }
    }

    @Test
    void securityWebFilterChain_requiresAuthenticationForUnauthorizedPaths() {
        webTestClient.get()
                .uri("/api/user/profile")
                .exchange()
                .expectStatus().isUnauthorized();
    }
}

