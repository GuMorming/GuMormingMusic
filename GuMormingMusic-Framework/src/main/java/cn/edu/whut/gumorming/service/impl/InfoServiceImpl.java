package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.RedisConstants;
import cn.edu.whut.gumorming.mapper.*;
import cn.edu.whut.gumorming.model.response.ArticleRankResp;
import cn.edu.whut.gumorming.model.response.BackInfoResponse;
import cn.edu.whut.gumorming.model.response.CategoryHomeResponse;
import cn.edu.whut.gumorming.model.response.UserViewResp;
import cn.edu.whut.gumorming.service.InfoService;
import cn.edu.whut.gumorming.service.SiteConfigService;
import cn.edu.whut.gumorming.utils.redis.RedisCache;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.service.impl
 * @createTime : 2024/2/21 14:03
 * @Email : gumorming@163.com
 * @Description :
 */
@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private SongMapper songMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SiteConfigService siteConfigService;

//    @Autowired
//    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VisitLogMapper visitLogMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public BackInfoResponse getBackInfo() {
        // 访问量
        Integer viewCount = redisCache.getCacheObject(RedisConstants.CLIENT_VIEW_COUNT);
        // todo 留言量
//        Long messageCount = messageMapper.selectCount(null);
        // 用户量
        Long userCount = userMapper.selectCount(null);
        // 音乐量
        Long songCount = songMapper.selectCount(null);
        // todo 分类数据
        List<CategoryHomeResponse> categoryHomeResponses = categoryMapper.selectCategoryHomeList();
        //todo 标签数据
//        List<TagOptionResp> tagVOList = tagMapper.selectTagOptionList();
        // 查询用户浏览
        DateTime startTime = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), -7));
        DateTime endTime = DateUtil.endOfDay(new Date());
        List<UserViewResp> userViewRespList = visitLogMapper.selectUserViewList(startTime, endTime);

        //todo 查询redis访问量前五的歌曲
//        Map<Object, Double> songMap = redisCache.zReverseRangeWithScore(RedisConstants.ARTICLE_VIEW_COUNT, 0, 4);
        BackInfoResponse backInfoResponse = BackInfoResponse.builder()
//                .tagVOList(tagVOList)
                .viewCount(viewCount)
//                .messageCount(messageCount)
                .userCount(userCount)
                .songCount(songCount)
                .categoryVOList(categoryHomeResponses)
                .userViewVOList(userViewRespList)
                .build();
//        if (CollectionUtils.isNotEmpty(articleMap)) {
//            // todo 查询歌曲排行
//            List<ArticleRankResp> articleRankRespList = listArticleRank(articleMap);
//            blogBackInfoResp.setArticleRankVOList(articleRankRespList);
//        }
        return backInfoResponse;
    }

    /**
     * 查询文章排行
     *
     * @param articleMap 文章浏览量信息
     * @return {@link List< ArticleRankResp >} 文章排行
     */
    private List<ArticleRankResp> listArticleRank(Map<Object, Double> articleMap) {
        // todo
        return null;
//        // 提取文章id
//        List<Integer> articleIdList = new ArrayList<>(articleMap.size());
//        articleMap.forEach((key, value) -> articleIdList.add((Integer) key));
//        // 查询文章信息
//        List<Article> articleList = articleMapper.selectList(new LambdaQueryWrapper<Article>()
//                .select(Article::getId, Article::getArticleTitle)
//                .in(Article::getId, articleIdList));
//        return articleList.stream()
//                .map(article -> ArticleRankResp.builder()
//                        .articleTitle(article.getArticleTitle())
//                        .viewCount(articleMap.get(article.getId()).intValue())
//                        .build())
//                .sorted(Comparator.comparingInt(ArticleRankResp::getViewCount).reversed())
//                .collect(Collectors.toList());
    }
}