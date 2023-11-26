package com.epsilon.module.system.controller;

import com.epsilon.common.core.context.SecurityContextHolder;
import com.epsilon.common.web.domain.CommonResult;
import com.epsilon.module.system.convert.user.UserConvert;
import com.epsilon.module.system.module.entity.SystemUser;
import com.epsilon.module.system.module.vo.LoginReqVO;
import com.epsilon.module.system.module.vo.LoginRespVO;
import com.epsilon.module.system.module.vo.UserInfoVo;
import com.epsilon.module.system.service.SystemRoleService;
import com.epsilon.module.system.service.SystemUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 用户信息表 前端控制器
 *
 * @author eitan
 * @since 2023-11-19
 */
@RestController
@RequestMapping("/system/user")
public class SystemUserController {
    private SystemUserService systemUserService;

    private SystemRoleService systemRoleService;

    @Autowired
    public SystemUserController(SystemUserService systemUserService, SystemRoleService systemRoleService) {
        this.systemUserService = systemUserService;
        this.systemRoleService = systemRoleService;
    }

    @PostMapping("/login")
    public CommonResult<LoginRespVO> login(@RequestBody @Valid LoginReqVO reqVO) {
        LoginRespVO authLoginRespVO = systemUserService.login(reqVO);
        return CommonResult.success(authLoginRespVO);
    }

    @PostMapping("/info")
    public CommonResult info() {
        SystemUser systemUser = systemUserService.getUserByUsername(SecurityContextHolder.getUserName());

        UserInfoVo userInfoVo = UserConvert.INSTANCE.convert(systemUser);
        // 角色合计
        Set<String> roles = systemRoleService.getRolesByUserId(systemUser.getId());
        userInfoVo.setRoles(roles);
        return CommonResult.success(userInfoVo);
    }
}
