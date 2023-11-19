package com.epsilon.common.web.exception;

import lombok.Data;

import java.io.Serial;

/**
 * 业务异常
 */
@Data
public final class ServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    public ServiceException(String message) {
        this.message = message;
    }
}
