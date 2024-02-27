package cn.edu.whut.gumorming.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model.vo.response
 * @createTime : 2024/2/19 15:36
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@Builder
@Schema(title = "后台登录用户信息Response")
public class UserInfoResponse {
    /**
     * 用户id
     */
    @Schema(title = "用户id")
    private Integer id;

    /**
     * 头像
     */
    @Schema(title = "头像")
    private String avatar;

    /**
     * 角色
     */
    @Schema(title = "角色")
    private List<Integer> roleIdList;

    /**
     * 权限标识
     */
    @Schema(title = "权限标识")
    private List<String> permissionList;
}