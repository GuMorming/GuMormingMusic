package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.CommonConstant;
import cn.edu.whut.gumorming.entity.Role;
import cn.edu.whut.gumorming.entity.RoleMenu;
import cn.edu.whut.gumorming.entity.UserRole;
import cn.edu.whut.gumorming.mapper.MenuMapper;
import cn.edu.whut.gumorming.mapper.RoleMapper;
import cn.edu.whut.gumorming.mapper.RoleMenuMapper;
import cn.edu.whut.gumorming.mapper.UserRoleMapper;
import cn.edu.whut.gumorming.model.LoginUser;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.RoleQuery;
import cn.edu.whut.gumorming.model.request.RoleRequest;
import cn.edu.whut.gumorming.model.request.RoleStatusRequest;
import cn.edu.whut.gumorming.model.response.RoleResponse;
import cn.edu.whut.gumorming.service.RoleService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.edu.whut.gumorming.utils.SecurityUtils;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 角色表(Role)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-19 16:11:24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public PageResult<RoleResponse> listRoleVO(RoleQuery roleQuery) {
        // 构造条件
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>()
                .eq(Objects.nonNull(roleQuery.getIsDisable()), Role::getIsDisable, roleQuery.getIsDisable())
                .like(StringUtils.hasText(roleQuery.getKeyword()), Role::getRoleName, roleQuery.getKeyword());
        // 分页
        Page<Role> page = new Page<>(roleQuery.getCurrent(), roleQuery.getSize());
        page(page, queryWrapper);
        // 封装VO
        List<RoleResponse> roleResponses = BeanCopyUtils.copyBeanList(page.getRecords(), RoleResponse.class);

        return new PageResult<>(roleResponses, page.getTotal());
    }

    @Override
    public ResponseResult<?> addRole(RoleRequest role) {
        // 角色名是否存在
        Role existRole = baseMapper.selectOne(new LambdaQueryWrapper<Role>()
                .select(Role::getId)
                .eq(Role::getRoleName, role.getRoleName()));
        Assert.isNull(existRole, role.getRoleName() + "角色名已存在");
        // 添加新角色
        Role newRole = Role.builder().roleName(role.getRoleName()).roleDesc(role.getRoleDesc()).isDisable(role.getIsDisable()).build();
        baseMapper.insert(newRole);
        // 添加角色菜单权限
        roleMenuMapper.insertRoleMenu(newRole.getId(), role.getMenuIdList());

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> deleteRole(List<Integer> roleIdList) {
        Assert.isFalse(roleIdList.contains(CommonConstant.ADMIN), "不允许删除管理员角色");
        // 角色是否已分配
        Long count = userRoleMapper.selectCount(new LambdaQueryWrapper<UserRole>().in(UserRole::getRoleId, roleIdList));
        Assert.isFalse(count > 0, "角色已分配");
        // 删除角色
        baseMapper.deleteBatchIds(roleIdList);
        // 批量删除角色关联的菜单权限
        roleMenuMapper.deleteRoleMenu(roleIdList);

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> updateRole(RoleRequest role) {
        Assert.isFalse(role.getId().equals(CommonConstant.ADMIN) && role.getIsDisable().equals(CommonConstant.TRUE),
                "不允许禁用管理员角色");
        // 角色名是否存在
        Role existRole = baseMapper.selectOne(new LambdaQueryWrapper<Role>().select(Role::getId).eq(Role::getRoleName, role.getRoleName()));
        Assert.isFalse(Objects.nonNull(existRole) && !existRole.getId().equals(role.getId()),
                role.getRoleName() + "角色名已存在");
        // 更新角色信息
        Role newRole = Role.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .roleDesc(role.getRoleDesc()).
                isDisable(role.getIsDisable())
                .build();
        baseMapper.updateById(newRole);
        // 先删除角色关联的菜单权限
        roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, newRole.getId()));
        // 再添加角色菜单权限
        roleMenuMapper.insertRoleMenu(newRole.getId(), role.getMenuIdList());
        // 若当前用户角色为被更改角色,更新SecurityContextHolder中缓存的权限
        List<Integer> roleIds = userRoleMapper.selectRoleIdsByUserId(SecurityUtils.getUserId());
        if (roleIds.contains(role.getId())) {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            System.out.println("1:" + loginUser.getAuthorities());
            // 数据库中查询对应权限信息
            List<String> permissions = menuMapper.selectPermsByUserId(loginUser.getUser().getId());
            loginUser.setPermissions(permissions);
            System.out.println("2:" + loginUser.getAuthorities());
            // 必须构造新的AuthenticationToken，因为Spring Security没有给出setAuthorities的方法
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(loginUser,
                    null,
                    loginUser.getAuthorities()));
        }
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> updateRoleStatus(RoleStatusRequest roleStatus) {
        Assert.isFalse(roleStatus.getId().equals(CommonConstant.ADMIN), "不允许禁用管理员角色");
        // 更新角色状态
        Role newRole = Role.builder()
                .id(roleStatus.getId())
                .isDisable(roleStatus.getIsDisable())
                .build();
        baseMapper.updateById(newRole);
        return ResponseResult.ok();
    }

    @Override
    public List<Integer> listRoleMenuTree(Integer roleId) {
        return roleMenuMapper.selectMenuByRoleId(roleId);
    }
}