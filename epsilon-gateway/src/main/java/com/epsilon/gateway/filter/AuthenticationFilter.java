package com.epsilon.gateway.filter;

import com.epsilon.common.core.utils.StringUtils;
import com.epsilon.gateway.properties.AuthenticationProperties;
import com.epsilon.gateway.strategy.AuthenticationStrategy;
import com.epsilon.gateway.strategy.OpenAuthenticationStrategy;
import com.epsilon.gateway.strategy.SystemAuthenticationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private final AuthenticationProperties authenticationProperties;
    private final OpenAuthenticationStrategy openAuthenticationStrategy;
    private final SystemAuthenticationStrategy systemAuthenticationStrategy;

    @Autowired
    public AuthenticationFilter(AuthenticationProperties authenticationProperties,
                                OpenAuthenticationStrategy openAuthenticationStrategy,
                                SystemAuthenticationStrategy systemAuthenticationStrategy) {
        this.authenticationProperties = authenticationProperties;
        this.openAuthenticationStrategy = openAuthenticationStrategy;
        this.systemAuthenticationStrategy = systemAuthenticationStrategy;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String url = request.getURI().getPath();
        // 如果在白名单内则不需要
        if (StringUtils.matches(url, authenticationProperties.getWhitelist())) {
            return chain.filter(exchange);
        }

        // 获取鉴权策略
        AuthenticationStrategy authenticationStrategy = determineAuthenticationStrategy(url);

        // 鉴权
        Mono<Void> mono = authenticationStrategy.authenticate(exchange, chain);

        return mono;
    }

    private AuthenticationStrategy determineAuthenticationStrategy(String url) {
        // 如果是系统路径则使用系统鉴权策略，否则使用三方鉴权策略
        if (StringUtils.matches(url, authenticationProperties.getSystemPaths())) {
            return systemAuthenticationStrategy;
        }
        return openAuthenticationStrategy;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
