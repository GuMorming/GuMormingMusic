package cn.edu.whut.gumorming.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "标签Response")
public class TagResp {

    /**
     * 标签id
     */
    @Schema(title = "标签id")
    private Integer id;

    /**
     * 标签名
     */
    @Schema(title = "标签名")
    private String tagName;

    /**
     * 文章数量
     */
    @Schema(title = "文章数量")
    private Integer articleCount;
}