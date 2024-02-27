package cn.edu.whut.gumorming.utils;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.mapper
 * @createTime : 2024/2/18 14:53
 * @Email : gumorming@163.com
 * @Description : 将BaseMapper使用Optional封装
 */

public interface OptionalBaseMapper<T> extends BaseMapper<T> {
    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */

    default Optional<T> getById(Serializable id) {
        T t = selectById(id);
        return t == null ? Optional.empty() : Optional.of(t);
    }

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */

    default Optional<List<T>> getBatchIds(@Param(Constants.COLL) Collection<? extends Serializable> idList) {
        List<T> ts = selectBatchIds(idList);
        return ts.isEmpty() ? Optional.empty() : Optional.of(ts);
    }

    /**
     * 根据 entity 条件，查询一条记录
     * <p>查询一条记录，例如 qw.last("limit 1") 限制取一条记录, 注意：多条数据会报异常</p>
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default Optional<T> getOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        T t = selectOne(queryWrapper, true);
        return t == null ? Optional.empty() : Optional.of(t);
    }


    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default Optional<Long> getCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        return Optional.ofNullable(selectCount(queryWrapper));
    }

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default Optional<List<T>> getList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        List<T> ts = selectList(queryWrapper);
        return ts.isEmpty() ? Optional.empty() : Optional.of(ts);
    }

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类
     */
    default Optional<List<Map<String, Object>>> getMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        List<Map<String, Object>> maps = selectMaps(queryWrapper);
        return maps.isEmpty() ? Optional.empty() : Optional.of(maps);
    }

}