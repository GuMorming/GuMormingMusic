package cn.edu.whut.gumorming.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色权限表(RoleMenu)表实体类
 *
 * @author GuMorming
 * @since 2024-02-19 16:13:07
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("role_menu")
public class RoleMenu {
    /**
     * 角色id
     */

    private Integer roleId;
    /**
     * 菜单id
     */

    private Integer menuId;


}