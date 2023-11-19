package com.epsilon.common.web.domain;

public class BaseController {
    /**
     * 返回成功
     */
    public CommonResult success() {
        return CommonResult.success();
    }

    /**
     * 返回成功消息
     */
    public CommonResult success(String message) {
        return CommonResult.success(message);
    }

    /**
     * 返回成功消息
     */
    public CommonResult success(Object data) {
        return CommonResult.success(data);
    }

    /**
     * 返回失败消息
     */
    public CommonResult error() {
        return CommonResult.error();
    }

    /**
     * 返回失败消息
     */
    public CommonResult error(String message) {
        return CommonResult.error(message);
    }
}
