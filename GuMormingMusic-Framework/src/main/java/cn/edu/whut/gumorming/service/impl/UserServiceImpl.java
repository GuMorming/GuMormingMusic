package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.CommonConstant;
import cn.edu.whut.gumorming.constants.RedisConstants;
import cn.edu.whut.gumorming.entity.Role;
import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.entity.UserRole;
import cn.edu.whut.gumorming.mapper.MenuMapper;
import cn.edu.whut.gumorming.mapper.RoleMapper;
import cn.edu.whut.gumorming.mapper.UserMapper;
import cn.edu.whut.gumorming.mapper.UserRoleMapper;
import cn.edu.whut.gumorming.model.LoginUser;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.OnlineUserQuery;
import cn.edu.whut.gumorming.model.request.DisableRequest;
import cn.edu.whut.gumorming.model.request.PasswordRequest;
import cn.edu.whut.gumorming.model.request.UserRoleRequset;
import cn.edu.whut.gumorming.model.response.*;
import cn.edu.whut.gumorming.model.user.UserBackResponse;
import cn.edu.whut.gumorming.model.user.UserInfoResponse;
import cn.edu.whut.gumorming.model.user.UserQuery;
import cn.edu.whut.gumorming.service.UserService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.edu.whut.gumorming.utils.JwtUtils;
import cn.edu.whut.gumorming.utils.SecurityUtils;
import cn.edu.whut.gumorming.utils.redis.RedisCache;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * (User)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-18 12:11:04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfoResponse getUserBackInfo() {
        // 从SecurityContextHolder中获取出userId
        Integer userId = SecurityUtils.getUserId();
        // 查询用户信息
        User user = baseMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getAvatar)
                .eq(User::getId, userId));
        //  查询用户角色
        LambdaQueryWrapper<UserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<UserRole>()
                .select(UserRole::getRoleId)
                .eq(UserRole::getUserId, userId);
        List<Integer> roleIds = userRoleMapper.selectList(userRoleLambdaQueryWrapper)
                .stream()
                .map(UserRole::getRoleId)
                .toList();
        // 用户权限列表
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        // 构建VO返回
        return UserInfoResponse.builder()
                .id(userId)
                .avatar(user.getAvatar())
                .roleIdList(roleIds)
                .permissionList(permissions)
                .build();
    }

    @Override
    public List<RouterResponse> getUserMenu() {
        // 查询用户菜单
        List<UserMenuResponse> userMenuRespList = menuMapper.selectMenuByUserId(SecurityUtils.getUserId());
        // 递归生成路由,parentId为0
        return recurRoutes(CommonConstant.PARENT_ID, userMenuRespList);
    }

    @Override
    public PageResult<UserBackResponse> listUserBackVO(UserQuery userQuery) {
        // 构建条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                // 昵称
                .like(org.springframework.util.StringUtils.hasText(userQuery.getKeyword()), User::getNickname, userQuery.getKeyword())
                // 登录类型
                .eq(Objects.nonNull(userQuery.getLoginType()), User::getLoginType, userQuery.getLoginType());
        // 分页
        Page<User> page = new Page<>(userQuery.getCurrent(), userQuery.getSize());
        page(page, queryWrapper);
        // 封装VO
        List<UserBackResponse> userBackResponseList = BeanCopyUtils.copyBeanList(page.getRecords(), UserBackResponse.class);
        // 查询用户对应角色
        userBackResponseList.stream()
                .map(userBackResponse -> {
                    // 根据userId获取对应roleIdList
                    List<Integer> roleIds = userRoleMapper.selectList(new LambdaQueryWrapper<UserRole>()
                                    .select(UserRole::getRoleId)
                                    .eq(UserRole::getUserId, userBackResponse.getId()))
                            .stream()
                            .map(UserRole::getRoleId)
                            .toList();
                    // 根据roleId查询对应角色信息,并封装VO
                    List<UserRoleResponse> userRoleResponses = roleIds.stream()
                            .map(roleId -> {
                                Role role = roleMapper.selectOne(new LambdaQueryWrapper<Role>()
                                        .select(Role::getId, Role::getRoleName)
                                        .eq(Role::getId, roleId));
                                return BeanCopyUtils.copyBean(role, UserRoleResponse.class);
                            }).toList();
                    // 设置对应角色信息
                    userBackResponse.setRoleList(userRoleResponses);
                    return null;
                }).toList();

        return new PageResult<>(userBackResponseList, page.getTotal());
    }

    @Override
    public List<UserRoleResponse> listUserRoleDTO() {
        List<Role> roles = roleMapper.selectList(new LambdaQueryWrapper<Role>()
                .select(Role::getId, Role::getRoleName));
        return BeanCopyUtils.copyBeanList(roles, UserRoleResponse.class);
    }

    @Override
    public ResponseResult<?> updateUser(UserRoleRequset user) {
        // 更新用户信息
        User newUser = User.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .build();

        baseMapper.updateById(newUser);
        // 删除用户角色
        userRoleMapper.delete(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId()));
        // 重新添加用户角色
        userRoleMapper.insertUserRole(user.getId(), user.getRoleIdList());
        // todo 删除Redis缓存中的角色
