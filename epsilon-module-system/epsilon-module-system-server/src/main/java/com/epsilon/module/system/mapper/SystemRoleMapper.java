package com.epsilon.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.epsilon.module.system.module.entity.SystemRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author eitan
 * @since 2023-11-19
 */
@Mapper
public interface SystemRoleMapper extends BaseMapper<SystemRole> {

    /**
     * 通过 userId 获取到其对应的角色集合
     *
     * @param userId
     * @return
     */
    Set<String> getRolesByUserId(@Param("userId") Integer userId);
}
