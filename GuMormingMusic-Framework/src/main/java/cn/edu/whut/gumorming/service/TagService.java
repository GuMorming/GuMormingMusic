package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.Tag;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.TagQuery;
import cn.edu.whut.gumorming.model.request.TagRequest;
import cn.edu.whut.gumorming.model.response.TagBackResponse;
import cn.edu.whut.gumorming.model.response.TagOptionResp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * (Tag)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-21 14:13:09
 */
public interface TagService extends IService<Tag> {
    PageResult<TagBackResponse> listTagBackVO(TagQuery tagQuery);

    List<TagOptionResp> listTagOption();

    ResponseResult<?> updateTag(TagRequest tag);


    ResponseResult<?> addTag(TagRequest tag);

    ResponseResult<?> deleteTag(List<Integer> tagIdList);
}