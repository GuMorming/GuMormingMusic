package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.File;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (File)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-21 10:50:04
 */
@Mapper
public interface FileMapper extends OptionalBaseMapper<File> {

}