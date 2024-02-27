package cn.edu.whut.gumorming.quartz.task;

import cn.edu.whut.gumorming.entity.VisitLog;
import cn.edu.whut.gumorming.mapper.VisitLogMapper;
import cn.edu.whut.gumorming.utils.redis.RedisCache;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static cn.edu.whut.gumorming.constants.RedisConstants.UNIQUE_VISITOR;

/**
 * 执行定时任务
 */
@SuppressWarnings(value = "all")
@Component("timedTask")
public class TimedTask {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private VisitLogMapper visitLogMapper;

    /**
     * 清除Redis每日访问记录
     */
    public void clear() {
        redisCache.deleteObject(UNIQUE_VISITOR);
    }

    /**
     * 测试任务
     */
    public void test() {
        System.out.println("测试任务");
    }

    /**
     * 清除数据库一周前的访问日志
     */
    public void clearVistiLog() {
        DateTime endTime = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), -7));
        visitLogMapper.delete(new LambdaQueryWrapper<VisitLog>()
                .le(VisitLog::getCreateTime, endTime));
    }
}