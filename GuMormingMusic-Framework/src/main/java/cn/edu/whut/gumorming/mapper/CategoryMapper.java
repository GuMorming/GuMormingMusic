package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.Category;
import cn.edu.whut.gumorming.model.response.CategoryHomeResponse;
import cn.edu.whut.gumorming.utils.OptionalBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Category)表数据库访问层
 *
 * @author GuMorming
 * @since 2024-02-21 14:12:57
 */
@Mapper
public interface CategoryMapper extends OptionalBaseMapper<Category> {

    List<CategoryHomeResponse> selectCategoryHomeList();
}