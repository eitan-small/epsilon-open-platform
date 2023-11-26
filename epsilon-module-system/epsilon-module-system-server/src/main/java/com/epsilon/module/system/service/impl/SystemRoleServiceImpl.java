package com.epsilon.module.system.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.epsilon.module.system.mapper.SystemRoleMapper;
import com.epsilon.module.system.module.entity.SystemRole;
import com.epsilon.module.system.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author eitan
 * @since 2023-11-19
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements SystemRoleService {
    private SystemRoleMapper systemRoleMapper;

    @Autowired
    public SystemRoleServiceImpl(SystemRoleMapper systemRoleMapper) {
        this.systemRoleMapper = systemRoleMapper;
    }

    @Override
    public Set<String> getRolesByUserId(Integer userId) {
        return systemRoleMapper.getRolesByUserId(userId);
    }
}
