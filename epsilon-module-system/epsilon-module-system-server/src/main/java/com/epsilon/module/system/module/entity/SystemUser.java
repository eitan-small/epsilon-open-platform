package com.epsilon.module.system.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.epsilon.common.mybatis.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author eitan
 * @since 2023-11-19
 */
@Getter
@Setter
@TableName("system_user")
public class SystemUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户账号
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 用户昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机号码
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 用户性别
     */
    @TableField("sex")
    private Byte sex;

    /**
     * 头像地址
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 帐号状态（0正常 1停用）
     */
    @TableField("status")
    private Byte status;

    /**
     * 最后登录IP
     */
    @TableField("login_ip")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @TableField("login_date")
    private LocalDateTime loginDate;
}
