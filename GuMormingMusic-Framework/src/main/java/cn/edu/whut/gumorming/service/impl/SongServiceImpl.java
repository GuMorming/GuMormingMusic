package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.Song;
import cn.edu.whut.gumorming.mapper.SongMapper;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.song.SongBackResponse;
import cn.edu.whut.gumorming.model.song.SongQuery;
import cn.edu.whut.gumorming.model.song.SongRequest;
import cn.edu.whut.gumorming.service.SongService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 歌曲表(Song)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-21 15:12:07
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {

    @Override
    public PageResult<SongBackResponse> listSongBackVO(SongQuery songQuery) {
        // 构造条件
        LambdaQueryWrapper<Song> queryWrapper = new LambdaQueryWrapper<Song>()
                // 歌曲名 todo 分类 歌手
                .like(StringUtils.hasText(songQuery.getName()), Song::getName, songQuery.getName());
        // 分页
        Page<Song> page = new Page<>(songQuery.getCurrent(), songQuery.getSize());
        page(page, queryWrapper);
        // 封装VO
        List<SongBackResponse> songBackResponses = BeanCopyUtils.copyBeanList(page.getRecords(), SongBackResponse.class);

        return new PageResult<>(songBackResponses, page.getTotal());
    }

    @Override
    public ResponseResult<?> addSong(SongRequest addSongRequest) {
        return null;
    }

    @Override
    public ResponseResult<?> deleteSong(List<Integer> id) {
        return null;
    }

    @Override
    public ResponseResult<?> updateSong(SongRequest updateSongRequest) {
        return null;
    }

    @Override
    public String uploadSongPic(MultipartFile file) {
        return null;
    }

    @Override
    public String uploadSong(MultipartFile file) {
        return null;
    }


}