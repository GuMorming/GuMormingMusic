package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.TaskLog;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (TaskLog)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-20 12:29:48
 */
@Mapper
public interface TaskLogMapper extends OptionalBaseMapper<TaskLog> {

}