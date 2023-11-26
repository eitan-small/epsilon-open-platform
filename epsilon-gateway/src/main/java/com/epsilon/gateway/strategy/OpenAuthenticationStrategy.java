package com.epsilon.gateway.strategy;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class OpenAuthenticationStrategy implements AuthenticationStrategy {
    @Override
    public Mono<Void> authenticate(ServerWebExchange exchange, GatewayFilterChain chain) {
        return null;
    }
}
