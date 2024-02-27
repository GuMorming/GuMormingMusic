package cn.edu.whut.gumorming.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (TaskLog)表实体类
 *
 * @author GuMorming
 * @since 2024-02-20 12:29:48
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("task_log")
public class TaskLog {
    /**
     * 任务日志id
     */
    @TableId
    private Integer id;

    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 任务组名
     */
    private String taskGroup;
    /**
     * 调用目标字符串
     */
    private String invokeTarget;
    /**
     * 日志信息
     */
    private String taskMessage;
    /**
     * 执行状态 (0失败 1正常)
     */
    private Integer status;
    /**
     * 错误信息
     */
    private String errorInfo;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}