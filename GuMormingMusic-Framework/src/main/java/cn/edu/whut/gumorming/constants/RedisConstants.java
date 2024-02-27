package cn.edu.whut.gumorming.constants;

public class RedisConstants {
    /**
     * 登录 KEY 前缀
     */
    public static final String ADMIN_LOGIN_PREFIX = "AdminLogin:";
    public static final String CLIENT_LOGIN_PREFIX = "ClientLogin:";
    /**
     * token前缀
     */
    public static final String TOKEN_PREFIX = "token:";
    public static final Integer TOKEN_EXPIRE_TIME = 12;
    /**
     * 验证码 key
     */
    public static final String CODE_KEY = "code:";

    /**
     * 验证码过期时间
     */
    public static final Integer CODE_EXPIRE_TIME = 5;

    /**
     * 网站配置
     */
    public static final String SITE_SETTING = "site_setting";

    /**
     * 访客
     */
    public static final String UNIQUE_VISITOR = "unique_visitor";


    public static String CLIENT_VIEW_COUNT = "client_view_count";
}