package cn.edu.whut.gumorming.controller.client;


import cn.edu.whut.gumorming.annotation.OpsLogger;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.songList.SongListBackResponse;
import cn.edu.whut.gumorming.model.songList.SongListQuery;
import cn.edu.whut.gumorming.model.songList.SongListRequest;
import cn.edu.whut.gumorming.service.SongListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.edu.whut.gumorming.constants.OpsTypeConstants.*;


/**
 * (SongList)表控制层
 *
 * @author GuMorming
 * @since 2024-02-23 14:58:59
 */
@Tag(name = "歌单模块")
@RestController
@RequestMapping("/admin/songList")
public class SongListController {
    /**
     * 服务对象
     */
    @Autowired
    private SongListService songListService;

    @Operation(summary = "查看后台歌单列表")
    @PreAuthorize("hasAuthority('client:songList:list')")
    @GetMapping("/list")
    public ResponseResult<PageResult<SongListBackResponse>> listSongListBackVO(SongListQuery songListQuery) {
        return ResponseResult.ok(songListService.listSongListBackVO(songListQuery));
    }

    /**
     * 添加歌手
     *
     * @param songListRequest 歌手信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(ADD)
    @Operation(summary = "添加歌单")
//    @PreAuthorize("hasAuthority('system:singer:add')")
    @PostMapping("/add")
    public ResponseResult<?> addSongList(@Validated @RequestBody SongListRequest songListRequest) {
        return songListService.addSongList(songListRequest);
    }

    /**
     * 删除歌手
     *
     * @param songListIdList 歌手id集合
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(DELETE)
    @Operation(summary = "删除歌单")
    @DeleteMapping("/delete")
    public ResponseResult<?> deleteSongList(@RequestBody List<Integer> songListIdList) {
        return songListService.deleteSongList(songListIdList);
    }


    /**
     * 修改角色
     *
     * @param updateSongListRequest 歌手信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = UPDATE)
    @Operation(summary = "修改歌手")
    @PutMapping("/update")
    public ResponseResult<?> updateSinger(@Validated @RequestBody SongListRequest updateSongListRequest) {
        return songListService.updateSinger(updateSongListRequest);
    }
}