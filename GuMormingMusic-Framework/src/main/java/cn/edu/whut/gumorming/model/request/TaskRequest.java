package cn.edu.whut.gumorming.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "定时任务Request")
public class TaskRequest {

    /**
     * 任务id
     */
    @Schema(title = "任务id")
    private Integer id;

    /**
     * 任务名称
     */
    @NotBlank(message = "任务名称不能为空")
    @Schema(title = "任务名称", required = true)
    private String taskName;

    /**
     * 任务组名
     */
    @NotBlank(message = "任务组名不能为空")
    @Schema(title = "任务组名", required = true)
    private String taskGroup;

    /**
     * 调用目标
     */
    @NotBlank(message = "调用目标不能为空")
    @Schema(title = "调用目标", required = true)
    private String invokeTarget;

    /**
     * cron执行表达式
     */
    @Schema(title = "cron执行表达式")
    private String cronExpression;

    /**
     * 计划执行错误策略 (1立即执行 2执行一次 3放弃执行)
     */
    @Schema(title = "计划执行错误策略 (1立即执行 2执行一次 3放弃执行)")
    private Integer misfirePolicy;

    /**
     * 是否并发执行 (0否 1是)
     */
    @Schema(title = "是否并发执行 (0否 1是)")
    private Integer concurrent;

    /**
     * 任务状态 (0运行 暂停)
     */
    @Schema(title = "任务状态 (0运行 暂停)")
    private Integer status;

    /**
     * 任务备注信息
     */
    @Schema(title = "任务备注信息")
    private String remark;
}