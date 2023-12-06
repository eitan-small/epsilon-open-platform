package com.epsilon.module.system.module.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVo {

    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单类型 1:目录|2:菜单|3:按钮
     */
    private Integer type;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 组件名
     */
    private String componentName;

    /**
     * 子菜单
     */
    private List<MenuVo> children;
}
