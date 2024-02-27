package cn.edu.whut.gumorming.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "定时任务日志Response")
public class TaskLogResponse {

    /**
     * 任务日志id
     */
    @Schema(title = "任务日志id")
    private Integer id;

    /**
     * 任务名称
     */
    @Schema(title = "任务名称")
    private String taskName;

    /**
     * 任务组名
     */
    @Schema(title = "任务组名")
    private String taskGroup;

    /**
     * 调用目标
     */
    @Schema(title = "调用目标")
    private String invokeTarget;

    /**
     * 日志信息
     */
    @Schema(title = "日志信息")
    private String taskMessage;

    /**
     * 任务状态 (0失败 1成功)
     */
    @Schema(title = "任务状态 (0失败 1成功)")
    private Integer status;

    /**
     * 错误信息
     */
    @Schema(title = "错误信息")
    private String errorInfo;

    /**
     * 创建时间
     */
    @Schema(title = "创建时间")
    private LocalDateTime createTime;

}