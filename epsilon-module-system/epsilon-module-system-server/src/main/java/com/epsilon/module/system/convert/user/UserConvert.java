package com.epsilon.module.system.convert.user;

import com.epsilon.module.system.module.entity.SystemUser;
import com.epsilon.module.system.module.vo.UserInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserInfoVo convert(SystemUser systemUser);
}
