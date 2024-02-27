package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.OperationLog;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.query.LogQuery;
import cn.edu.whut.gumorming.model.response.OperationLogResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (OperationLog)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-20 12:13:18
 */
public interface OperationLogService extends IService<OperationLog> {

    void saveOperationLog(OperationLog operationLog);

    PageResult<OperationLogResponse> listOperationLogVO(LogQuery logQuery);
}