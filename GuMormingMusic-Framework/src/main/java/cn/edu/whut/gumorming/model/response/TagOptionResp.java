package cn.edu.whut.gumorming.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 标签选项Response
 **/
@Data
@Schema(title = "标签选项Response")
public class TagOptionResp {

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
}