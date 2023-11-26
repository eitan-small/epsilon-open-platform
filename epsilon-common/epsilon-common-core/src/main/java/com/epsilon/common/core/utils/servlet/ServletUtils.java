package com.epsilon.common.core.utils.servlet;

import cn.hutool.core.util.StrUtil;
import com.epsilon.common.core.utils.json.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import reactor.core.publisher.Mono;

import java.io.Serial;
import java.io.Serializable;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;

/**
 * 客户端工具类
 */
public class ServletUtils {
    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        try {
            return getRequestAttributes().getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        try {
            return getRequestAttributes().getResponse();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            return (ServletRequestAttributes) attributes;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 内容编码
     *
     * @param str 内容
     * @return 编码后的内容
     */
    public static String urlEncode(String str) {
        return URLEncoder.encode(str, StandardCharsets.UTF_8);
    }

    /**
     * 内容解码
     *
     * @param str 内容
     * @return 解码后的内容
     */
    public static String urlDecode(String str) {
        return URLDecoder.decode(str, StandardCharsets.UTF_8);
    }

    public static String getHeader(HttpServletRequest request, String name) {
        String value = request.getHeader(name);
        if (StringUtils.isEmpty(value)) {
            return StringUtils.EMPTY;
        }
        return urlDecode(value);
    }

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedCaseInsensitiveMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Object value) {
        return webFluxResponseWriter(response, HttpStatus.OK, value, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param code     响应状态码
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Object value, int code) {
        return webFluxResponseWriter(response, HttpStatus.OK, value, code);
    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param status   http状态码
     * @param code     响应状态码
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, HttpStatus status, Object value, int code) {
        return webFluxResponseWriter(response, MediaType.APPLICATION_JSON_VALUE, status, value, code);
    }

    /**
     * 设置webflux模型响应
     *
     * @param response    ServerHttpResponse
     * @param contentType content-type
     * @param status      http状态码
     * @param code        响应状态码
     * @param value       响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, String contentType, HttpStatus status, Object value, int code) {
        response.setStatusCode(status);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, contentType);
        R<?> result = R.error(code, value.toString());
        DataBuffer dataBuffer = response.bufferFactory().wrap(JsonUtils.toJsonString(result).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    @Data
    public static class R<T> implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        /**
         * 成功
         */
        public static final int SUCCESS = HttpStatus.OK.value();

        /**
         * 失败
         */
        public static final int ERROR = HttpStatus.INTERNAL_SERVER_ERROR.value();

        /**
         * 状态码
         */
        private int code;

        /**
         * 返回信息
         */
        private String message;

        /**
         * 数据对象
         */
        private T data;

        public static <T> R<T> success() {
            return restResult(null, SUCCESS, StrUtil.EMPTY);
        }

        public static <T> R<T> success(T data) {
            return restResult(data, SUCCESS, StrUtil.EMPTY);
        }

        public static <T> R<T> success(T data, String msg) {
            return restResult(data, SUCCESS, msg);
        }

        public static <T> R<T> error() {
            return restResult(null, ERROR, StrUtil.EMPTY);
        }

        public static <T> R<T> error(String msg) {
            return restResult(null, ERROR, msg);
        }

        public static <T> R<T> error(int code, String msg) {
            return restResult(null, code, msg);
        }

        private static <T> R<T> restResult(T data, int code, String msg) {
            R<T> apiResult = new R<>();
            apiResult.setCode(code);
            apiResult.setData(data);
            apiResult.setMessage(msg);
            return apiResult;
        }
    }
}
