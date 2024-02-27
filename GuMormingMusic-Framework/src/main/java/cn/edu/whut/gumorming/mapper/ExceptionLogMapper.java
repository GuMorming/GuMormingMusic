package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.ExceptionLog;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (ExceptionLog)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-20 12:19:23
 */
@Mapper
public interface ExceptionLogMapper extends OptionalBaseMapper<ExceptionLog> {

}