package cn.edu.whut.gumorming.model.singer;

import cn.edu.whut.gumorming.model.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model.vo.query
 * @createTime : 2024/2/21 15:36
 * @Email : gumorming@163.com
 * @Description :
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "歌手查询条件")
public class SingerQuery extends PageQuery {
    /**
     * 搜索内容
     */
    @Schema(title = "搜索内容")
    private String keyword;

    /**
     * 是否禁用 (0否 1是)
     */
    @Schema(title = "性别 (0女 1男)")
    private Integer sex;


}