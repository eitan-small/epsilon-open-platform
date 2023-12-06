package com.epsilon.module.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.epsilon.module.system.module.entity.SystemMenu;
import com.epsilon.module.system.module.vo.MenuVo;
import com.epsilon.module.system.module.vo.RouterVo;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author eitan
 * @since 2023-11-30
 */
public interface SystemMenuService extends IService<SystemMenu> {

    List<MenuVo> selectMenuList(Integer userId);

    List<RouterVo> buildRouter(List<MenuVo> menuList);
}
