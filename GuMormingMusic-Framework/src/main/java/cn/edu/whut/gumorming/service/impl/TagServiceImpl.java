package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.Tag;
import cn.edu.whut.gumorming.mapper.TagMapper;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.TagQuery;
import cn.edu.whut.gumorming.model.request.TagRequest;
import cn.edu.whut.gumorming.model.response.TagBackResponse;
import cn.edu.whut.gumorming.model.response.TagOptionResp;
import cn.edu.whut.gumorming.service.TagService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * (Tag)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-21 14:13:09
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public PageResult<TagBackResponse> listTagBackVO(TagQuery tagQuery) {
        // 构造条件
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<Tag>()
                .like(StringUtils.hasText(tagQuery.getKeyword()), Tag::getTagName, tagQuery.getKeyword());
        // 分页
        Page<Tag> page = new Page<>(tagQuery.getCurrent(), tagQuery.getSize());
        page(page, queryWrapper);
        // 封装VO
        List<TagBackResponse> tagBackResponses = BeanCopyUtils.copyBeanList(page.getRecords(), TagBackResponse.class);

        return new PageResult<>(tagBackResponses, page.getTotal());
    }

    @Override
    public List<TagOptionResp> listTagOption() {
        return null;
    }

    @Override
    public ResponseResult<?> updateTag(TagRequest tag) {
        return null;
    }

    @Override
    public ResponseResult<?> addTag(TagRequest tag) {
        // 标签是否存在
        Tag existTag = baseMapper.selectOne(new LambdaQueryWrapper<Tag>()
                .select(Tag::getId)
                .eq(Tag::getTagName, tag.getTagName()));
        Assert.isNull(existTag, tag.getTagName() + "标签已存在");
        // 添加新标签
        Tag newTag = Tag.builder()
                .tagName(tag.getTagName())
                .build();
        baseMapper.insert(newTag);

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> deleteTag(List<Integer> tagIdList) {
        // todo 未实现
//        // 标签下是否有歌曲
//        Long count = musicTagMapper.selectCount(new LambdaQueryWrapper<MusicTag>()
//                .in(MusicTag::getTagId, tagIdList));
//        Assert.isFalse(count > 0, "删除失败，标签下存在文章");
//        // 批量删除标签
//        baseMapper.deleteBatchIds(tagIdList);
        return ResponseResult.ok();
    }
}