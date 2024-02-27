package cn.edu.whut.gumorming.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model.vo.query
 * @createTime : 2024/2/19 9:52
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@Schema(title = "菜单Request")
public class MenuRequest {

    /**
     * 菜单id
     */
    @Schema(title = "菜单id")
    private Integer id;

    /**
     * 父菜单id
     */
    @Schema(title = "父级菜单id")
    private Integer parentId;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Schema(title = "菜单名称", required = true)
    private String menuName;

    /**
     * 类型（M目录 C菜单 B按钮）
     */
    @NotBlank(message = "类型不能为空")
    @Schema(title = "类型（M目录 C菜单 B按钮）", required = true)
    private String menuType;

    /**
     * 路由地址
     */
    @Schema(title = "路由地址")
    private String path;

    /**
     * 菜单图标
     */
    @Schema(title = "菜单图标")
    private String icon;

    /**
     * 菜单组件
     */
    @Schema(title = "菜单组件")
    private String component;

    /**
     * 是否隐藏 (0否 1是)
     */
    @Schema(title = "是否隐藏 (0否 1是)")
    private Integer isHidden;

    /**
     * 是否禁用 (0否 1是)
     */
    @Schema(title = "是否禁用 (0否 1是)")
    private Integer isDisable;

    /**
     * 菜单排序
     */
    @NotNull(message = "菜单排序不能为空")
    @Schema(title = "菜单排序", required = true)
    private Integer orderNum;

    /**
     * 权限标识
     */
    @Schema(title = "权限标识")
    private String perms;
}