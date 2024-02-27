package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2024/2/18 15:50
 * @Email : gumorming@163.com
 * @Description :
 */
@Tag(name = "登录模块")

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;


    @Operation(summary = "登录", description = "根据用户名和密码登录")
    @PostMapping("/login")
    public ResponseResult<String> login(@Validated @RequestBody User user) {
        return loginService.login(user);
    }

    @Operation(summary = "登出", description = "退出登录状态")
    @GetMapping("/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }


    /**
     * 上传访客信息
     *
     * @return {@link ResponseResult<>}
     */

    @Operation(summary = "上传访客信息")
    @PostMapping("/report")
    public ResponseResult<?> report() {
        return loginService.report();
    }


}