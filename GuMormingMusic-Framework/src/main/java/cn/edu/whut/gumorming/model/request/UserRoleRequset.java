package cn.edu.whut.gumorming.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "用户角色Request")
public class UserRoleRequset {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    @Schema(title = "用户id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    @Schema(title = "昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;

    /**
     * 角色id
     */
    @NotEmpty(message = "角色id不能为空")
    @Schema(title = "角色id", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> roleIdList;
}