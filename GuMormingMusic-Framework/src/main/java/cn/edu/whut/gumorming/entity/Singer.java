package cn.edu.whut.gumorming.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 歌手表(Singer)表实体类
 *
 * @author GuMorming
 * @since 2024-02-21 15:28:22
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("singer")
public class Singer {
    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 歌手名
     */
    private String name;
    /**
     * 性别(0女 1男)
     */
    private Integer sex;
    /**
     * 歌手图片
     */
    private String pic;
    /**
     * 出生日期
     */
    private LocalDateTime birth;
    /**
     * 地区
     */
    private String location;
    /**
     * 歌手介绍
     */
    private String introduction;


}