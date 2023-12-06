package com.epsilon.module.system.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.epsilon.common.core.utils.StringUtils;
import com.epsilon.module.system.convert.menu.MenuConvert;
import com.epsilon.module.system.convert.user.UserConvert;
import com.epsilon.module.system.enums.CommonStatusEnum;
import com.epsilon.module.system.mapper.SystemMenuMapper;
import com.epsilon.module.system.module.entity.SystemMenu;
import com.epsilon.module.system.module.vo.MenuVo;
import com.epsilon.module.system.module.vo.MetaVo;
import com.epsilon.module.system.module.vo.RouterVo;
import com.epsilon.module.system.module.vo.UserInfoVo;
import com.epsilon.module.system.service.SystemMenuService;
import com.epsilon.module.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author eitan
 * @since 2023-11-30
 */
@Service
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements SystemMenuService {
    private SystemMenuMapper systemMenuMapper;

    @Autowired
    public SystemMenuServiceImpl(SystemMenuMapper systemMenuMapper) {
        this.systemMenuMapper = systemMenuMapper;
    }

    @Override
    public List<MenuVo> selectMenuList(Integer userId) {
        List<SystemMenu> menuList;

        if (UserUtil.isAdmin(userId)) {
            menuList = new LambdaQueryChainWrapper<>(systemMenuMapper)
                    .eq(SystemMenu::getDeleted, false)
                    .list();
        } else {
            menuList = systemMenuMapper.selectMenuListByUserId(userId);
        }

        // 删选出状态为关闭的菜单
        menuList.removeIf(i -> i.getStatus().equals(CommonStatusEnum.DISABLE.getStatus()));
        return buildMenuTree(menuList);
    }

    @Override
    public List<RouterVo> buildRouter(List<MenuVo> menuList) {
        LinkedList<RouterVo> routers = new LinkedList<>();
        for (MenuVo menu : menuList) {
            RouterVo router = new RouterVo();
            router.setName(StringUtils.defaultString(menu.getComponentName()));
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon(), menu.getType(), menu.getSort()));

            List<MenuVo> menuChildren = menu.getChildren();
            if (!CollectionUtils.isEmpty(menuChildren)) {
                router.setChildren(buildRouter(menuChildren));
            }
            routers.add(router);
        }
        return routers;
    }

    private List<MenuVo> buildMenuTree(List<SystemMenu> menuList) {
        List<MenuVo> menuVoList = menuList.stream().map(MenuConvert.INSTANCE::convert).toList();
        Map<Integer, MenuVo> menuVoMap = menuVoList.stream().collect(Collectors.toMap(MenuVo::getId, i -> i));

        List<MenuVo> topLevelMenus = new ArrayList<>();
        for (MenuVo menuVo : menuVoList) {
            Integer parentId = menuVo.getParentId();
            if (parentId == null || parentId == 0) {
                topLevelMenus.add(menuVo);
            } else {
                MenuVo parentMenu = menuVoMap.get(parentId);
                addMenuChildren(parentMenu, menuVo);
            }
        }

        return topLevelMenus;
    }

    private void addMenuChildren(MenuVo parent, MenuVo children) {
        if (parent == null) {
            return;
        }
        List<MenuVo> childrenList = parent.getChildren();
        if (childrenList != null) {
            childrenList.add(children);
        } else {
            childrenList = new ArrayList<>();
            childrenList.add(children);
            parent.setChildren(childrenList);
        }
    }
}
