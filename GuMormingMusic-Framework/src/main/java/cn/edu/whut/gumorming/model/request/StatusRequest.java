package cn.edu.whut.gumorming.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(title = "状态Request")
public class StatusRequest {
    /**
     * id
     */
    @NotNull(message = "id不能为空")
    @Schema(title = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    @Schema(title = "状态", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;
}