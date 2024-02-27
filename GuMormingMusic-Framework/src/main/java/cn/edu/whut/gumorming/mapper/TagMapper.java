package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.Tag;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Tag)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-21 14:13:09
 */
@Mapper
public interface TagMapper extends OptionalBaseMapper<Tag> {

}