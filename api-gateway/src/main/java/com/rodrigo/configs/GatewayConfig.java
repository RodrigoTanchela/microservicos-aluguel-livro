package com.rodrigo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Rota para o microserviço cliente (autenticação)
                .route("cliente", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://cliente"))
                // Rota para o microserviço pessoa
                .route("pessoa", r -> r.path("/pessoa/**")
                		.filters(f -> f.filter(filter))
                        .uri("lb://pessoa"))
                // Rota para o microserviço multa
                .route("multa", r -> r.path("/multa/**")
                		.filters(f -> f.filter(filter))
                        .uri("lb://multa"))
                // Rota para o microserviço book
                .route("book", r -> r.path("/book/**")
                		.filters(f -> f.filter(filter))
                        .uri("lb://book"))
                // Rota para o microserviço aluguel
                .route("emprestimo", r -> r.path("/emprestimo/**")
                		.filters(f -> f.filter(filter))
                        .uri("lb://emprestimo"))
                .build();
    }
}