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
 * (Tag)表实体类
 *
 * @author GuMorming
 * @since 2024-02-21 14:13:09
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tag")
public class Tag {
    /**
     * 标签id
     */
    @TableId
    private Integer id;

    /**
     * 标签名
     */
    private String tagName;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer createBy;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateBy;
    /**
     * 逻辑删除(0否 1是)
     */
    private Integer isDelete;


}