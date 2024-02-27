package cn.edu.whut.gumorming.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户浏览Response
 **/
@Data
@Schema(title = "用户浏览Response")
public class UserViewResp {

    /**
     * 日期
     */
    @Schema(title = "日期")
    private String date;

    /**
     * pv
     */
    @Schema(title = "pv")
    private Integer pv;

    /**
     * uv
     */
    @Schema(title = "uv")
    private Integer uv;
}