package cn.edu.whut.gumorming.model.songList;

import cn.edu.whut.gumorming.model.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model.vo.songList
 * @createTime : 2024/2/23 15:02
 * @Email : gumorming@163.com
 * @Description :
 */
@Schema(title = "歌单查询条件")
@Data
@EqualsAndHashCode(callSuper = true)
public class SongListQuery extends PageQuery {
    /**
     * 搜索内容
     */
    @Schema(title = "搜索内容")
    private String keyword;

    /**
     * 分类Id
     */
    @Schema(title = "分类Id")
    private Integer categoryId;
}