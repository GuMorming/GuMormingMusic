package cn.edu.whut.gumorming.model.category;

import cn.edu.whut.gumorming.model.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "分类查询条件")
public class CategoryQuery extends PageQuery {

    /**
     * 搜索内容
     */
    @Schema(title = "搜索内容")
    private String keyword;

}