package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.model.user.UserBackResponse;
import cn.edu.whut.gumorming.model.user.UserQuery;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-18 12:11:04
 */
@Mapper
public interface UserMapper extends OptionalBaseMapper<User> {
    /**
     * 查询后台用户列表
     *
     * @param userQuery 用户查询条件
     * @return 用户后台列表
     */
    List<UserBackResponse> selectUserList(@Param("userQuery") UserQuery userQuery);
}