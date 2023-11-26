package com.epsilon.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StaticRoutesConfig {

    @Bean
    public RouteLocator staticRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes().route("epsilon-system-server", r -> r.path("/system/**").uri("http://127.0.0.1:8090")).build();
    }
}
