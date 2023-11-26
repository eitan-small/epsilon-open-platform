package com.epsilon.module.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.epsilon.module.system.module.entity.SystemRole;

import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author eitan
 * @since 2023-11-19
 */
public interface SystemRoleService extends IService<SystemRole> {

    Set<String> getRolesByUserId(Integer userId);
}
