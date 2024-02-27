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
 * (ExceptionLog)表实体类
 *
 * @author GuMorming
 * @since 2024-02-20 12:19:23
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("exception_log")
public class ExceptionLog {
    /**
     * 异常id
     */
    @TableId
    private Integer id;

    /**
     * 异常模块
     */
    private String module;
    /**
     * 异常uri
     */
    private String uri;
    /**
     * 异常名称
     */
    private String name;
    /**
     * 操作描述
     */
    private String description;
    /**
     * 异常方法
     */
    private String errorMethod;
    /**
     * 异常信息
     */
    private String message;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作ip
     */
    private String ipAddress;
    /**
     * 操作地址
     */
    private String ipSource;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 操作时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}