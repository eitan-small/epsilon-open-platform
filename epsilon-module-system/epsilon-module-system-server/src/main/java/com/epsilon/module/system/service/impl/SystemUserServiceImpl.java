package com.epsilon.module.system.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.epsilon.common.core.constant.SecurityConstants;
import com.epsilon.common.core.constant.TokenConstants;
import com.epsilon.common.core.utils.ip.IpUtils;
import com.epsilon.common.core.utils.jwt.JwtUtils;
import com.epsilon.common.web.exception.ServiceException;
import com.epsilon.module.system.mapper.SystemUserMapper;
import com.epsilon.module.system.module.entity.SystemUser;
import com.epsilon.module.system.module.vo.LoginReqVO;
import com.epsilon.module.system.module.vo.LoginRespVO;
import com.epsilon.module.system.service.SystemUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author eitan
 * @since 2023-11-19
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {
    private SystemUserMapper systemUserMapper;

    @Autowired
    public SystemUserServiceImpl(SystemUserMapper systemUserMapper) {
        this.systemUserMapper = systemUserMapper;
    }

    @Override
    public LoginRespVO login(LoginReqVO loginReqVO) {
        String username = loginReqVO.getUsername();
        // 密码使用 md5 加密
        String password = DigestUtils.md5DigestAsHex(loginReqVO.getPassword().getBytes());

        // 根据用户名查询出用户数据
        SystemUser systemUser = getUserByUsername(username);

        if (Objects.isNull(systemUser) || !password.equals(systemUser.getPassword())) {
            throw new ServiceException("用户名不存在/密码不正确");
        }

        // 保持最后登录ip和最后登录时间
        systemUser.setLoginIp(IpUtils.getIpAddr());
        systemUser.setLoginDate(LocalDateTime.now());
        updateById(systemUser);

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, systemUser.getId());
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, systemUser.getUsername());


        // 返回 authLoginRespVO
        LoginRespVO authLoginRespVO = new LoginRespVO();
        authLoginRespVO.setToken(JwtUtils.createToken(claimsMap, TokenConstants.EXPIRATION_MILLIS));

        return authLoginRespVO;
    }

    @Override
    public SystemUser getUserByUsername(String username) {
        return new LambdaQueryChainWrapper<>(systemUserMapper)
                .eq(SystemUser::getUsername, username)
                .last("LIMIT 1")
                .one();
    }
}
