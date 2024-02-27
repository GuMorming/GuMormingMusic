package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.Task;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Task)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-20 21:47:23
 */
@Mapper
public interface TaskMapper extends OptionalBaseMapper<Task> {

}