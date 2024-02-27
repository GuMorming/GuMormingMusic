package cn.edu.whut.gumorming.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.model.vo.response
 * @createTime : 2024/2/19 10:42
 * @Email : gumorming@163.com
 * @Description :
 */

@Data
@Schema(title = "菜单选项树Response")
public class MenuOptionResponse {

    /**
     * id
     */
    @Schema(title = "菜单id")
    private Integer value;

    /**
     * 父菜单id
     */
    @JsonIgnore
    @Schema(title = "父菜单id")
    private Integer parentId;

    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称")
    private String label;

    /**
     * 子菜单树
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(title = "子菜单树")
    private List<MenuOptionResponse> children;
}