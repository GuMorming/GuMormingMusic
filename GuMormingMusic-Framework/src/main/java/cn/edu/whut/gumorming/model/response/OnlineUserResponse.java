package cn.edu.whut.gumorming.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@Accessors
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "在线用户Response")
public class OnlineUserResponse {

    /**
     * 用户id
     */
    @Schema(title = "用户id")
    private Integer id;

    /**
     * 在线token
     */
    @Schema(title = "在线token")
    private String token;

    /**
     * 用户头像
     */
    @Schema(title = "用户头像")
    private String avatar;

    /**
     * 用户昵称
     */
    @Schema(title = "用户昵称")
    private String nickname;

    /**
     * ip地址
     */
    @Schema(title = "ip地址")
    private String ipAddress;

    /**
     * ip来源
     */
    @Schema(title = "ip来源")
    private String ipSource;

    /**
     * 操作系统
     */
    @Schema(title = "操作系统")
    private String os;

    /**
     * 浏览器
     */
    @Schema(title = "浏览器")
    private String browser;

    /**
     * 登录时间
     */
    @Schema(title = "登录时间")
    private LocalDateTime loginTime;

}