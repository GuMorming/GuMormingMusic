package cn.edu.whut.gumorming.utils;

import cn.edu.whut.gumorming.model.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.utils
 * @createTime : 2024/2/18 18:07
 * @Email : gumorming@163.com
 * @Description : 从SecurityContextHolder中获取LoginUser
 */

public class SecurityUtils {

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前登录用户
     **/
    public static LoginUser getLoginUser() {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    /**
     * @return 当前登录用户的ID
     */

    public static Integer getUserId() {
        return getLoginUser().getUser().getId();
    }


}