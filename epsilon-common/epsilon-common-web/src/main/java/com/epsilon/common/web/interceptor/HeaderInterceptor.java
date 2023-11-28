package com.epsilon.common.web.interceptor;

import com.epsilon.common.core.constant.CacheConstants;
import com.epsilon.common.core.constant.SecurityConstants;
import com.epsilon.common.core.context.SecurityContextHolder;
import com.epsilon.common.core.utils.servlet.ServletUtils;
import com.epsilon.common.redis.service.CacheService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * 自定义请求头拦截器，将Header数据封装到线程变量中方便获取
 * 注意：此拦截器会同时验证当前用户有效期自动刷新有效期
 */
public class HeaderInterceptor implements AsyncHandlerInterceptor {
    private final CacheService cacheService;

    public HeaderInterceptor(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String userId = ServletUtils.getHeader(request, SecurityConstants.DETAILS_USER_ID);
        SecurityContextHolder.setUserId(userId);
        SecurityContextHolder.setUserName(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USERNAME));
        // 刷新过期时间
        cacheService.expire(CacheConstants.SYSTEM_USER_KEY + userId, CacheConstants.EXPIRATION, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SecurityContextHolder.remove();
    }
}
