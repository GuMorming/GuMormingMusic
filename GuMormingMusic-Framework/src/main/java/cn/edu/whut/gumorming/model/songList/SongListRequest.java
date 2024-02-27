package cn.edu.whut.gumorming.model.songList;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model.vo.songList
 * @createTime : 2024/2/23 15:02
 * @Email : gumorming@163.com
 * @Description :
 */
@Data

@Schema(title = "歌单Request")
public class SongListRequest {
    @Schema(title = "歌单id")
    private Integer id;

    @NotBlank(message = "歌单名不能为空")
    @Schema(title = "歌单名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(title = "歌单分类")
    private Integer categoryId;

    @Schema(title = "歌单图片")
    private String pic;

    @Schema(title = "歌单简介")
    private String introduction;
}