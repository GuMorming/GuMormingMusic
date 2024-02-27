package cn.edu.whut.gumorming.model.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data

@Schema(title = "分类后台Response")
public class CategoryBackResponse {
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
     * 创建时间
     */
    @Schema(title = "创建时间")
    private LocalDateTime createTime;

}