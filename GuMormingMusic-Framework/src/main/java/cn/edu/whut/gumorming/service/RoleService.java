package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.Role;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.RoleQuery;
import cn.edu.whut.gumorming.model.request.RoleRequest;
import cn.edu.whut.gumorming.model.request.RoleStatusRequest;
import cn.edu.whut.gumorming.model.response.RoleResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色表(Role)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-19 16:11:24
 */
public interface RoleService extends IService<Role> {

    PageResult<RoleResponse> listRoleVO(RoleQuery roleQuery);

    ResponseResult<?> addRole(RoleRequest role);

    ResponseResult<?> deleteRole(List<Integer> roleIdList);

    ResponseResult<?> updateRole(RoleRequest role);

    ResponseResult<?> updateRoleStatus(RoleStatusRequest roleStatus);

    List<Integer> listRoleMenuTree(Integer roleId);
}