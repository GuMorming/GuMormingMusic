package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.Song;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 歌曲表(Song)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-21 15:12:07
 */
@Mapper
public interface SongMapper extends OptionalBaseMapper<Song> {

}