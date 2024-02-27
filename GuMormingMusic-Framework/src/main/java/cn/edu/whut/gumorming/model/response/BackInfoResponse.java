package cn.edu.whut.gumorming.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(title = "网站后台信息Response")
public class BackInfoResponse {

    /**
     * 访问量
     */
    @Schema(title = "访问量")
    private Integer viewCount;

    /**
     * 留言量
     */
    @Schema(title = "留言量")
    private Long messageCount;

    /**
     * 用户量
     */
    @Schema(title = "用户量")
    private Long userCount;

    /**
     * 文章量
     */
    @Schema(title = "音乐量")
    private Long songCount;

    /**
     * 分类统计
     */
    @Schema(title = "分类统计")
    private List<CategoryHomeResponse> categoryVOList;

    /**
     * 标签列表
     */
    @Schema(title = "标签列表")
    private List<TagOptionResp> tagVOList;


    /**
     *  todo 音乐浏览量排行
     */
//    @Schema(title = "歌曲浏览量排行")
//    private List<SongRankResponse> articleRankVOList;

    /**
     * 一周访问量
     */
    @Schema(title = "一周访问量")
    private List<UserViewResp> userViewVOList;
}