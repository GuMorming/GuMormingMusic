package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.Category;
import cn.edu.whut.gumorming.mapper.CategoryMapper;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.category.CategoryBackResponse;
import cn.edu.whut.gumorming.model.category.CategoryOptionResponse;
import cn.edu.whut.gumorming.model.category.CategoryQuery;
import cn.edu.whut.gumorming.model.category.CategoryRequest;
import cn.edu.whut.gumorming.service.CategoryService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * (Category)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-21 14:12:57
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<CategoryOptionResponse> getAllCategoryOption() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<Category>().select(Category::getId, Category::getCategoryName);

        List<Category> categories = baseMapper.selectList(queryWrapper);

        return BeanCopyUtils.copyBeanList(categories, CategoryOptionResponse.class);
    }

    @Override
    public PageResult<CategoryBackResponse> listCategoryBackVO(CategoryQuery categoryQuery) {
        // 构造条件
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<Category>()
                .like(StringUtils.hasText(categoryQuery.getKeyword()), Category::getCategoryName, categoryQuery.getKeyword());
        // 分页
        Page<Category> page = new Page<>(categoryQuery.getCurrent(), categoryQuery.getSize());
        page(page, queryWrapper);
        // 封装vo
        List<CategoryBackResponse> categoryBackResponses = BeanCopyUtils.copyBeanList(page.getRecords(), CategoryBackResponse.class);

        return new PageResult<>(categoryBackResponses, page.getTotal());
    }

    @Override
    public ResponseResult<?> addCategory(CategoryRequest category) {
        // 分类是否存在
        Category existCategory = baseMapper.selectOne(new LambdaQueryWrapper<Category>()
                .select(Category::getId)
                .eq(Category::getCategoryName, category.getCategoryName()));
        Assert.isNull(existCategory, category.getCategoryName() + "分类已存在");
        // 添加新分类
        Category newCategory = Category.builder()
                .categoryName(category.getCategoryName())
                .build();
        baseMapper.insert(newCategory);

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> deleteCategory(List<Integer> categoryIdList) {
        return null;
    }

    @Override
    public ResponseResult<?> updateCategory(CategoryRequest category) {
        return null;
    }
}