//        SaSession sessionByLoginId = StpUtil.getSessionByLoginId(user.getId(), false);
//        Optional.ofNullable(sessionByLoginId).ifPresent(saSession -> saSession.delete("Role_List"));
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> updateUserStatus(DisableRequest disable) {
        // 更新用户状态
        User newUser = User.builder()
                .id(disable.getId())
                .isDisable(disable.getIsDisable())
                .build();
        baseMapper.updateById(newUser);

//        if (disable.getIsDisable().equals(CommonConstant.TRUE)) {
//            // todo 先踢下线
////            StpUtil.logout(disable.getId());
//            // 再封禁账号
//            StpUtil.disable(disable.getId(), 86400);
//        } else {
//            // 解除封禁
//            StpUtil.untieDisable(disable.getId());
//        }
        return ResponseResult.ok();
    }

    @Override
    public PageResult<OnlineUserResponse> listOnlineUser(OnlineUserQuery onlineUserQuery) {
        // 从Redis中查询所有登录用户 LoginUser
        List<LoginUser> loginUsers = redisCache.getLoginUsersByPrefix(RedisConstants.ADMIN_LOGIN_PREFIX);
        //转换为onlineUser VO
        List<OnlineUserResponse> onlineUserResponses = loginUsers.stream()
                .filter(loginUser -> {
                    if (org.springframework.util.StringUtils.hasText(onlineUserQuery.getKeyword())) {
                        return StringUtils.contains(loginUser.getUser().getNickname(), onlineUserQuery.getKeyword());
                    }
                    return true;
                })
                .map(loginUser -> {
                    OnlineUserResponse onlineUserResponse = BeanCopyUtils.copyBean(loginUser.getUser(), OnlineUserResponse.class);
                    onlineUserResponse.setToken(loginUser.getToken());
                    onlineUserResponse.setBrowser(loginUser.getBrowser());
                    onlineUserResponse.setOs(loginUser.getOs());
                    return onlineUserResponse;
                }).toList();
        // 执行分页
        int fromIndex = onlineUserQuery.getCurrent();
        int size = onlineUserQuery.getSize();
        int toIndex = onlineUserResponses.size() - fromIndex > size ? fromIndex + size : onlineUserResponses.size();
        List<OnlineUserResponse> userOnlineList = onlineUserResponses.subList(fromIndex, toIndex);

        return new PageResult<>(userOnlineList, (long) onlineUserResponses.size());
    }

    @Override
    public ResponseResult<?> updateAdminPassword(PasswordRequest passwordRequest) {
        Integer userId = SecurityUtils.getUserId();
        // 查询旧密码是否正确
        User user = baseMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getId, userId));
        Assert.notNull(user, "用户不存在");
        Assert.isTrue(StringUtils.equals(user.getPassword(), passwordEncoder.encode(passwordRequest.getOldPassword()))
                , "旧密码校验不通过!");
        // 正确则修改密码
        String encodedNewPassword = passwordEncoder.encode(passwordRequest.getNewPassword());
        user.setPassword(encodedNewPassword);
        baseMapper.updateById(user);

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> kickOutUser(String token) throws Exception {
        // 从token中解析出userId
        String userId = JwtUtils.parseJWT(token).getSubject();
        // 将其从Redis中删除
        redisCache.deleteObject(RedisConstants.ADMIN_LOGIN_PREFIX + userId);

        return ResponseResult.ok();
    }

    /**
     * 递归生成路由列表
     *
     * @param parentId 父级ID
     * @param menuList 菜单列表
     * @return 路由列表
     */
    private List<RouterResponse> recurRoutes(Integer parentId, List<UserMenuResponse> menuList) {
        List<RouterResponse> list = new ArrayList<>();
        Optional.ofNullable(menuList).ifPresent(menus -> menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .forEach(menu -> {
                    RouterResponse routeVO = new RouterResponse();
                    routeVO.setName(menu.getMenuName());
                    routeVO.setPath(getRouterPath(menu));
                    routeVO.setComponent(getComponent(menu));
                    routeVO.setMeta(MetaResponse.builder()
                            .title(menu.getMenuName())
                            .icon(menu.getIcon())
                            .hidden(menu.getIsHidden().equals(CommonConstant.TRUE))
                            .build());
                    if (menu.getMenuType().equals(CommonConstant.TYPE_DIR)) {
                        List<RouterResponse> children = recurRoutes(menu.getId(), menuList);
                        if (CollectionUtil.isNotEmpty(children)) {
                            routeVO.setAlwaysShow(true);
                            routeVO.setRedirect("noRedirect");
                        }
                        routeVO.setChildren(children);
                    } else if (isMenuFrame(menu)) {
                        routeVO.setMeta(null);
                        List<RouterResponse> childrenList = new ArrayList<>();
                        RouterResponse children = new RouterResponse();
                        children.setName(menu.getMenuName());
                        children.setPath(menu.getPath());
                        children.setComponent(menu.getComponent());
                        children.setMeta(MetaResponse.builder()
                                .title(menu.getMenuName())
                                .icon(menu.getIcon())
                                .hidden(menu.getIsHidden().equals(CommonConstant.TRUE))
                                .build());
                        childrenList.add(children);
                        routeVO.setChildren(childrenList);
                    }
                    list.add(routeVO);
                }));
        return list;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(UserMenuResponse menu) {
        String component = CommonConstant.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = CommonConstant.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(UserMenuResponse menu) {
        String routerPath = menu.getPath();
        // 一级目录
        if (menu.getParentId().equals(CommonConstant.PARENT_ID) && CommonConstant.TYPE_DIR.equals(menu.getMenuType())) {
            routerPath = "/" + menu.getPath();
        }
        // 一级菜单
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(UserMenuResponse menu) {
        return menu.getParentId().equals(CommonConstant.PARENT_ID) && CommonConstant.TYPE_MENU.equals(menu.getMenuType());
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(UserMenuResponse menu) {
        return !menu.getParentId().equals(CommonConstant.PARENT_ID) && CommonConstant.TYPE_DIR.equals(menu.getMenuType());
    }
}