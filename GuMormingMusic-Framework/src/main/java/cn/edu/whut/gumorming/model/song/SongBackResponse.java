package cn.edu.whut.gumorming.model.song;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model.song
 * @createTime : 2024/2/24 11:41
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
public class SongBackResponse {
    @Schema(title = "歌曲id")
    private Integer id;

    @Schema(title = "歌手id")
    private Integer singerId;

    @Schema(title = "歌名")
    private String name;

    @Schema(title = "专辑名")
    private String album;

    @Schema(title = "歌曲图片")
    private String pic;

    @Schema(title = "歌词")
    private String lyric;

    @Schema(title = "歌曲url")
    private String url;
}