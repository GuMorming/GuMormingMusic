package cn.edu.whut.gumorming.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文章贡献统计Response
 */
@Data
@Schema(title = "文章贡献统计Response")
public class ArticleStatisticsResp {

    /**
     * 日期
     */
    @Schema(title = "日期")
    private String date;

    /**
     * 数量
     */
    @Schema(title = "数量")
    private Integer count;
}