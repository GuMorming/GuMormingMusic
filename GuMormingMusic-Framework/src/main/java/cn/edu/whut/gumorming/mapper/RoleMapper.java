package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.Role;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表(Role)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-19 16:11:24
 */
@Mapper
public interface RoleMapper extends OptionalBaseMapper<Role> {

}