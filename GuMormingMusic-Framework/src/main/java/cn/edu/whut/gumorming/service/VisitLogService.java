package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.VisitLog;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.query.LogQuery;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (VisitLog)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-20 12:19:40
 */
public interface VisitLogService extends IService<VisitLog> {

    void saveVisitLog(VisitLog visitLog);

    PageResult<VisitLog> listVisitLog(LogQuery logQuery);
}