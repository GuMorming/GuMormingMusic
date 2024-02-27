package cn.edu.whut.gumorming.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 角色表(Role)表实体类
 *
 * @author GuMorming
 * @since 2024-02-19 16:11:24
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("role")
public class Role {
    /**
     * 主键id
     */
    @TableId
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDesc;
    /**
     * 是否禁用 (0否 1是)
     */
    private Integer isDisable;
    /**
     * 创建人ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer createBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新人ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 逻辑删除(0否 1是)
     */
    private Integer isDelete;


}