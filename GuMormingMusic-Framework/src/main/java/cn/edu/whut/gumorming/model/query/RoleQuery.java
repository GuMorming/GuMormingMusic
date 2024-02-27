package cn.edu.whut.gumorming.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "角色查询条件")
public class RoleQuery extends PageQuery {

    /**
     * 搜索内容
     */
    @Schema(title = "搜索内容")
    private String keyword;

    /**
     * 是否禁用 (0否 1是)
     */
    @Schema(title = "是否禁用 (0否 1是)")
    private Integer isDisable;

}