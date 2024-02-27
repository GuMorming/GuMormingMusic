package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.ExceptionLog;
import cn.edu.whut.gumorming.mapper.ExceptionLogMapper;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.query.LogQuery;
import cn.edu.whut.gumorming.service.ExceptionLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * (ExceptionLog)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-20 12:19:23
 */
@Service
public class ExceptionLogServiceImpl extends ServiceImpl<ExceptionLogMapper, ExceptionLog> implements ExceptionLogService {

    @Override
    public void saveExceptionLog(ExceptionLog exceptionLog) {
        baseMapper.insert(exceptionLog);
    }

    @Override
    public PageResult<ExceptionLog> listExceptionLog(LogQuery logQuery) {
        // 构造条件
        LambdaQueryWrapper<ExceptionLog> queryWrapper = new LambdaQueryWrapper<ExceptionLog>()
                .orderByDesc(ExceptionLog::getCreateTime)
                .like(StringUtils.hasText(logQuery.getOptModule()), ExceptionLog::getModule, logQuery.getOptModule())
                .or()
                .like(StringUtils.hasText(logQuery.getKeyword()), ExceptionLog::getDescription, logQuery.getKeyword());
        // 分页
        Page<ExceptionLog> page = new Page<>(logQuery.getCurrent(), logQuery.getSize());
        page(page, queryWrapper);

        return new PageResult<>(page.getRecords(), page.getTotal());

    }
}