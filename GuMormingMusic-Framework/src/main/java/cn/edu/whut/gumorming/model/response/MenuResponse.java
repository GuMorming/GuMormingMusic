package cn.edu.whut.gumorming.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model.vo.response
 * @createTime : 2024/2/19 10:31
 * @Email : gumorming@163.com
 * @Description :
 */

@Data
@Schema(title = "菜单Response")
public class MenuResponse {

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
     * 类型（M目录 C菜单 B按钮）
     */
    @Schema(title = "类型（M目录 C菜单 B按钮）")
    private String menuType;

    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称")
    private String menuName;

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
     * 权限标识
     */
    @Schema(title = "权限标识")
    private String perms;

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
    @Schema(title = "菜单排序")
    private Integer orderNum;

    /**
     * 创建时间
     */
    @Schema(title = "创建时间")
    private LocalDateTime createTime;

    /**
     * 子菜单列表
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(title = "子菜单列表")
    private List<MenuResponse> children;

}