package com.epsilon.gateway.strategy;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface AuthenticationStrategy {
    Mono<Void> authenticate(ServerWebExchange exchange, GatewayFilterChain chain);
}
