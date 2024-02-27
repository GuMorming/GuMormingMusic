package cn.edu.whut.gumorming.controller.log;

import cn.edu.whut.gumorming.entity.ExceptionLog;
import cn.edu.whut.gumorming.entity.VisitLog;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.LogQuery;
import cn.edu.whut.gumorming.model.query.TaskQuery;
import cn.edu.whut.gumorming.model.response.OperationLogResponse;
import cn.edu.whut.gumorming.model.response.TaskLogResponse;
import cn.edu.whut.gumorming.service.ExceptionLogService;
import cn.edu.whut.gumorming.service.OperationLogService;
import cn.edu.whut.gumorming.service.TaskLogService;
import cn.edu.whut.gumorming.service.VisitLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "日志模块")
@RestController
@RequestMapping("/admin/log")
public class LogController {

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private ExceptionLogService exceptionLogService;

    @Autowired
    private VisitLogService visitLogService;

    @Autowired
    private TaskLogService taskLogService;

    /**
     * 查看操作日志
     *
     * @param logQuery 条件
     * @return {@link OperationLogResponse} 操作日志
     */
    @Operation(summary = "查看操作日志")
    @PreAuthorize("hasAuthority('log:operation:list')")
    @GetMapping("/operation/list")
    public ResponseResult<PageResult<OperationLogResponse>> listOperationLogVO(LogQuery logQuery) {
        return ResponseResult.ok(operationLogService.listOperationLogVO(logQuery));
    }

    /**
     * 删除操作日志
     *
     * @param logIdList 日志id集合
     * @return {@link ResponseResult<>}
     */
    @Operation(summary = "删除操作日志")
    @PreAuthorize("hasAuthority('log:operation:delete')")
    @DeleteMapping("/operation/delete")
    public ResponseResult<?> deleteOperationLog(@RequestBody List<Integer> logIdList) {
        operationLogService.removeByIds(logIdList);
        return ResponseResult.ok();
    }

    /**
     * 查看异常日志
     *
     * @param logQuery 异常日志查询条件
     * @return {@link ResponseResult<PageResult<ExceptionLog>>} 异常日志列表
     */
    @Operation(summary = "查看异常日志")
    @PreAuthorize("hasAuthority('log:exception:list')")
    @GetMapping("/exception/list")
    public ResponseResult<PageResult<ExceptionLog>> listExceptionLog(LogQuery logQuery) {
        return ResponseResult.ok(exceptionLogService.listExceptionLog(logQuery));
    }

    /**
     * 删除异常日志
     *
     * @param logIdList 日志id集合
     * @return {@link ResponseResult<>}
     */
    @Operation(summary = "删除异常日志")
    @PreAuthorize("hasAuthority('log:exception:delete')")
    @DeleteMapping("/exception/delete")
    public ResponseResult<?> deleteExceptionLog(@RequestBody List<Integer> logIdList) {
        exceptionLogService.removeByIds(logIdList);
        return ResponseResult.ok();
    }

    /**
     * 查看访问日志
     *
     * @param logQuery 访问日志查询条件
     * @return {@link ResponseResult<PageResult<VisitLog>>} 访问日志列表
     */
    @Operation(summary = "查看访问日志")
    @PreAuthorize("hasAuthority('log:visit:list')")
    @GetMapping("/visit/list")
    public ResponseResult<PageResult<VisitLog>> listVisitLog(LogQuery logQuery) {
        return ResponseResult.ok(visitLogService.listVisitLog(logQuery));
    }

    /**
     * 删除访问日志
     *
     * @param logIdList 日志id集合
     * @return {@link ResponseResult<>}
     */
    @Operation(summary = "删除访问日志")
    @PreAuthorize("hasAuthority('log:visit:delete')")
    @DeleteMapping("/visit/delete")
    public ResponseResult<?> deleteVisitLog(@RequestBody List<Integer> logIdList) {
        visitLogService.removeByIds(logIdList);
        return ResponseResult.ok();
    }

    /**
     * 查看定时任务日志
     *
     * @param taskQuery 条件
     * @return {@link PageResult< TaskLogResponse >} 后台定时任务日志
     */
    @Operation(summary = "查看定时任务日志")
    @PreAuthorize("hasAuthority('log:task:list')")
    @GetMapping("/taskLog/list")
    public ResponseResult<PageResult<TaskLogResponse>> listTaskLog(TaskQuery taskQuery) {
        return ResponseResult.ok(taskLogService.listTaskLog(taskQuery));
    }

    /**
     * 删除定时任务日志
     *
     * @param logIdList 日志id集合
     * @return {@link ResponseResult<>}
     */
    @Operation(summary = "删除定时任务的日志")
    @PreAuthorize("hasAuthority('log:task:delete')")
    @DeleteMapping("/taskLog/delete")
    public ResponseResult<?> deleteTaskLog(@RequestBody List<Integer> logIdList) {
        taskLogService.removeByIds(logIdList);
        return ResponseResult.ok();
    }

    /**
     * 清空定时任务日志
     *
     * @return {@link ResponseResult<>}
     */
    @Operation(summary = "清空定时任务日志")
    @PreAuthorize("hasAuthority('log:task:clear')")
    @DeleteMapping("/taskLog/clear")
    public ResponseResult<?> clearTaskLog() {
        taskLogService.clearTaskLog();
        return ResponseResult.ok();
    }
}