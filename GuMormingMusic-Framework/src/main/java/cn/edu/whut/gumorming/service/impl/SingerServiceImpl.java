package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.Singer;
import cn.edu.whut.gumorming.enums.FilePathEnum;
import cn.edu.whut.gumorming.mapper.SingerMapper;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.singer.SingerQuery;
import cn.edu.whut.gumorming.model.singer.SingerRequest;
import cn.edu.whut.gumorming.service.FileService;
import cn.edu.whut.gumorming.service.SingerService;
import cn.edu.whut.gumorming.strategy.context.UploadStrategyContext;
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
 * 歌手表(Singer)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-21 15:28:22
 */
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {
    @Autowired
    private FileService fileService;
    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Override
    public PageResult<Singer> listSingerBackVO(SingerQuery singerQuery) {
        // 构造查询条件
        LambdaQueryWrapper<Singer> queryWrapper = new LambdaQueryWrapper<Singer>()
                // 姓名
                .like(StringUtils.hasText(singerQuery.getKeyword()), Singer::getName, singerQuery.getKeyword())
                .eq(Objects.nonNull(singerQuery.getSex()), Singer::getSex, singerQuery.getSex())
                .orderByAsc(Singer::getId);
        // 分页
        Page<Singer> page = new Page<>(singerQuery.getCurrent(), singerQuery.getSize());
        page(page, queryWrapper);
        //todo 封装vo

        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public ResponseResult<?> addSinger(SingerRequest singer) {
        // 歌手是否存在
        Singer existSinger = baseMapper.selectOne(new LambdaQueryWrapper<Singer>()
                .select(Singer::getId)
                .eq(Singer::getName, singer.getName()));
        Assert.isNull(existSinger, singer.getName() + "角色名已存在");
        // 添加新歌手
        Singer newSinger = Singer.builder()
                .name(singer.getName())
                .birth(singer.getBirth())
                .location(singer.getLocation())
                .introduction(singer.getIntroduction())
                .sex(singer.getSex())
                .pic(singer.getPic()).build();
        baseMapper.insert(newSinger);

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> deleteSinger(List<Integer> singerIdList) {
        // 歌手是否有歌曲
        Long count = baseMapper.selectSongCount(singerIdList);
        Assert.isFalse(count > 0, "该歌手已上传歌曲");
        // 删除歌手
        baseMapper.deleteBatchIds(singerIdList);

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> updateSinger(SingerRequest singer) {
        // 歌手是否存在
        Singer existSinger = baseMapper.selectOne(new LambdaQueryWrapper<Singer>()
                .select(Singer::getId)
                .eq(Singer::getName, singer.getName()));
        Assert.isFalse(Objects.nonNull(existSinger) && !existSinger.getId().equals(singer.getId()),
                singer.getName() + "歌手已存在");
        // 更新角色信息
        Singer newSinger = Singer.builder()
                .id(singer.getId())
                .name(singer.getName())
                .pic(singer.getPic())
                .sex(singer.getSex())
                .birth(singer.getBirth())
                .location(singer.getLocation())
                .introduction(singer.getIntroduction())
                .build();
        baseMapper.updateById(newSinger);

        return ResponseResult.ok();
    }

    @Override
    public String uploadSingerPic(MultipartFile file) {
        // 上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.singerPic.getPath());
        // todo 文件模块管理
        fileService.saveFile(file, url, FilePathEnum.singerPic.getFilePath());

        return url;
    }
}