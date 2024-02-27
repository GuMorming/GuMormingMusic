package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.VisitLog;
import cn.edu.whut.gumorming.model.response.UserViewResp;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import cn.hutool.core.date.DateTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (VisitLog)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-20 12:19:40
 */
@Mapper
public interface VisitLogMapper extends OptionalBaseMapper<VisitLog> {

    /**
     * 获取7天用户访问结果
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 用户访问结果
     */
    List<UserViewResp> selectUserViewList(@Param("startTime") DateTime startTime, @Param("endTime") DateTime endTime);
}