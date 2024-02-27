package cn.edu.whut.gumorming.model.songList;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model.vo.songList
 * @createTime : 2024/2/23 15:02
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@Schema(title = "歌单后台Response")
public class SongListBackResponse {

    @Schema(title = "主键")
    private Integer id;


    @Schema(title = "歌单名")
    private String title;

    @Schema(title = "歌单图片")
    private String pic;

    @Schema(title = "歌单简介")
    private String introduction;

    @Schema(title = "歌单分类id")
    private Integer categoryId;

    @Schema(title = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(title = "更新时间")
    private LocalDateTime updateTime;

}