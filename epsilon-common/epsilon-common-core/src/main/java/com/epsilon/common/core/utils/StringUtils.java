package com.epsilon.common.core.utils;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * The mapping matches URLs using the following rules:
     * ? matches one character
     * * matches zero or more characters
     * ** matches zero or more directories in a path
     * {spring:[a-z]+} matches the regexp [a-z]+ as a path variable named "spring"
     */
    private static final AntPathMatcher matcher = new AntPathMatcher();

    /**
     * 查找指定字符串是否匹配指定字符串列表中的任意一个字符串
     *
     * @param url       指定字符串
     * @param whitelist 需要检查的字符串数组
     * @return 是否匹配
     */
    public static boolean matches(String url, List<String> whitelist) {
        if (isEmpty(url) || CollectionUtils.isEmpty(whitelist)) {
            return false;
        }
        for (String pattern : whitelist) {
            if (matcher.match(pattern, url)) {
                return true;
            }
        }
        return false;
    }
}
