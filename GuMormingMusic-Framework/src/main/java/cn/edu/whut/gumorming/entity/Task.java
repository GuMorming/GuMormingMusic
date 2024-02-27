package cn.edu.whut.gumorming.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * (Task)表实体类
 *
 * @author GuMorming
 * @since 2024-02-20 21:51:01
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("task")
public class Task {
    /**
     * 任务id
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
     * 调用目标
     */
    private String invokeTarget;
    /**
     * cron执行表达式
     */
    private String cronExpression;
    /**
     * 计划执行错误策略 (1立即执行 2执行一次 3放弃执行)
     */
    private Integer misfirePolicy;
    /**
     * 是否并发执行 (0否 1是)
     */
    private Integer concurrent;
    /**
     * 任务状态 (0运行 1暂停)
     */
    private Integer status;
    /**
     * 任务备注信息
     */
    private String remark;
    /**
     * 创建人ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer createBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新人ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 逻辑删除(0否 1是)
     */
    private Integer isDelete;


}