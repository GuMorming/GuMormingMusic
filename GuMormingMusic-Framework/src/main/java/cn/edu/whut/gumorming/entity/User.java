package cn.edu.whut.gumorming.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * (User)表实体类
 *
 * @author GuMorming
 * @since 2024-02-18 12:11:04
 */
@Data
@Builder
@Accessors
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    /**
     * 用户id
     */
    @TableId
    private Integer id;

    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户名
     */
    @NotEmpty
    private String username;
    /**
     * 用户密码
     */
    @NotEmpty
    private String password;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 个人简介
     */
    private String intro;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 登录ip
     */
    private String ipAddress;
    /**
     * 登录地址
     */
    private String ipSource;
    /**
     * 登录方式 (1邮箱 2QQ 3Gitee 4Github)
     */
    private Integer loginType;
    /**
     * 是否禁用 (0否 1是)
     */
    private Integer isDisable;
    /**
     * 登录时间
     */
    private LocalDateTime loginTime;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 逻辑删除(0否 1是)
     */
    private Integer isDelete;
    /**
     * 乐观锁字段
     */
    @Version
    private Integer version;


}