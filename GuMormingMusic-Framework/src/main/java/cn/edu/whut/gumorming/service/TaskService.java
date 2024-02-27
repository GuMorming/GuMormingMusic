package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.Task;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.TaskQuery;
import cn.edu.whut.gumorming.model.request.StatusRequest;
import cn.edu.whut.gumorming.model.request.TaskRequest;
import cn.edu.whut.gumorming.model.request.TaskRunRequest;
import cn.edu.whut.gumorming.model.response.TaskBackResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * (Task)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-20 21:47:23
 */
public interface TaskService extends IService<Task> {

    PageResult<TaskBackResponse> listTaskBackVO(TaskQuery taskQuery);

    ResponseResult<?> addTask(TaskRequest task);

    ResponseResult<?> updateTask(TaskRequest task);

    ResponseResult<?> deleteTask(List<Integer> taskIdList);

    ResponseResult<?> updateTaskStatus(StatusRequest taskStatus);

    ResponseResult<?> runTask(TaskRunRequest taskRun);
}