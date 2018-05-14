package com.tuyongkang.blog;

/**
 * APP 常量
 */
public class AppConstants {

    /**
     * 包扫描常量
     */
    public static final String BASE_SCAN = "com.tuyongkang.blog";

    public static final String CONTROLLER_SCAN = BASE_SCAN + ".mvc.controller";

    public static final String SERVICE_SCAN = BASE_SCAN + ".service.impl";

    public static final String REPOSITORY_SCAN = BASE_SCAN + ".dao.repository";

    public static final String DAO_SCAN = BASE_SCAN + ".dao.impl";

    public static final String ENTITY_SCAN = BASE_SCAN + ".entity";

    public static final String EXT_CONFIG_SCAN = BASE_SCAN + ".config.ext";

    /**
     * 用户session的key
     */
    public static final String USER_SESSION_KEY = "userSessionKey";

}
