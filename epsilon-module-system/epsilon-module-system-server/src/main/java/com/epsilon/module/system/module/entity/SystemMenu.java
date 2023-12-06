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
 * 菜单权限表
 * </p>
 *
 * @author eitan
 * @since 2023-11-30
 */
@Getter
@Setter
@TableName("system_menu")
public class SystemMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 菜单类型 1:目录|2:菜单|3:按钮
     */
    @TableField("type")
    private Integer type;

    /**
     * 显示顺序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 组件名
     */
    @TableField("component_name")
    private String componentName;

    /**
     * 菜单状态
     */
    @TableField("status")
    private Integer status;
}
