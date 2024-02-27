package cn.edu.whut.gumorming.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(title = "角色Response")
public class RoleResponse {

    /**
     * 角色id
     */
    @Schema(title = "角色id")
    private Integer id;

    /**
     * 角色名
     */
    @Schema(title = "角色名")
    private String roleName;

    /**
     * 角色描述
     */
    @Schema(title = "角色描述")
    private String roleDesc;

    /**
     * 是否禁用 (0否 1是)
     */
    @Schema(title = "是否禁用 (0否 1是)")
    private Integer isDisable;

    /**
     * 创建时间
     */
    @Schema(title = "创建时间")
    private LocalDateTime createTime;

}