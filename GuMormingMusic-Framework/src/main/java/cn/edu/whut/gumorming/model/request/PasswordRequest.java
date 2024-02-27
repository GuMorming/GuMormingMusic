package cn.edu.whut.gumorming.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "用户密码")
public class PasswordRequest {

    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空")
    @Schema(title = "旧密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String oldPassword;

    /**
     * 新密码
     */
    @Size(min = 6, message = "新密码不能少于6位")
    @NotBlank(message = "新密码不能为空")
    @Schema(title = "新密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String newPassword;
}