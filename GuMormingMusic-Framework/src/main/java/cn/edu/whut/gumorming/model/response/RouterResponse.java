package cn.edu.whut.gumorming.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "路由Response")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterResponse {

    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称")
    private String name;

    /**
     * 路由地址
     */
    @Schema(title = "路由地址")
    private String path;

    /**
     * 菜单组件
     */
    @Schema(title = "菜单组件")
    private String component;

    /**
     * 路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
     */
    private Boolean alwaysShow;

    /**
     * 重定向地址
     */
    @Schema(title = "重定向地址")
    private String redirect;

    /**
     * 其他信息
     */
    @Schema(title = "其他信息")
    private MetaResponse meta;

    /**
     * 子菜单列表
     */
    @Schema(title = "子菜单列表")
    private List<RouterResponse> children;
}