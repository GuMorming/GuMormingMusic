package cn.edu.whut.gumorming.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "定时任务运行Request")
public class TaskRunRequest {

    /**
     * 任务id
     */
    @Schema(title = "任务id")
    private Integer id;

    /**
     * 任务组别
     */
    @Schema(title = "任务组别")
    private String taskGroup;
}