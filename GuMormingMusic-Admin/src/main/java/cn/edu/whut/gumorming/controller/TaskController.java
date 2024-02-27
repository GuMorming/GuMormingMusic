package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.OpsLogger;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.TaskQuery;
import cn.edu.whut.gumorming.model.request.StatusRequest;
import cn.edu.whut.gumorming.model.request.TaskRequest;
import cn.edu.whut.gumorming.model.request.TaskRunRequest;
import cn.edu.whut.gumorming.model.response.TaskBackResponse;
import cn.edu.whut.gumorming.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.edu.whut.gumorming.constants.OpsTypeConstants.*;

/**
 * 定时任务控制器
 */
@Tag(name = "定时任务模块")
@RestController
@RequestMapping("/admin/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 查看定时任务列表
     *
     * @param taskQuery 任务查询条件
     * @return {@link TaskBackResponse} 定时任务列表
     */
    @Operation(summary = "查看定时任务列表")
    @PreAuthorize("hasAuthority('monitor:task:list')")
    @GetMapping("/list")
    public ResponseResult<PageResult<TaskBackResponse>> listTaskBackVO(TaskQuery taskQuery) {
        return ResponseResult.ok(taskService.listTaskBackVO(taskQuery));
    }

    /**
     * 添加定时任务
     *
     * @param task 定时任务信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = ADD)
    @Operation(summary = "添加定时任务")
    @PreAuthorize("hasAuthority('monitor:task:add')")
    @PostMapping("/add")
    public ResponseResult<?> addTask(@Validated @RequestBody TaskRequest task) {
        return taskService.addTask(task);

    }

    /**
     * 修改定时任务
     *
     * @param task 定时任务信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = UPDATE)
    @Operation(summary = "修改定时任务")
    @PreAuthorize("hasAuthority('monitor:task:update')")
    @PutMapping("/update")
    public ResponseResult<?> updateTask(@Validated @RequestBody TaskRequest task) {
        return taskService.updateTask(task);

    }

    /**
     * 删除定时任务
     *
     * @param taskIdList 定时任务id集合
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = DELETE)
    @Operation(summary = "删除定时任务")
    @PreAuthorize("hasAuthority('monitor:task:delete')")
    @DeleteMapping("/delete")
    public ResponseResult<?> deleteTask(@RequestBody List<Integer> taskIdList) {
        return taskService.deleteTask(taskIdList);

    }

    /**
     * 修改定时任务状态
     *
     * @param taskStatus 定时任务状态
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = UPDATE)
    @Operation(summary = "修改定时任务状态")
    @PreAuthorize("hasAnyAuthority('monitor:task:update', 'monitor:task:status')")
    @PutMapping("/changeStatus")
    public ResponseResult<?> updateTaskStatus(@Validated @RequestBody StatusRequest taskStatus) {
        return taskService.updateTaskStatus(taskStatus);
    }

    /**
     * 执行定时任务
     *
     * @param taskRun 运行定时任务
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = UPDATE)
    @Operation(summary = "执行定时任务")
    @PreAuthorize("hasAuthority('monitor:task:run')")
    @PutMapping("/run")
    public ResponseResult<?> runTask(@RequestBody TaskRunRequest taskRun) {
        return taskService.runTask(taskRun);
    }

}