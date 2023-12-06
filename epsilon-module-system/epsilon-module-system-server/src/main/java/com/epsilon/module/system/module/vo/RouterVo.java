package com.epsilon.module.system.module.vo;

import lombok.Data;

import java.util.List;

@Data
public class RouterVo {
    /**
     * 路由名字
     */
    private String name;

    /**
     * 其他元素
     */
    private MetaVo meta;

    /**
     * 子路由
     */
    private List<RouterVo> children;
}
