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
 * (SongList)表实体类
 *
 * @author GuMorming
 * @since 2024-02-23 15:08:45
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("song_list")
public class SongList {
    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 歌单名
     */
    private String title;
    /**
     * 歌单图片
     */
    private String pic;
    /**
     * 歌单简介
     */
    private String introduction;
    /**
     * 歌单分类id
     */
    private Integer categoryId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
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
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 逻辑删除(0否 1是)
     */
    private Integer isDelete;
}