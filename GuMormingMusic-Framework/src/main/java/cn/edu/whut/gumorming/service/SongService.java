package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.Song;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.song.SongBackResponse;
import cn.edu.whut.gumorming.model.song.SongQuery;
import cn.edu.whut.gumorming.model.song.SongRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 歌曲表(Song)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-21 15:12:07
 */
public interface SongService extends IService<Song> {
    PageResult<SongBackResponse> listSongBackVO(SongQuery songQuery);

    ResponseResult<?> addSong(SongRequest addSongRequest);

    ResponseResult<?> deleteSong(List<Integer> id);

    ResponseResult<?> updateSong(SongRequest updateSongRequest);


    String uploadSongPic(MultipartFile file);

    String uploadSong(MultipartFile file);
}