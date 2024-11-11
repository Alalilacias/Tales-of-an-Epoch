package com.toae.auth_player.security;

import com.toae.auth_player.entity.User;
import com.toae.auth_player.service.interfaces.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@ExtendWith(MockitoExtension.class)
class TokenFilterTest {

    @Mock
    private CustomReactiveUserDetailsService detailsService;

    @Mock
    private AuthService authServiceImp;

    @InjectMocks
    private TokenFilter tokenFilter;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        RouterFunction<ServerResponse> routerFunction = route(GET("/test"), request -> ServerResponse.ok().build());
        webTestClient = WebTestClient.bindToRouterFunction(routerFunction)
                .webFilter(tokenFilter)
                .build();
    }

    @Test
    void filter() {
        String token = "validToken";
        String username = "testUser";

        // Mock the AuthService and CustomReactiveUserDetailsService behavior
        when(authServiceImp.getUsername(token)).thenReturn(username);
        when(authServiceImp.validateToken(any(), any(UserDetails.class))).thenReturn(Mono.just(true));
        when(detailsService.findByUsername(username)).thenReturn(Mono.just(new CustomUserDetails(new User("1", "testUser", "hashedPassword", "test@example.com", "123456789", "123 Main St"))));

        webTestClient.get()
                .uri("/test")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .exchange()
                .expectStatus().isOk();
    }
}
