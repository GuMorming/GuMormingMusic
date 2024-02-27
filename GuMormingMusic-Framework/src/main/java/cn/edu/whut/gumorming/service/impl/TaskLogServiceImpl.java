package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.TaskLog;
import cn.edu.whut.gumorming.mapper.TaskLogMapper;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.query.TaskQuery;
import cn.edu.whut.gumorming.model.response.TaskLogResponse;
import cn.edu.whut.gumorming.service.TaskLogService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * (TaskLog)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-20 12:29:48
 */
@Service
public class TaskLogServiceImpl extends ServiceImpl<TaskLogMapper, TaskLog> implements TaskLogService {

    @Override
    public PageResult<TaskLogResponse> listTaskLog(TaskQuery taskQuery) {
        // 构造条件
        LambdaQueryWrapper<TaskLog> queryWrapper = new LambdaQueryWrapper<TaskLog>()
                .orderByDesc(TaskLog::getCreateTime)
                .like(StringUtils.hasText(taskQuery.getKeyword()), TaskLog::getTaskName, taskQuery.getKeyword())
                .like(StringUtils.hasText(taskQuery.getTaskGroup()), TaskLog::getTaskGroup, taskQuery.getTaskGroup())
                .eq(Objects.nonNull(taskQuery.getStatus()), TaskLog::getStatus, taskQuery.getStatus());
        // 分页
        Page<TaskLog> page = new Page<>(taskQuery.getCurrent(), taskQuery.getSize());
        page(page, queryWrapper);
        // 封装VO
        List<TaskLogResponse> taskLogResponses = BeanCopyUtils.copyBeanList(page.getRecords(), TaskLogResponse.class);

        return new PageResult<>(taskLogResponses, page.getTotal());
    }

    @Override
    public void clearTaskLog() {
        baseMapper.delete(null);
    }
}