package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.Singer;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.singer.SingerQuery;
import cn.edu.whut.gumorming.model.singer.SingerRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 歌手表(Singer)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-21 15:28:22
 */
public interface SingerService extends IService<Singer> {

    PageResult<Singer> listSingerBackVO(SingerQuery singerQuery);

    ResponseResult<?> addSinger(SingerRequest singerRequest);

    ResponseResult<?> deleteSinger(List<Integer> singerIdList);

    ResponseResult<?> updateSinger(SingerRequest updateSingerRequest);

    String uploadSingerPic(MultipartFile file);
}