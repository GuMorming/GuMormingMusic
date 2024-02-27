package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.TaskLog;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.query.TaskQuery;
import cn.edu.whut.gumorming.model.response.TaskLogResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (TaskLog)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-20 12:29:48
 */
public interface TaskLogService extends IService<TaskLog> {

    PageResult<TaskLogResponse> listTaskLog(TaskQuery taskQuery);

    void clearTaskLog();
}