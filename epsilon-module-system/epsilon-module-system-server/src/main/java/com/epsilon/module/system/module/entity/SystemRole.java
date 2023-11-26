package com.epsilon.module.system.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.epsilon.common.mybatis.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author eitan
 * @since 2023-11-19
 */
@Getter
@Setter
@TableName("system_role")
public class SystemRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    @TableField("name")
    private String name;

    /**
     * 角色权限字符串
     */
    @TableField("code")
    private String code;

    /**
     * 角色状态（0正常 1停用）
     */
    @TableField("status")
    private Byte status;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
