package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.RedisConstants;
import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.model.LoginUser;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.service.LoginService;
import cn.edu.whut.gumorming.service.UserService;
import cn.edu.whut.gumorming.utils.IpUtils;
import cn.edu.whut.gumorming.utils.JwtUtils;
import cn.edu.whut.gumorming.utils.SecurityUtils;
import cn.edu.whut.gumorming.utils.UserAgentUtils;
import cn.edu.whut.gumorming.utils.redis.RedisCache;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.service.impl
 * @createTime : 2024/2/18 16:01
 * @Email : gumorming@163.com
 * @Description : 登录服务实现类
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private HttpServletRequest request;

    @Override
    public ResponseResult<String> login(User user) {
        // AuthenticationManage.authenticate()进行用户认证
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        // 认证未通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        // 认证通过,生成jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        String token = JwtUtils.createJWT(userId.toString());
        loginUser.setToken(token);
        // 解析browser和os
        Map<String, String> userAgentMap = UserAgentUtils.parseOsAndBrowser(request.getHeader("User-Agent"));
        loginUser.setOs(userAgentMap.get("os"));
        loginUser.setBrowser(userAgentMap.get("browser"));
        // 把完整用户信息和对应token存入Redis中
//        redisCache.setCacheObject(RedisConstants.TOKEN_PREFIX + userId, token, RedisConstants.TOKEN_EXPIRE_TIME, TimeUnit.HOURS);
        redisCache.setCacheObject(RedisConstants.ADMIN_LOGIN_PREFIX + userId, loginUser, RedisConstants.TOKEN_EXPIRE_TIME, TimeUnit.HOURS);
        // 获取request登录IP
        String ipAddress = IpUtils.getIpAddress(request);
        String ipSource = IpUtils.getIpSource(ipAddress);
        // 登录时间和IP更新
        user.setLoginTime(LocalDateTime.now());
        userService.update(new LambdaUpdateWrapper<User>()
                .set(User::getLoginTime, new Date())
                .set(User::getIpAddress, ipAddress)
                .set(User::getIpSource, ipSource)
                .eq(User::getId, userId));

        // 返回jwt
        return ResponseResult.ok(token);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的认证信息 userId
        Integer userId = SecurityUtils.getUserId();
        // 删除redis中对应的数据
        redisCache.deleteObject(RedisConstants.ADMIN_LOGIN_PREFIX + userId);

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> report() {
        // 获取用户ip
        String ipAddress = IpUtils.getIpAddress(request);
        Map<String, String> userAgentMap = UserAgentUtils.parseOsAndBrowser(request.getHeader("User-Agent"));
        // 获取访问设备
        String browser = userAgentMap.get("browser");
        String os = userAgentMap.get("os");
        // 生成唯一用户标识
        String uuid = ipAddress + browser + os;
        String md5 = DigestUtils.md5DigestAsHex(uuid.getBytes());
        // 判断是否访问
        if (!redisCache.hasSetValue(RedisConstants.UNIQUE_VISITOR, md5)) {
            // 访问量+1
            redisCache.incr(RedisConstants.CLIENT_VIEW_COUNT, 1);
            // 保存唯一标识
            redisCache.setSet(RedisConstants.UNIQUE_VISITOR, md5);
        }
        return ResponseResult.ok();
    }

}