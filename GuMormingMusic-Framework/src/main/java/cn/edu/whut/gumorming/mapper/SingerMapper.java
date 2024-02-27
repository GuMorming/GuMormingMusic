package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.Singer;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 歌手表(Singer)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-21 15:28:22
 */
@Mapper
public interface SingerMapper extends OptionalBaseMapper<Singer> {

    Long selectSongCount(@Param("singerIdList") List<Integer> singerIdList);
}