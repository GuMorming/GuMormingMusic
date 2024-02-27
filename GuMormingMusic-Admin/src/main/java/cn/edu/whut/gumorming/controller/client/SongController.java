package cn.edu.whut.gumorming.controller.client;

import cn.edu.whut.gumorming.annotation.OpsLogger;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.song.SongBackResponse;
import cn.edu.whut.gumorming.model.song.SongQuery;
import cn.edu.whut.gumorming.model.song.SongRequest;
import cn.edu.whut.gumorming.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.edu.whut.gumorming.constants.OpsTypeConstants.*;


/**
 * 歌曲表(Song)表控制层
 *
 * @author GuMorming
 * @since 2024-02-21 15:12:05
 */
@Tag(name = "歌曲模块")
@RestController
@RequestMapping("/admin/song")
public class SongController {
    /**
     * 服务对象
     */
    @Autowired
    private SongService songService;

    @Operation(summary = "查看后台歌曲列表")
    @PreAuthorize("hasAuthority('client:song:list')")
    @GetMapping("/list")
    public ResponseResult<PageResult<SongBackResponse>> listSongBackVO(SongQuery songQuery) {
        return ResponseResult.ok(songService.listSongBackVO(songQuery));
    }

    // 添加歌曲
    @OpsLogger(ADD)
    @Operation(summary = "添加歌曲")
    @PostMapping("/add")
    public ResponseResult<?> addSong(@Validated @RequestBody SongRequest songRequest) {
        return songService.addSong(songRequest);
    }

    // 删除歌曲
    @OpsLogger(DELETE)
    @Operation(summary = "删除歌曲")
    @DeleteMapping("/delete")
    public ResponseResult<?> deleteSong(@RequestBody List<Integer> songIdList) {
        // todo 删除对应文件
        return songService.deleteSong(songIdList);
    }

    // 更新歌曲信息
    @OpsLogger(UPDATE)
    @Operation(summary = "更新歌曲")
    @PostMapping("/update")
    public ResponseResult<?> updateSongMsg(@Validated @RequestBody SongRequest updateSongRequest) {
        return songService.updateSong(updateSongRequest);
    }


}