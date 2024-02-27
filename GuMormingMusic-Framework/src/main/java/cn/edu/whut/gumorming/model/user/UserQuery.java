package cn.edu.whut.gumorming.model.user;

import cn.edu.whut.gumorming.model.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data

@EqualsAndHashCode(callSuper = true)
@Schema(title = "用户查询条件")
public class UserQuery extends PageQuery {

    /**
     * 搜索内容
     */
    @Schema(title = "搜索内容")
    private String keyword;

    /**
     * 登录方式 (1邮箱 2QQ 3Gitee 4Github)
     */
    @Schema(title = "登录方式 (1邮箱 2QQ 3Gitee 4Github)")
    private Integer loginType;

}