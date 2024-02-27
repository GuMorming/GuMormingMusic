package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.ScheduleConstant;
import cn.edu.whut.gumorming.entity.Task;
import cn.edu.whut.gumorming.enums.TaskStatusEnum;
import cn.edu.whut.gumorming.exception.ServiceException;
import cn.edu.whut.gumorming.mapper.TaskMapper;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.TaskQuery;
import cn.edu.whut.gumorming.model.request.StatusRequest;
import cn.edu.whut.gumorming.model.request.TaskRequest;
import cn.edu.whut.gumorming.model.request.TaskRunRequest;
import cn.edu.whut.gumorming.model.response.TaskBackResponse;
import cn.edu.whut.gumorming.service.TaskService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.edu.whut.gumorming.utils.quartz.CronUtils;
import cn.edu.whut.gumorming.utils.quartz.ScheduleUtils;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.PostConstruct;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * (Task)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-20 21:47:23
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
    @Autowired
    private Scheduler scheduler;


    /**
     * 项目启动时，初始化定时器 主要是防止手动修改数据库导致未同步到定时任务处理
     * 注：不能手动修改数据库ID和任务组名，否则会导致脏数据
     */
    @PostConstruct
    public void init() throws SchedulerException {
        scheduler.clear();
        List<Task> taskList = baseMapper.selectList(null);
        for (Task task : taskList) {
            // 创建定时任务
            ScheduleUtils.createScheduleJob(scheduler, task);
        }
    }

    @Override
    public PageResult<TaskBackResponse> listTaskBackVO(TaskQuery taskQuery) {
        // 构造条件
        LambdaQueryWrapper<Task> queryWrapper = new LambdaQueryWrapper<Task>()
                .like(StringUtils.hasText(taskQuery.getKeyword()), Task::getTaskName, taskQuery.getKeyword())
                .eq(Objects.nonNull(taskQuery.getStatus()), Task::getStatus, taskQuery.getStatus())
                .like(StringUtils.hasText(taskQuery.getTaskGroup()), Task::getTaskGroup, taskQuery.getTaskGroup());
        // 分页
        Page<Task> page = new Page<>(taskQuery.getCurrent(), taskQuery.getSize());
        page(page, queryWrapper);
        // 封装VO
        List<TaskBackResponse> taskBackResponses = BeanCopyUtils.copyBeanList(page.getRecords(), TaskBackResponse.class);
        // 设置下一次执行时间
        taskBackResponses.forEach(item -> {
            if (StringUtils.hasText(item.getCronExpression())) {
                Date nextExecutionTime = CronUtils.getNextExecution(item.getCronExpression());
                item.setNextValidTime(nextExecutionTime);
            } else {
                item.setNextValidTime(null);
            }
        });
        return new PageResult<>(taskBackResponses, page.getTotal());
    }

    @Override
    public ResponseResult<?> addTask(TaskRequest task) {
        Assert.isTrue(CronUtils.isValid(task.getCronExpression()), "Cron表达式无效");
        Task newTask = BeanCopyUtils.copyBean(task, Task.class);
        // 新增定时任务
        int row = baseMapper.insert(newTask);
        // 创建定时任务
        if (row > 0) {
            ScheduleUtils.createScheduleJob(scheduler, newTask);
        }
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> updateTask(TaskRequest task) {
        Assert.isTrue(CronUtils.isValid(task.getCronExpression()), "Cron表达式无效");
        Task existTask = baseMapper.selectById(task.getId());
        Task newTask = BeanCopyUtils.copyBean(task, Task.class);
        // 更新定时任务
        int row = baseMapper.updateById(newTask);
        if (row > 0) {
            try {
                updateSchedulerJob(newTask, existTask.getTaskGroup());
            } catch (SchedulerException e) {
                throw new ServiceException("更新定时任务异常");
            }
        }
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> deleteTask(List<Integer> taskIdList) {
        List<Task> taskList = baseMapper.selectList(new LambdaQueryWrapper<Task>()
                .select(Task::getId, Task::getTaskGroup)
                .in(Task::getId, taskIdList));
        // 删除定时任务
        int row = baseMapper.delete(new LambdaQueryWrapper<Task>().in(Task::getId, taskIdList));
        if (row > 0) {
            taskList.forEach(task -> {
                try {
                    scheduler.deleteJob(ScheduleUtils.getJobKey(task.getId(), task.getTaskGroup()));
                } catch (SchedulerException e) {
                    throw new ServiceException("删除定时任务异常");
                }
            });
        }
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> updateTaskStatus(StatusRequest taskStatus) {
        Task task = baseMapper.selectById(taskStatus.getId());
        // 相同不用更新
        if (task.getStatus().equals(taskStatus.getStatus())) {
            return ResponseResult.ok();
        }
        // 更新数据库中的定时任务状态
        Task newTask = Task.builder()
                .id(taskStatus.getId())
                .status(taskStatus.getStatus())
                .build();
        int row = baseMapper.updateById(newTask);
        // 获取定时任务状态、id、任务组
        Integer status = taskStatus.getStatus();
        Integer taskId = task.getId();
        String taskGroup = task.getTaskGroup();
        if (row > 0) {
            // 更新定时任务
            try {
                if (TaskStatusEnum.RUNNING.getStatus().equals(status)) {
                    scheduler.resumeJob(ScheduleUtils.getJobKey(taskId, taskGroup));
                }
                if (TaskStatusEnum.PAUSE.getStatus().equals(status)) {
                    scheduler.pauseJob(ScheduleUtils.getJobKey(taskId, taskGroup));
                }
            } catch (SchedulerException e) {
                throw new ServiceException("更新定时任务状态异常");
            }
        }
        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> runTask(TaskRunRequest taskRun) {
        Integer taskId = taskRun.getId();
        String taskGroup = taskRun.getTaskGroup();
        // 查询定时任务
        Task task = baseMapper.selectById(taskRun.getId());
        // 设置参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstant.TASK_PROPERTIES, task);
        try {
            scheduler.triggerJob(ScheduleUtils.getJobKey(taskId, taskGroup), dataMap);
        } catch (SchedulerException e) {
            throw new ServiceException("执行定时任务异常");
        }
        return ResponseResult.ok();
    }

    /**
     * 更新任务
     *
     * @param task      任务对象
     * @param taskGroup 任务组名
     */
    public void updateSchedulerJob(Task task, String taskGroup) throws SchedulerException {
        Integer taskId = task.getId();
        // 判断是否存在
        JobKey jobKey = ScheduleUtils.getJobKey(taskId, taskGroup);
        if (scheduler.checkExists(jobKey)) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(jobKey);
        }
        ScheduleUtils.createScheduleJob(scheduler, task);
    }
}