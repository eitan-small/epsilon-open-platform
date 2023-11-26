package com.epsilon.module.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.epsilon.module.system.module.entity.SystemUser;
import com.epsilon.module.system.module.vo.LoginReqVO;
import com.epsilon.module.system.module.vo.LoginRespVO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 用户信息表 服务类
 *
 * @author eitan
 * @since 2023-11-19
 */
public interface SystemUserService extends IService<SystemUser> {

    LoginRespVO login(LoginReqVO loginReqVO);

    SystemUser getUserByUsername(String username);
}
