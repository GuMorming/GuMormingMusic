package cn.edu.whut.gumorming.handler.security;

import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.utils.WebUtils;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.handler.security
 * @createTime : 2024/2/19 8:59
 * @Email : gumorming@163.com
 * @Description : 认证过程中出现的异常处理
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ResponseResult result = null;
        //BadCredentialsException
        if (authException instanceof BadCredentialsException) {
            result = ResponseResult.error(HttpCodeEnum.LOGIN_ERROR.getCode(), authException.getMessage());
            //InsufficientAuthenticationException
        } else if (authException instanceof InsufficientAuthenticationException) {
            result = ResponseResult.error(HttpCodeEnum.INSUFFICIENT_AUTHENTICATION);
        } else {
            result = ResponseResult.error(HttpCodeEnum.SYSTEM_ERROR.getCode(), "认证或授权失败");
        }
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}