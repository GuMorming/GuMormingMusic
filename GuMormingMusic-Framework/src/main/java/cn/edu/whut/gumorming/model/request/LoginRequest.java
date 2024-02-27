package cn.edu.whut.gumorming.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(title = "登录信息Request")
public class LoginRequest {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Schema(title = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
//    @Size(min = 6, message = "密码不能少于6位")
    @Schema(title = "用户密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

}