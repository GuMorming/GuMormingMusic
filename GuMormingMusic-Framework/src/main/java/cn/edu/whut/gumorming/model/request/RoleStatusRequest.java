package cn.edu.whut.gumorming.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(title = "角色状态Request")
public class RoleStatusRequest {
    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空")
    @Schema(title = "角色id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 是否禁用 (0否 1是)
     */
    @NotNull(message = "角色状态不能为空")
    @Schema(title = "是否禁用 (0否 1是)", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer isDisable;
}