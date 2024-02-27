package cn.edu.whut.gumorming.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(title = "目录Request")
public class FolderRequest {

    /**
     * 文件路径
     */
    @NotBlank(message = "文件路径不能为空")
    @Schema(title = "文件路径", requiredMode = Schema.RequiredMode.REQUIRED)
    private String filePath;

    /**
     * 文件名称
     */
    @NotBlank(message = "文件名称不能为空")
    @Schema(title = "文件名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String fileName;
}