package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.Menu;
import cn.edu.whut.gumorming.model.response.MenuOptionResponse;
import cn.edu.whut.gumorming.model.response.MenuTreeResponse;
import cn.edu.whut.gumorming.model.response.UserMenuResponse;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Menu)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-18 22:49:53
 */
@Mapper
public interface MenuMapper extends OptionalBaseMapper<Menu> {
    /**
     * @param userId
     * @return 对应用户权限
     */
    List<String> selectPermsByUserId(Integer userId);


    /**
     * 根据用户id查询用户菜单列表
     *
     * @param userId 用户id
     * @return 用户菜单列表
     */
    List<UserMenuResponse> selectMenuByUserId(@Param("userId") Integer userId);

    /**
     * 查询菜单下拉树
     *
     * @return 菜单下拉树
     */
    List<MenuTreeResponse> selectMenuTree();

    /**
     * 查询菜单选项树
     *
     * @return 菜单选项树
     */
    List<MenuOptionResponse> selectMenuOptions();


}