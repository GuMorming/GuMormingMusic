package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.User;
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
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-18 12:11:04
 */
public interface UserService extends IService<User> {

    UserInfoResponse getUserBackInfo();

    List<RouterResponse> getUserMenu();

    PageResult<UserBackResponse> listUserBackVO(UserQuery userQuery);

    List<UserRoleResponse> listUserRoleDTO();

    ResponseResult<?> updateUser(UserRoleRequset user);

    ResponseResult<?> updateUserStatus(DisableRequest disable);

    PageResult<OnlineUserResponse> listOnlineUser(OnlineUserQuery onlineUserQuery);

    ResponseResult<?> updateAdminPassword(PasswordRequest password);

    ResponseResult<?> kickOutUser(String token) throws Exception;
}