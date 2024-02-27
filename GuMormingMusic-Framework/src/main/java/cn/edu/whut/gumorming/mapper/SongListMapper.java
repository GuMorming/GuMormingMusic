package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.SongList;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (SongList)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-23 14:58:59
 */
@Mapper
public interface SongListMapper extends OptionalBaseMapper<SongList> {

    Long selectSongListSongCount(List<Integer> songListIdList);
}