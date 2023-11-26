package com.epsilon.module.system.module.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class LoginRespVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private String token;
}
