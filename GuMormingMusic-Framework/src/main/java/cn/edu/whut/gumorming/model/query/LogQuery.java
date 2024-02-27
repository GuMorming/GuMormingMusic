package cn.edu.whut.gumorming.model.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日志查询条件
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "日志查询条件")
public class LogQuery extends PageQuery {

    /**
     * 搜索内容
     */
    @Schema(title = "搜索内容")
    private String keyword;

    /**
     * 操作模块
     */
    @Schema(title = "操作模块")
    private String optModule;

}