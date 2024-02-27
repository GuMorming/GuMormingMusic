package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.UserRole;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (UserRole)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-19 16:12:50
 */
@Mapper
public interface UserRoleMapper extends OptionalBaseMapper<UserRole> {
    /**
     * 添加用户角色
     *
     * @param userId     用户id
     * @param roleIdList 角色id集合
     */
    void insertUserRole(@Param("userId") Integer userId, @Param("roleIdList") List<String> roleIdList);

    List<Integer> selectRoleIdsByUserId(@Param("userId") Integer userId);
}