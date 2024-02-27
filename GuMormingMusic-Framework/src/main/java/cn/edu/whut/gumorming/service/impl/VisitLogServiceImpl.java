package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.VisitLog;
import cn.edu.whut.gumorming.mapper.VisitLogMapper;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.query.LogQuery;
import cn.edu.whut.gumorming.service.VisitLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * (VisitLog)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-20 12:19:40
 */
@Service
public class VisitLogServiceImpl extends ServiceImpl<VisitLogMapper, VisitLog> implements VisitLogService {

    @Override
    public void saveVisitLog(VisitLog visitLog) {
        baseMapper.insert(visitLog);
    }

    @Override
    public PageResult<VisitLog> listVisitLog(LogQuery logQuery) {
        // 构造条件
        LambdaQueryWrapper<VisitLog> queryWrapper = new LambdaQueryWrapper<VisitLog>()
                .orderByDesc(VisitLog::getCreateTime)
                .like(StringUtils.hasText(logQuery.getKeyword()), VisitLog::getPage, logQuery.getKeyword());
        // 分页
        Page<VisitLog> page = new Page<>(logQuery.getCurrent(), logQuery.getSize());
        page(page, queryWrapper);

        return new PageResult<>(page.getRecords(), page.getTotal());
    }
}