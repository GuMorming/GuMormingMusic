package cn.edu.whut.gumorming.controller.client;


import cn.edu.whut.gumorming.annotation.OpsLogger;
import cn.edu.whut.gumorming.entity.Singer;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.singer.SingerQuery;
import cn.edu.whut.gumorming.model.singer.SingerRequest;
import cn.edu.whut.gumorming.service.SingerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.edu.whut.gumorming.constants.OpsTypeConstants.*;


/**
 * 歌手表(Singer)表控制层
 *
 * @author GuMorming
 * @since 2024-02-21 15:32:03
 */
@Tag(name = "歌手模块")
@RestController
@RequestMapping("/admin/singer")
public class SingerController {
    /**
     * 服务对象
     */
    @Autowired
    private SingerService singerService;

    @Operation(summary = "查看后台歌手列表")
//    @PreAuthorize("hasAuthority('system:singer:list')")
    @GetMapping("/list")
    public ResponseResult<PageResult<Singer>> listSingerBackVO(SingerQuery singerQuery) {
        return ResponseResult.ok(singerService.listSingerBackVO(singerQuery));
    }

    /**
     * 添加歌手
     *
     * @param singerRequest 歌手信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(ADD)
    @Operation(summary = "添加歌手")
//    @PreAuthorize("hasAuthority('system:singer:add')")
    @PostMapping("/add")
    public ResponseResult<?> addSinger(@Validated @RequestBody SingerRequest singerRequest) {
        return singerService.addSinger(singerRequest);
    }

    /**
     * 删除歌手
     *
     * @param singerIdList 歌手id集合
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(DELETE)
    @Operation(summary = "删除歌手")
    @DeleteMapping("/delete")
    public ResponseResult<?> deleteSinger(@RequestBody List<Integer> singerIdList) {
        return singerService.deleteSinger(singerIdList);
    }


    /**
     * 修改角色
     *
     * @param updateSingerRequest 歌手信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = UPDATE)
    @Operation(summary = "修改歌手")
    @PutMapping("/update")
    public ResponseResult<?> updateSinger(@Validated @RequestBody SingerRequest updateSingerRequest) {
        return singerService.updateSinger(updateSingerRequest);
    }


}