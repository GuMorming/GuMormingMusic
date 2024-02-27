package cn.edu.whut.gumorming.filter;

import cn.edu.whut.gumorming.constants.CommonConstant;
import cn.edu.whut.gumorming.constants.RedisConstants;
import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.model.LoginUser;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.utils.JwtUtils;
import cn.edu.whut.gumorming.utils.UserAgentUtils;
import cn.edu.whut.gumorming.utils.WebUtils;
import cn.edu.whut.gumorming.utils.redis.RedisCache;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.filter
 * @createTime : 2024/2/18 18:09
 * @Email : gumorming@163.com
 * @Description :
 * 这个过滤器会去获取请求头中的token，对token进行解析取出其中的userid。
 * 使用userid去redis中获取对应的LoginUser对象。
 * 然后封装Authentication对象存入SecurityContextHolder,以使后续filter链获取认证过的用户
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    public static final String TOKEN = "token";

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的Token
        String token = request.getHeader(TOKEN);
        if (!StringUtils.hasText(token)) {
            // 说明该接口不需要登录, 直接放行
            filterChain.doFilter(request, response);
            // 过滤完回来时,return令其不访问下面的代码
            return;
        }
        // 解析获取UserId
        Claims claims = null;
        try {
            claims = JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            // token 超时或非法
            // 响应告诉前端需要重新登录
            ResponseResult result = ResponseResult.error(HttpCodeEnum.INSUFFICIENT_AUTHENTICATION);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        String userId = claims.getSubject();
        // 从Redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject(RedisConstants.ADMIN_LOGIN_PREFIX + userId);
        // 如果获取不到
        if (Objects.isNull(loginUser)) {
            // 登录过期，提示重新登录
            ResponseResult result = ResponseResult.error(HttpCodeEnum.INSUFFICIENT_AUTHENTICATION);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        // 若账户被禁用
        if (loginUser.getUser().getIsDisable().equals(CommonConstant.TRUE)) {
            // 登录过期，提示重新登录
            ResponseResult result = ResponseResult.error(HttpCodeEnum.USER_IS_DISABLE);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        // 解析browser和os
        Map<String, String> userAgentMap = UserAgentUtils.parseOsAndBrowser(request.getHeader("User-Agent"));
        loginUser.setOs(userAgentMap.get("os"));
        loginUser.setBrowser(userAgentMap.get("browser"));
        // 存入SecurityContextHolder:后续的filter链均从其中获取认证过的用户信息
        UsernamePasswordAuthenticationToken authenticationToken =
                // 三个参数的构造器会设置为已认证状态 setAuthenticated(true)
                // 获取权限信息(authorities)封装到Authentication中
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行给后续filter
        filterChain.doFilter(request, response);
    }
}