package cn.edu.whut.gumorming.handler.security;

import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.utils.WebUtils;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.handler.security
 * @createTime : 2024/2/19 9:01
 * @Email : gumorming@163.com
 * @Description : 授权过程中出现的异常处理
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ResponseResult result = ResponseResult.error(HttpCodeEnum.NO_OPERATION_AUTH);
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}