package cn.edu.whut.gumorming.quartz.execution;


import cn.edu.whut.gumorming.entity.Task;
import cn.edu.whut.gumorming.utils.quartz.TaskInvokeUtils;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, Task task) throws Exception {
        TaskInvokeUtils.invokeMethod(task);
    }
}