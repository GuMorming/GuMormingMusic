package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.SongList;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.songList.SongListBackResponse;
import cn.edu.whut.gumorming.model.songList.SongListQuery;
import cn.edu.whut.gumorming.model.songList.SongListRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (SongList)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-23 14:58:59
 */
public interface SongListService extends IService<SongList> {

    PageResult<SongListBackResponse> listSongListBackVO(SongListQuery songListQuery);

    ResponseResult<?> addSongList(SongListRequest songListRequest);

    ResponseResult<?> deleteSongList(List<Integer> songListIdList);

    ResponseResult<?> updateSinger(SongListRequest updateSongListRequest);

    String uploadSongListPic(MultipartFile file);
}