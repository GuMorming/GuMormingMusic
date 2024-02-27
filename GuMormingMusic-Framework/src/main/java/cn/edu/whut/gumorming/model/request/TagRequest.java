package cn.edu.whut.gumorming.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "标签Request")
public class TagRequest {

    /**
     * 标签id
     */
    @Schema(title = "标签id")
    private Integer id;

    /**
     * 标签名
     */
    @NotBlank(message = "标签名不能为空")
    @Schema(title = "标签名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String tagName;

}