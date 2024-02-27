package cn.edu.whut.gumorming.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件查询条件
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "文件查询条件")
public class FileQuery extends PageQuery {

    /**
     * 文件路径
     */
    @Schema(title = "文件路径")
    private String filePath;

}