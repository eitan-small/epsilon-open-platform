package com.epsilon.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.epsilon.module.system.module.entity.SystemMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author eitan
 * @since 2023-11-30
 */
@Mapper
public interface SystemMenuMapper extends BaseMapper<SystemMenu> {

    List<SystemMenu> selectMenuListByUserId(@Param("userId") Integer userId);
}
