package cn.edu.whut.gumorming.model.song;

import cn.edu.whut.gumorming.model.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model.song
 * @createTime : 2024/2/24 11:42
 * @Email : gumorming@163.com
 * @Description :
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "歌曲查询条件")
public class SongQuery extends PageQuery {
    @Schema(title = "歌曲名")
    private String name;

    @Schema(title = "歌手名")
    private String singerName;

    @Schema(title = "分类id")
    private Integer categoryId;
}