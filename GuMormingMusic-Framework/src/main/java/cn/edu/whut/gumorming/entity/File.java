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
 * (File)表实体类
 *
 * @author GuMorming
 * @since 2024-02-21 10:50:04
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("file")
public class File {
    /**
     * 文件id
     */
    @TableId
    private Integer id;

    /**
     * 文件url
     */
    private String fileUrl;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件大小
     */
    private Integer fileSize;
    /**
     * 文件类型
     */
    private String extendName;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 是否为目录 (0否 1是)
     */
    private Integer isDir;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
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


}