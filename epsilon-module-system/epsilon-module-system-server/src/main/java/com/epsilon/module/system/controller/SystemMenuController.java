package com.epsilon.module.system.controller;

import com.epsilon.common.core.context.SecurityContextHolder;
import com.epsilon.common.web.domain.CommonResult;
import com.epsilon.module.system.module.vo.MenuVo;
import com.epsilon.module.system.module.vo.RouterVo;
import com.epsilon.module.system.service.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author eitan
 * @since 2023-11-30
 */
@RestController
@RequestMapping("/system/menu")
public class SystemMenuController {
    private SystemMenuService systemMenuService;

    @Autowired
    public SystemMenuController(SystemMenuService systemMenuService) {
        this.systemMenuService = systemMenuService;
    }

    @PostMapping("/list")
    public CommonResult<List<RouterVo>> list() {
        List<MenuVo> menuList = systemMenuService.selectMenuList(SecurityContextHolder.getUserId());
        return CommonResult.success(systemMenuService.buildRouter(menuList));
    }
}
