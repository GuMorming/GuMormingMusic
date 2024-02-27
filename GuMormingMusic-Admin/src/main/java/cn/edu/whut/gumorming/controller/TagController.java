package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.OpsLogger;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.TagQuery;
import cn.edu.whut.gumorming.model.request.TagRequest;
import cn.edu.whut.gumorming.model.response.TagBackResponse;
import cn.edu.whut.gumorming.model.response.TagOptionResp;
import cn.edu.whut.gumorming.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.edu.whut.gumorming.constants.OpsTypeConstants.*;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2024/2/21 14:17
 * @Email : gumorming@163.com
 * @Description :
 */
@Tag(name = "标签模块")
@RestController
@RequestMapping("/admin/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 查看后台标签列表
     *
     * @param tagQuery 标签查询条件
     * @return {@link TagBackResponse} 后台标签
     */
    @Operation(summary = "查看后台标签列表")
    @PreAuthorize("hasAuthority('blog:tag:list')")
    @GetMapping("/list")
    public ResponseResult<PageResult<TagBackResponse>> listTagBackVO(TagQuery tagQuery) {
        return ResponseResult.ok(tagService.listTagBackVO(tagQuery));
    }

    /**
     * 添加标签
     *
     * @param tag 标签信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = ADD)
    @Operation(summary = "添加标签")
    @PreAuthorize("hasAuthority('blog:tag:add')")
    @PostMapping("/add")
    public ResponseResult<?> addTag(@Validated @RequestBody TagRequest tag) {
        return tagService.addTag(tag);

    }

    /**
     * 删除标签
     *
     * @param tagIdList 标签id集合
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = DELETE)
    @Operation(summary = "删除分类")
    @PreAuthorize("hasAuthority('blog:tag:delete')")
    @DeleteMapping("/delete")
    public ResponseResult<?> deleteTag(@RequestBody List<Integer> tagIdList) {
        return tagService.deleteTag(tagIdList);

    }

    /**
     * 修改标签
     *
     * @param tag 标签信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = UPDATE)
    @Operation(summary = "修改标签")
    @PreAuthorize("hasAuthority('blog:tag:update')")
    @PutMapping("/update")
    public ResponseResult<?> updateTag(@Validated @RequestBody TagRequest tag) {
        return tagService.updateTag(tag);

    }

    /**
     * 查看标签选项
     *
     * @return {@link ResponseResult<TagOptionResp>} 标签列表
     */
    @Operation(summary = "查看标签选项")
    @GetMapping("/option")
    public ResponseResult<List<TagOptionResp>> listTagOption() {
        return ResponseResult.ok(tagService.listTagOption());
    }


}