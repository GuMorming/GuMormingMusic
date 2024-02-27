package cn.edu.whut.gumorming.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页返回类")
public class PageResult<T> {

    /**
     * 分页结果
     */
    @Schema(title = "分页结果")
    private List<T> recordList;

    /**
     * 总数
     */
    @Schema(title = "总数", type = "Long")
    private Long count;

}