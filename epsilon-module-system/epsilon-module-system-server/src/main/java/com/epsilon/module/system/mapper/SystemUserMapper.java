package com.epsilon.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.epsilon.module.system.module.entity.SystemUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author eitan
 * @since 2023-11-19
 */
@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {

}
