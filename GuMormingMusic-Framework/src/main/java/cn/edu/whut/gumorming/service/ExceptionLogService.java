package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.ExceptionLog;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.query.LogQuery;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (ExceptionLog)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-20 12:19:23
 */
public interface ExceptionLogService extends IService<ExceptionLog> {

    void saveExceptionLog(ExceptionLog exceptionLog);

    PageResult<ExceptionLog> listExceptionLog(LogQuery logQuery);
}