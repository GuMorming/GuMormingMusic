package cn.edu.whut.gumorming.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分类Response
 **/
@Data
@Schema(title = "分类Response")
public class CategoryHomeResponse {

    /**
     * 分类id
     */
    @Schema(title = "分类id")
    private Integer id;

    /**
     * 分类名
     */
    @Schema(title = "分类名")
    private String categoryName;

    /**
     * 文章数量
     */
    @Schema(title = "歌曲数量")
    private Integer songCount;

}