package com.application.apigateway.infra.adapter.middleware;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import io.jsonwebtoken.Claims;


@Component
public class JwtAuthentication extends AbstractGatewayFilterFactory<JwtAuthentication.Config> {
    @Value("${jwt.secret}")
    private String secretKey;

    public static class Config {
        // Put any configuration properties for your filter here
    }

    public JwtAuthentication() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String authorizationHeader = request.getHeaders().getFirst("Authorization");

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header");
            }

            try {
                String token = authorizationHeader.substring(7);
                Claims claims = Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token)
                        .getBody();

                request = request.mutate()
                        .header("user-id", claims.getSubject())
                        .build();

                return chain.filter(exchange.mutate().request(request).build());
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
            }

        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of();
    }
}
