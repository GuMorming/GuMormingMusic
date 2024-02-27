package cn.edu.whut.gumorming.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model.vo.query
 * @createTime : 2024/2/19 9:52
 * @Email : gumorming@163.com
 * @Description :
 */
@Schema(title = "菜单查询条件")
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuQuery extends PageQuery {
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