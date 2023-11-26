package com.epsilon.gateway.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "epsilon.gateway")
public class AuthenticationProperties {

    /**
     * 白名单，网关不对白名单进行鉴权
     */
    private List<String> whitelist = new ArrayList<>();

    /**
     * 系统路径，使用系统鉴权策略
     */
    private List<String> systemPaths = new ArrayList<>();


    public List<String> getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(List<String> whitelist) {
        this.whitelist = whitelist;
    }

    public List<String> getSystemPaths() {
        return systemPaths;
    }

    public void setSystemPaths(List<String> systemPaths) {
        this.systemPaths = systemPaths;
    }
}
