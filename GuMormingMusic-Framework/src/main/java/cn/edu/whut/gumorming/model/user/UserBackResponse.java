package cn.edu.whut.gumorming.model.user;

import cn.edu.whut.gumorming.model.response.UserRoleResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(title = "用户后台Response")
public class UserBackResponse {

    /**
     * 用户id
     */
    @Schema(title = "用户id")
    private Integer id;

    /**
     * 用户昵称
     */
    @Schema(title = "用户昵称")
    private String nickname;

    /**
     * 用户头像
     */
    @Schema(title = "用户头像")
    private String avatar;

    /**
     * 登录ip todo 脱敏
     */
//    @Desensitization(type = DesensitizationTypeEnum.IP4)
    @Schema(title = "登录ip")
    private String ipAddress;

    /**
     * 登录地址 todo 脱敏
     */

//    @Desensitization(type = DesensitizationTypeEnum.ADDRESS)
    @Schema(title = "登录地址")
    private String ipSource;

    /**
     * 登录方式 (1邮箱 2QQ 3Gitee 4Github)
     */
    @Schema(title = "登录方式 (1邮箱 2QQ 3Gitee 4Github)")
    private Integer loginType;

    /**
     * 用户角色
     */
    @Schema(title = "用户角色")
    private List<UserRoleResponse> roleList;

    /**
     * 是否禁用 (0否 1是)
     */
    @Schema(title = "是否禁用 (0否 1是)")
    private Integer isDisable;

    /**
     * 登录时间
     */
    @Schema(title = "登录时间")
    private LocalDateTime loginTime;

    /**
     * 创建时间
     */
    @Schema(title = "创建时间")
    private LocalDateTime createTime;

}