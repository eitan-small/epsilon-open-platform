package com.epsilon.common.web.config;

import com.epsilon.common.redis.service.CacheService;
import com.epsilon.common.web.handler.GlobalExceptionHandler;
import com.epsilon.common.web.interceptor.HeaderInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebAutoConfiguration implements WebMvcConfigurer {
    /**
     * 不需要拦截地址
     */
    public static final String[] excludeUrls = {"/system/user/login", "/system/user/logout", "/system/user/refresh"};

    private CacheService cacheService;

    @Autowired
    public WebAutoConfiguration(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getHeaderInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludeUrls)
                .order(-10);
    }

    /**
     * 自定义请求头拦截器
     */
    public HeaderInterceptor getHeaderInterceptor() {
        return new HeaderInterceptor(cacheService);
    }
}
