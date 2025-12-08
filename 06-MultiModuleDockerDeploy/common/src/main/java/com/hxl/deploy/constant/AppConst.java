package com.hxl.deploy.constant;

/**
 * 应用常量
 *
 * @author hengxiaoliang
 */
public final class AppConst {

    private AppConst() {
    }

    // ==========================数据库表相关缓存常量======================================
    /**
     * 系统用户缓存前缀
     */
    public static final String SYSTEM_USER_PREFIX = "SYSTEM_USER";


    // ==========================符号相关常量======================================

    public static final String UNDERLINE = "_";
    public static final String DOT = ".";
    public static final String COMMA = ",";
    public static final String SEMICOLON = ";";
    public static final String COLON = ":";
    public static final String DOUBLE_COLON = "::";
    public static final String AT = "@";
    public static final String ASTERISK = "*";
    public static final String HASH = "#";
    public static final String SPACE = " ";

    // ==========================异常相关常量======================================
    public static final String CACHE_NAME_NOT_NULL = "缓存名不能为空";
    public static final String CACHE_KEY_NOT_NULL = "缓存键不能为空";
    public static final String CACHE_VALUE_NOT_NULL = "缓存值不能为空";
    public static final String CACHE_EXPIRE_NOT_NULL = "缓存过期时间不能为空";
    public static final String USER_NOT_EXISTS = "用户不存在";
}
