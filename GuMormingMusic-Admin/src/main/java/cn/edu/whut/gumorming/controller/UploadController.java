package cn.edu.whut.gumorming.controller;


import cn.edu.whut.gumorming.annotation.OpsLogger;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.service.SingerService;
import cn.edu.whut.gumorming.service.SongListService;
import cn.edu.whut.gumorming.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static cn.edu.whut.gumorming.constants.OpsTypeConstants.UPLOAD;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/10 14:30
 * @Email : gumorming@163.com
 * @Description :
 */
@Tag(name = "上传模块")
@RestController
@RequestMapping("/admin/upload")
public class UploadController {

    @Autowired
    private SingerService singerService;

    @Autowired
    private SongListService songListService;

    @Autowired
    private SongService songService;


    @OpsLogger(UPLOAD)
    @Operation(summary = "上传歌手图片")
    @PostMapping("/singerPic")
    public ResponseResult<String> uploadSingerPic(MultipartFile file) {
        // 返回url
        return ResponseResult.ok(singerService.uploadSingerPic(file));
    }

    @OpsLogger(UPLOAD)
    @Operation(summary = "上传歌单图片")
    @PostMapping("/songListPic")
    public ResponseResult<String> uploadSongListPic(MultipartFile file) {
        // 返回url
        return ResponseResult.ok(songListService.uploadSongListPic(file));
    }

    @OpsLogger(UPLOAD)
    @Operation(summary = "上传歌曲图片")
    @PostMapping("/songPic")
    public ResponseResult<String> uploadSongPic(MultipartFile file) {
        // 返回url
        return ResponseResult.ok(songService.uploadSongPic(file));
    }

    @OpsLogger(UPLOAD)
    @Operation(summary = "上传歌曲资源")
    @PostMapping("/song")
    public ResponseResult<String> uploadSong(MultipartFile file) {
        // 返回url
        return ResponseResult.ok(songService.uploadSong(file));
    }
}