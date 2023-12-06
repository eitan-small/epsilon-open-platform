package com.epsilon.module.system.convert.menu;

import com.epsilon.module.system.module.entity.SystemMenu;
import com.epsilon.module.system.module.vo.MenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MenuConvert {
    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    MenuVo convert(SystemMenu systemMenu);
}
