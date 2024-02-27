package cn.edu.whut.gumorming.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "角色Request")
public class RoleRequest {

    /**
     * 角色id
     */
    @Schema(title = "角色id")
    private Integer id;

    /**
     * 角色名
     */
    @NotBlank(message = "角色名不能为空")
    @Schema(title = "角色名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String roleName;

    /**
     * 角色描述
     */
    @Schema(title = "角色描述")
    private String roleDesc;

    /**
     * 是否禁用 (0否 1是)
     */
    @NotNull(message = "角色状态不能为空")
    @Schema(title = "是否禁用 (0否 1是)", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer isDisable;

    /**
     * 菜单id集合
     */
    @Schema(title = "菜单id集合")
    private List<Integer> menuIdList;
}