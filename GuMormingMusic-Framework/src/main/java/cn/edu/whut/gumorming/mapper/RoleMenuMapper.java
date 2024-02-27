package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.RoleMenu;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限表(RoleMenu)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-19 16:13:07
 */
@Mapper
public interface RoleMenuMapper extends OptionalBaseMapper<RoleMenu> {

    void insertRoleMenu(@Param("roleId") Integer id, @Param("menuIdList") List<Integer> menuIdList);

    void deleteRoleMenu(@Param("roleIdList") List<Integer> roleIdList);

    List<Integer> selectMenuByRoleId(@Param("roleId") Integer roleId);


}