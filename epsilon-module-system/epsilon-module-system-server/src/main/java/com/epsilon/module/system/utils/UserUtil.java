package com.epsilon.module.system.utils;

public class UserUtil {
    public static Boolean isAdmin(Integer userId) {
        return userId != null && 1 == userId;
    }
}
