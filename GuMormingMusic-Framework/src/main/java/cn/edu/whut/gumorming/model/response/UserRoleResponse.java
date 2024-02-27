package cn.edu.whut.gumorming.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户角色Response")
public class UserRoleResponse {

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
}