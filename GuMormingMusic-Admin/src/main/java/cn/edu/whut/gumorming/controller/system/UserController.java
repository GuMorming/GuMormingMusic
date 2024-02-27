package cn.edu.whut.gumorming.controller.system;

import cn.edu.whut.gumorming.annotation.OpsLogger;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.OnlineUserQuery;
import cn.edu.whut.gumorming.model.request.DisableRequest;
import cn.edu.whut.gumorming.model.request.PasswordRequest;
import cn.edu.whut.gumorming.model.request.UserRoleRequset;
import cn.edu.whut.gumorming.model.response.OnlineUserResponse;
import cn.edu.whut.gumorming.model.response.RouterResponse;
import cn.edu.whut.gumorming.model.response.UserRoleResponse;
import cn.edu.whut.gumorming.model.user.UserBackResponse;
import cn.edu.whut.gumorming.model.user.UserInfoResponse;
import cn.edu.whut.gumorming.model.user.UserQuery;
import cn.edu.whut.gumorming.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.edu.whut.gumorming.constants.OpsTypeConstants.KICK;
import static cn.edu.whut.gumorming.constants.OpsTypeConstants.UPDATE;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2024/2/19 14:55
 * @Email : gumorming@163.com
 * @Description :
 */
@Tag(name = "用户模块")
@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取后台登录用户信息
     *
     * @return {@link UserInfoResponse} 登录用户信息
     */
    @Operation(summary = "获取后台登录用户信息")
    @GetMapping("/user/getUserInfo")
    public ResponseResult<UserInfoResponse> getUserBackInfo() {
        return ResponseResult.ok(userService.getUserBackInfo());
    }

    /**
     * 获取登录用户菜单
     *
     * @return {@link RouterResponse} 登录用户菜单
     */
    @Operation(summary = "获取登录用户菜单")
    @GetMapping("/user/getUserMenu")
    public ResponseResult<List<RouterResponse>> getUserMenu() {
        return ResponseResult.ok(userService.getUserMenu());
    }

    /**
     * 查看后台用户列表
     *
     * @param userQuery 用户查询条件
     * @return {@link UserBackResponse} 用户后台列表
     */
    @Operation(summary = "查看后台用户列表")
    @PreAuthorize("hasAuthority('system:user:list')")
    @GetMapping("/user/list")
    public ResponseResult<PageResult<UserBackResponse>> listUserBackVO(UserQuery userQuery) {
        return ResponseResult.ok(userService.listUserBackVO(userQuery));
    }

    /**
     * 查看用户角色选项
     *
     * @return {@link UserRoleResponse} 用户角色选项
     */
    @Operation(summary = "查看用户角色选项")
    @PreAuthorize("hasAuthority('system:user:list')")
    @GetMapping("/user/role")
    public ResponseResult<List<UserRoleResponse>> listUserRoleDTO() {
        return ResponseResult.ok(userService.listUserRoleDTO());
    }

    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(UPDATE)
    @Operation(summary = "修改用户")
    @PreAuthorize("hasAuthority('system:user:update')")
    @PutMapping("/user/update")
    public ResponseResult<?> updateUser(@Validated @RequestBody UserRoleRequset user) {
        return userService.updateUser(user);

    }

    /**
     * 修改用户状态
     *
     * @param disable 禁用信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(UPDATE)
    @Operation(summary = "修改用户状态")
    @PreAuthorize("hasAuthority('system:user:status')")
    @PutMapping("/user/changeStatus")
    public ResponseResult<?> updateUserStatus(@Validated @RequestBody DisableRequest disable) {
        return userService.updateUserStatus(disable);
    }

    /**
     * 查看在线用户
     *
     * @param onlineUserQuery 查询条件
     * @return {@link OnlineUserResponse} 在线用户列表
     */
    @Operation(summary = "查看在线用户")
    @PreAuthorize("hasAuthority('monitor:online:list')")
    @GetMapping("/online/list")
    public ResponseResult<PageResult<OnlineUserResponse>> listOnlineUser(OnlineUserQuery onlineUserQuery) {
        return ResponseResult.ok(userService.listOnlineUser(onlineUserQuery));
    }

    /**
     * 下线用户
     *
     * @param token 在线token
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(KICK)
    @Operation(summary = "下线用户")
    @PreAuthorize("hasAuthority('monitor:online:kick')")
    @GetMapping("/online/kick/{token}")
    public ResponseResult<?> kickOutUser(@PathVariable("token") String token) throws Exception {
        return userService.kickOutUser(token);

    }


    /**
     * 修改管理员密码
     *
     * @param password 密码
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(UPDATE)
    @Operation(summary = "修改管理员密码")
    @PutMapping("/password")
    public ResponseResult<?> updateAdminPassword(@Validated @RequestBody PasswordRequest password) {
        return userService.updateAdminPassword(password);

    }

}