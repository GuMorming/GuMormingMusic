package cn.edu.whut.gumorming.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "用户菜单Response")
public class UserMenuResponse {

    /**
     * 菜单id
     */
    @Schema(title = "菜单id")
    private Integer id;

    /**
     * 父菜单id
     */
    @Schema(title = "父菜单id")
    private Integer parentId;

    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称")
    private String menuName;

    /**
     * 类型（M目录 C菜单 B按钮）
     */
    @Schema(title = "类型（M目录 C菜单 B按钮）")
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

}