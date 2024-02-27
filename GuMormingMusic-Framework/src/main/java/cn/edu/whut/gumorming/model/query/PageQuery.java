package cn.edu.whut.gumorming.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model.vo.query
 * @createTime : 2024/2/19 9:57
 * @Email : gumorming@163.com
 * @Description : 分页条件
 */
@Data
@Schema(title = "分页查询条件")
public class PageQuery {
    /**
     * 当前页
     */
    @Schema(title = "当前页", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer current;

    /**
     * 条数
     */
    @Schema(title = "条数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer size;

//    public Integer getCurrent() {
//        return current == null ? (PageConstants.DEFAULT_CURRENT - 1) * getSize() : (current - 1) * getSize();
//    }
//
//    public Integer getSize() {
//        return size == null ? PageConstants.MY_SIZE : size;
//    }
}