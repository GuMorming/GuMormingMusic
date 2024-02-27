package cn.edu.whut.gumorming.model.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model.vo.category
 * @createTime : 2024/2/23 16:10
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@Schema(title = "分类选项Response")
public class CategoryOptionResponse {
    @Schema(title = "分类id")
    private Integer id;

    @Schema(title = "分类名")
    private String categoryName;

}