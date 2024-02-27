package cn.edu.whut.gumorming.factory;

import cn.edu.whut.gumorming.entity.ExceptionLog;
import cn.edu.whut.gumorming.entity.OperationLog;
import cn.edu.whut.gumorming.entity.VisitLog;
import cn.edu.whut.gumorming.service.ExceptionLogService;
import cn.edu.whut.gumorming.service.OperationLogService;
import cn.edu.whut.gumorming.service.VisitLogService;
import cn.hutool.extra.spring.SpringUtil;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 */
public class AsyncFactory {

    /**
     * 记录操作日志
     *
     * @param operationLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOperation(OperationLog operationLog) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtil.getBean(OperationLogService.class).saveOperationLog(operationLog);
            }
        };
    }

    /**
     * 记录异常日志
     *
     * @param exceptionLog 异常日志信息
     * @return 任务task
     */
    public static TimerTask recordException(ExceptionLog exceptionLog) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtil.getBean(ExceptionLogService.class).saveExceptionLog(exceptionLog);
            }
        };
    }

    /**
     * 记录访问日志
     *
     * @param visitLog 访问日志信息
     * @return 任务task
     */
    public static TimerTask recordVisit(VisitLog visitLog) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtil.getBean(VisitLogService.class).saveVisitLog(visitLog);
            }
        };
    }
}