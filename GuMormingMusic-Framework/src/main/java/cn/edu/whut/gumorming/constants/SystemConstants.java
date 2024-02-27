package cn.edu.whut.gumorming.constants;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.constants
 * @createTime : 2024/2/18 17:49
 * @Email : gumorming@163.com
 * @Description :
 */

public class SystemConstants {
    /**
     * JWT有效时间 60*60*1000L==1小时
     */
    public final static Long JWT_TTL = 12 * 60 * 60 * 1000L;
    /**
     * JWT密钥 TODO 不能为9字节
     */
    public final static String JWT_KEY = "gumorming1";
}