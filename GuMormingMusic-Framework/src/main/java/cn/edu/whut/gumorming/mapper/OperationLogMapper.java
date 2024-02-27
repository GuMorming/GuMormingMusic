package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.OperationLog;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (OperationLog)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-20 12:13:18
 */
@Mapper
public interface OperationLogMapper extends OptionalBaseMapper<OperationLog> {

}