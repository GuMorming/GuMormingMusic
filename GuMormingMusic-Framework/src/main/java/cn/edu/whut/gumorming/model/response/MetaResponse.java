package cn.edu.whut.gumorming.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(title = "路由其他信息Response")
public class MetaResponse {

    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称")
    private String title;

    /**
     * 菜单图标
     */
    @Schema(title = "菜单图标")
    private String icon;

    /**
     * 是否隐藏
     */
    @Schema(title = "是否隐藏")
    private Boolean hidden;

}