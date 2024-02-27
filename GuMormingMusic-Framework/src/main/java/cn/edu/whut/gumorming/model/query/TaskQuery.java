package cn.edu.whut.gumorming.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "任务查询条件")
public class TaskQuery extends PageQuery {

    /**
     * 搜索内容
     */
    @Schema(title = "搜索内容")
    private String keyword;

    /**
     * 任务状态 (0运行 1暂停)
     */
    @Schema(title = "状态")
    private Integer status;

    /**
     * 任务组名
     */
    @Schema(title = "任务组名")
    private String taskGroup;
}