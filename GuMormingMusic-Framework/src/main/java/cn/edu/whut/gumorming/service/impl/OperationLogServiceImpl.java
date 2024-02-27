package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.OperationLog;
import cn.edu.whut.gumorming.mapper.OperationLogMapper;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.query.LogQuery;
import cn.edu.whut.gumorming.model.response.OperationLogResponse;
import cn.edu.whut.gumorming.service.OperationLogService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * (OperationLog)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-20 12:13:18
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {


    @Override
    public void saveOperationLog(OperationLog operationLog) {
        // 保存操作日志
        baseMapper.insert(operationLog);
    }

    @Override
    public PageResult<OperationLogResponse> listOperationLogVO(LogQuery logQuery) {
        // 构造条件
        LambdaQueryWrapper<OperationLog> queryWrapper = new LambdaQueryWrapper<OperationLog>()
                .like(StringUtils.hasText(logQuery.getOptModule()), OperationLog::getModule, logQuery.getOptModule())
                .or()
                .like(StringUtils.hasText(logQuery.getKeyword()), OperationLog::getDescription, logQuery.getKeyword())
                .orderByDesc(OperationLog::getCreateTime);
        // 分页
        Page<OperationLog> page = new Page<>(logQuery.getCurrent(), logQuery.getSize());
        page(page, queryWrapper);
        // 封装VO
        List<OperationLogResponse> operationLogResponses = BeanCopyUtils.copyBeanList(page.getRecords(), OperationLogResponse.class);

        return new PageResult<>(operationLogResponses, page.getTotal());
    }
}