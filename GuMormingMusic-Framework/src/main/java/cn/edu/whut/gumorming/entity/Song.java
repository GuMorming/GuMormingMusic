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
 * 歌曲表(Song)表实体类
 *
 * @author GuMorming
 * @since 2024-02-21 15:12:07
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("song")
public class Song {
    /**
     * 歌曲id
     */
    @TableId
    private Integer id;

    /**
     * 歌手id
     */
    private Integer singerId;
    /**
     * 歌曲名
     */
    private String name;
    /**
     * 专辑名
     */
    private String album;
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
     * 歌曲封面图
     */
    private String pic;
    /**
     * 歌词
     */
    private String lyric;
    /**
     * 歌曲url
     */
    private String url;
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