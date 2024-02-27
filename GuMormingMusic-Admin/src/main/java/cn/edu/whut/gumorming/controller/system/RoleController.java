package cn.edu.whut.gumorming.controller.system;

import cn.edu.whut.gumorming.annotation.OpsLogger;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.RoleQuery;
import cn.edu.whut.gumorming.model.request.RoleRequest;
import cn.edu.whut.gumorming.model.request.RoleStatusRequest;
import cn.edu.whut.gumorming.model.response.RoleResponse;
import cn.edu.whut.gumorming.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.edu.whut.gumorming.constants.OpsTypeConstants.*;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2024/2/19 19:48
 * @Email : gumorming@163.com
 * @Description :
 */
@Tag(name = "角色模块")
@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查看后台角色列表
     *
     * @param roleQuery 角色查询条件
     * @return {@link RoleResponse} 角色列表
     */
    @Operation(summary = "查看后台角色列表")
    @PreAuthorize("hasAuthority('system:role:list')")
    @GetMapping("/list")
    public ResponseResult<PageResult<RoleResponse>> listRoleVO(RoleQuery roleQuery) {
        return ResponseResult.ok(roleService.listRoleVO(roleQuery));
    }

    /**
     * 添加角色
     *
     * @param role 角色信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(ADD)
    @Operation(summary = "添加角色")
    @PreAuthorize("hasAuthority('system:role:add')")
    @PostMapping("/add")
    public ResponseResult<?> addRole(@Validated @RequestBody RoleRequest role) {
        return roleService.addRole(role);
    }

    /**
     * 删除角色
     *
     * @param roleIdList 角色id集合
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(DELETE)
    @Operation(summary = "删除角色")
    @PreAuthorize("hasAuthority('system:role:delete')")
    @DeleteMapping("/delete")
    public ResponseResult<?> deleteRole(@RequestBody List<Integer> roleIdList) {
        return roleService.deleteRole(roleIdList);
    }

    /**
     * 修改角色
     *
     * @param role 角色信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = UPDATE)
    @Operation(summary = "修改角色")
    @PreAuthorize("hasAuthority('system:role:update')")
    @PutMapping("/update")
    public ResponseResult<?> updateRole(@Validated @RequestBody RoleRequest role) {
        return roleService.updateRole(role);

    }

    /**
     * 修改角色状态
     *
     * @param roleStatus 角色状态
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(UPDATE)
    @Operation(summary = "修改角色状态")
    @PreAuthorize("hasAnyAuthority('system:role:update','system:role:status')")
    @PutMapping("/changeStatus")
    public ResponseResult<?> updateRoleStatus(@Validated @RequestBody RoleStatusRequest roleStatus) {
        return roleService.updateRoleStatus(roleStatus);

    }

    /**
     * 查看角色的菜单权限
     *
     * @param roleId 角色id
     * @return {@link List<Integer>} 角色的菜单权限
     */
    @Operation(summary = "查看角色的菜单权限")
    @PreAuthorize("hasAuthority('system:role:list')")
    @GetMapping("/menu/{roleId}")
    public ResponseResult<List<Integer>> listRoleMenuTree(@PathVariable("roleId") Integer roleId) {
        return ResponseResult.ok(roleService.listRoleMenuTree(roleId));
    }
}