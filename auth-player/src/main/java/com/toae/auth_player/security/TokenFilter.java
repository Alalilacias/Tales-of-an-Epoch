package com.toae.auth_player.security;

import com.toae.auth_player.service.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class TokenFilter implements WebFilter {

    private final CustomReactiveUserDetailsService detailsService;
    private final AuthService authServiceImp;

    @Autowired
    public TokenFilter(CustomReactiveUserDetailsService detailsService, AuthService authServiceImp) {
        this.detailsService = detailsService;
        this.authServiceImp = authServiceImp;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String header = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            return chain.filter(exchange);
        }

        String token = header.substring(7);
        String username = authServiceImp.getUsername(token);

        if (username != null) {
            return detailsService.findByUsername(username)
                    .flatMap(userDetails -> {
                        if (Boolean.TRUE.equals(authServiceImp.validateToken(token, userDetails).block())) {
                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                            // Set the authentication context without WebAuthenticationDetailsSource
                            return ReactiveSecurityContextHolder.getContext()
                                    .doOnNext(securityContext -> securityContext.setAuthentication(authToken))
                                    .then(chain.filter(exchange));
                        }
                        return chain.filter(exchange);
                    })
                    .switchIfEmpty(chain.filter(exchange)); // If user is not found, continue the chain
        }

        return chain.filter(exchange);
    }
}