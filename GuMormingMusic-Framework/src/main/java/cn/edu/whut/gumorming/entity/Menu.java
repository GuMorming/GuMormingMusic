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
 * (Menu)表实体类
 *
 * @author GuMorming
 * @since 2024-02-18 22:49:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("menu")
public class Menu {
    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 父菜单id (paren_id为0且type为M则是一级菜单)
     */
    private Integer parentId;
    /**
     * 权限类型 (M目录 C菜单 B按钮)
     */
    private String menuType;
    /**
     * 名称
     */
    private String menuName;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单组件
     */
    private String component;
    /**
     * 权限标识
     */
    private String perms;
    /**
     * 是否隐藏 (0否 1是)
     */
    private Integer isHidden;
    /**
     * 是否禁用 (0否 1是)
     */
    private Integer isDisable;
    /**
     * 排序
     */
    private Integer orderNum;
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
     * 逻辑删除标识(0否 1是)
     */
    private Integer isDelete;


}