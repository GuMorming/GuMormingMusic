package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.SiteConfig;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (SiteConfig)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-21 10:36:56
 */
@Mapper
public interface SiteConfigMapper extends OptionalBaseMapper<SiteConfig> {

}