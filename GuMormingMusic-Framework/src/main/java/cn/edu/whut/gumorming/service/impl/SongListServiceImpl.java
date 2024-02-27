package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.SongList;
import cn.edu.whut.gumorming.enums.FilePathEnum;
import cn.edu.whut.gumorming.mapper.SongListMapper;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.songList.SongListBackResponse;
import cn.edu.whut.gumorming.model.songList.SongListQuery;
import cn.edu.whut.gumorming.model.songList.SongListRequest;
import cn.edu.whut.gumorming.service.FileService;
import cn.edu.whut.gumorming.service.SongListService;
import cn.edu.whut.gumorming.strategy.context.UploadStrategyContext;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * (SongList)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-23 14:58:59
 */
@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {
    @Autowired
    private FileService fileService;
    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Override
    public PageResult<SongListBackResponse> listSongListBackVO(SongListQuery songListQuery) {
        // 构造查询条件
        LambdaQueryWrapper<SongList> queryWrapper = new LambdaQueryWrapper<SongList>()
                // 标题
                .like(StringUtils.hasText(songListQuery.getKeyword()), SongList::getTitle, songListQuery.getKeyword())
                // 分类Id
                .eq(Objects.nonNull(songListQuery.getCategoryId()), SongList::getCategoryId, songListQuery.getCategoryId());
        // 分页
        Page<SongList> page = new Page<>(songListQuery.getCurrent(), songListQuery.getSize());
        page(page, queryWrapper);
        // 封装vo
        List<SongListBackResponse> songListBackResponses = BeanCopyUtils.copyBeanList(page.getRecords(), SongListBackResponse.class);

        return new PageResult<>(songListBackResponses, page.getTotal());
    }

    @Override
    public ResponseResult<?> addSongList(SongListRequest songListRequest) {
        // 歌单名是否存在
        SongList existSongList = baseMapper.selectOne(new LambdaQueryWrapper<SongList>()
                .select(SongList::getTitle)
                .eq(SongList::getTitle, songListRequest.getTitle()));
        Assert.isNull(existSongList, songListRequest.getTitle() + "歌单名已存在");
        // 添加新歌单
        SongList newSongList = SongList.builder()
                .title(songListRequest.getTitle())
                .categoryId(songListRequest.getCategoryId())
                .pic(songListRequest.getPic())
                .introduction(songListRequest.getIntroduction())
                .build();

        baseMapper.insert(newSongList);

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> deleteSongList(List<Integer> songListIdList) {
        // 删除歌单
        baseMapper.deleteBatchIds(songListIdList);

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> updateSinger(SongListRequest songList) {
        // 歌手是否存在
        SongList existsongList = baseMapper.selectOne(new LambdaQueryWrapper<SongList>()
                .select(SongList::getId)
                .eq(SongList::getTitle, songList.getTitle()));
        Assert.isFalse(Objects.nonNull(existsongList) && !existsongList.getId().equals(existsongList.getId()),
                existsongList.getTitle() + "歌单已存在");
        // 更新角色信息
        SongList newSongList = SongList.builder()
                .id(songList.getId())
                .title(songList.getTitle())
                .pic(songList.getPic())
                .categoryId(songList.getCategoryId())
                .introduction(songList.getIntroduction())
                .build();
        baseMapper.updateById(newSongList);

        return ResponseResult.ok();
    }

    @Override
    public String uploadSongListPic(MultipartFile file) {
        // 上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.songListPic.getPath());
        // 文件模块管理
        fileService.saveFile(file, url, FilePathEnum.songListPic.getFilePath());

        return url;
    }
}