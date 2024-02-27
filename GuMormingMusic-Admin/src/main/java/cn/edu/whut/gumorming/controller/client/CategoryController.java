package cn.edu.whut.gumorming.controller.client;

import cn.edu.whut.gumorming.annotation.OpsLogger;
import cn.edu.whut.gumorming.model.PageResult;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.category.CategoryBackResponse;
import cn.edu.whut.gumorming.model.category.CategoryOptionResponse;
import cn.edu.whut.gumorming.model.category.CategoryQuery;
import cn.edu.whut.gumorming.model.category.CategoryRequest;
import cn.edu.whut.gumorming.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.edu.whut.gumorming.constants.OpsTypeConstants.*;

/**
 * @author : GuMorming
 * @Project : GuMormingMusic
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2024/2/21 14:16
 * @Email : gumorming@163.com
 * @Description :
 */
@Tag(name = "分类模块")
@RestController
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/optionList")
    public ResponseResult<List<CategoryOptionResponse>> getAllCategoryItem() {
        return ResponseResult.ok(categoryService.getAllCategoryOption());
    }

    /**
     * 查看后台分类列表
     *
     * @param categoryQuery 分类查询条件
     * @return {@link CategoryBackResponse} 后台分类
     */
    @Operation(summary = "查看后台分类列表")
//    @PreAuthorize("hasAuthority('client:category:list')")
    @GetMapping("/list")
    public ResponseResult<PageResult<CategoryBackResponse>> listCategoryBackVO(CategoryQuery categoryQuery) {
        return ResponseResult.ok(categoryService.listCategoryBackVO(categoryQuery));
    }

    /**
     * 添加分类
     *
     * @param category 分类信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = ADD)
    @Operation(summary = "添加分类")
//    @PreAuthorize("hasAuthority('client:category:add')")
    @PostMapping("/add")
    public ResponseResult<?> addCategory(@Validated @RequestBody CategoryRequest category) {
        return categoryService.addCategory(category);
    }

    /**
     * 删除分类
     *
     * @param categoryIdList 分类id集合
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = DELETE)
    @Operation(summary = "删除分类")
//    @PreAuthorize("hasAuthority('client:category:delete')")
    @DeleteMapping("/delete")
    public ResponseResult<?> deleteCategory(@RequestBody List<Integer> categoryIdList) {
        return categoryService.deleteCategory(categoryIdList);
    }

    /**
     * 修改分类
     *
     * @param category 分类信息
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = UPDATE)
    @Operation(summary = "修改分类")
//    @PreAuthorize("hasAuthority('client:category:update')")
    @PutMapping("/update")
    public ResponseResult<?> updateCategory(@Validated @RequestBody CategoryRequest category) {
        return categoryService.updateCategory(category);

    }


}