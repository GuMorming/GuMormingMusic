package cn.edu.whut.gumorming.model.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model.vo.category
 * @createTime : 2024/2/23 16:37
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@Schema(title = "分类")
public class CategoryRequest {
    /**
     * 分类id
     */
    @Schema(title = "分类id")
    private Integer id;

    /**
     * 分类名
     */
    @NotBlank(message = "分类名不能为空")
    @Schema(title = "分类名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String categoryName;
}