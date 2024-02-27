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
 * (VisitLog)表实体类
 *
 * @author GuMorming
 * @since 2024-02-20 12:19:40
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("visit_log")
public class VisitLog {
    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 访问页面
     */
    private String page;
    /**
     * 访问ip
     */
    private String ipAddress;
    /**
     * 访问地址
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
     * 访问时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}