package com.epsilon.module.system.module.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Data
public class UserInfoVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 登录日期
     */
    private String loginDate;

    /**
     * 角色列表
     */
    private Set<String> roles;
}
