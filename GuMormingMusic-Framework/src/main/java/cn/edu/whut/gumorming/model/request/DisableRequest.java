package cn.edu.whut.gumorming.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(title = "禁用状态Request")
public class DisableRequest {
    /**
     * id
     */
    @NotNull(message = "id不能为空")
    @Schema(title = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 是否禁用 (0否 1是)
     */
    @NotNull(message = "状态不能为空")
    @Schema(title = "是否禁用 (0否 1是)", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer isDisable;
}