package cn.edu.whut.gumorming.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (UserRole)表实体类
 *
 * @author GuMorming
 * @since 2024-02-19 16:12:50
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_role")
public class UserRole {
    /**
     * 用户id
     */

    private Integer userId;
    /**
     * 角色id
     */

    private Integer roleId;


}