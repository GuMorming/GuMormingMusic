package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.Category;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.category.CategoryBackResponse;
import cn.edu.whut.gumorming.model.category.CategoryOptionResponse;
import cn.edu.whut.gumorming.model.category.CategoryQuery;
import cn.edu.whut.gumorming.model.category.CategoryRequest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * (Category)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-21 14:12:57
 */
public interface CategoryService extends IService<Category> {

    List<CategoryOptionResponse> getAllCategoryOption();

    PageResult<CategoryBackResponse> listCategoryBackVO(CategoryQuery categoryQuery);

    ResponseResult<?> addCategory(CategoryRequest category);

    ResponseResult<?> deleteCategory(List<Integer> categoryIdList);

    ResponseResult<?> updateCategory(CategoryRequest category);
}