package cn.edu.whut.gumorming.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(title = "文件后台Response")
public class FileResponse {
    /**
     * 文件id
     */
    @Schema(title = "文件id")
    private Integer id;

    /**
     * 文件url
     */
    @Schema(title = "文件url")
    private String fileUrl;

    /**
     * 文件名
     */
    @Schema(title = "文件名")
    private String fileName;

    /**
     * 文件大小
     */
    @Schema(title = "文件大小")
    private Integer fileSize;

    /**
     * 文件类型
     */
    @Schema(title = "文件类型")
    private String extendName;

    /**
     * 文件路径
     */
    @Schema(title = "文件路径")
    private String filePath;

    /**
     * 是否为目录 (0否 1是)
     */
    @Schema(title = "是否为目录 (0否 1是)")
    private Integer isDir;

    /**
     * 创建时间
     */
    @Schema(title = "创建时间")
    private LocalDateTime createTime;
}