package cn.edu.whut.gumorming.controller.system;


import cn.edu.whut.gumorming.annotation.OpsLogger;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.MenuQuery;
import cn.edu.whut.gumorming.model.request.MenuRequest;
import cn.edu.whut.gumorming.model.response.MenuOptionResponse;
import cn.edu.whut.gumorming.model.response.MenuResponse;
import cn.edu.whut.gumorming.model.response.MenuTreeResponse;
import cn.edu.whut.gumorming.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.edu.whut.gumorming.constants.OpsTypeConstants.*;
// TODO 修改和更新时左边菜单栏不会刷新

/**
 * (Menu)表控制层
 *
 * @author GuMorming
 * @since 2024-02-18 22:49:53
 */
@Tag(name = "菜单模块")

@RestController
@RequestMapping("/admin/menu")
public class MenuController {
    /**
     * 服务对象
     */
    @Autowired
    private MenuService menuService;

    /**
     * 查看菜单列表
     *
     * @param menuQuery 菜单查询条件
     * @return {@link MenuTreeResponse} 菜单列表
     */
    @Operation(summary = "查看菜单列表")
    @PreAuthorize("hasAuthority('system:menu:list')")
    @GetMapping("/list")
    public ResponseResult<List<MenuResponse>> listMenuVO(MenuQuery menuQuery) {
        return ResponseResult.ok(menuService.listMenuVO(menuQuery));
    }

    /**
     * 添加菜单
     *
     * @return {@link ResponseResult <>}
     */
    @OpsLogger(value = ADD)
    @Operation(summary = "添加菜单")
    @PreAuthorize("hasAuthority('system:menu:add')")
    @PostMapping("/add")
    public ResponseResult<?> addMenu(@Validated @RequestBody MenuRequest menuRequest) {
        return menuService.addMenu(menuRequest);
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单id
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = DELETE)
    @Operation(summary = "删除菜单")
    @PreAuthorize("hasAuthority('system:menu:delete')")
    @DeleteMapping("/delete/{menuId}")
    public ResponseResult<?> deleteMenu(@PathVariable("menuId") Integer menuId) {

        return menuService.deleteMenu(menuId);
    }

    /**
     * 修改菜单
     *
     * @return {@link ResponseResult<>}
     */
    @OpsLogger(value = UPDATE)
    @Operation(summary = "修改菜单")
    @PreAuthorize("hasAuthority('system:menu:update')")
    @PutMapping("/update")
    public ResponseResult<?> updateMenu(@Validated @RequestBody MenuRequest menuRequest) {

        return menuService.updateMenu(menuRequest);
    }

    /**
     * 获取菜单下拉树
     *
     * @return {@link MenuTreeResponse} 菜单树
     */
    @Operation(summary = "获取菜单下拉树")
    @PreAuthorize("hasAuthority('system:role:list')")
    @GetMapping("/getMenuTree")
    public ResponseResult<List<MenuTreeResponse>> listMenuTree() {
        return ResponseResult.ok(menuService.listMenuTree());
    }

    /**
     * 获取菜单选项树
     *
     * @return {@link MenuOptionResponse} 菜单下拉树
     */
    @Operation(summary = "获取菜单选项树")
    @PreAuthorize("hasAuthority('system:menu:list')")
    @GetMapping("/getMenuOptions")
    public ResponseResult<List<MenuOptionResponse>> listMenuOption() {
        return ResponseResult.ok(menuService.listMenuOption());
    }

    /**
     * 编辑菜单
     *
     * @param menuId 菜单id
     * @return {@link MenuRequest} 菜单
     */
    @Operation(summary = "编辑菜单")
    @PreAuthorize("hasAuthority('system:menu:edit')")
    @GetMapping("/edit/{menuId}")
    public ResponseResult<MenuRequest> editMenu(@PathVariable("menuId") Integer menuId) {
        return ResponseResult.ok(menuService.editMenu(menuId));
    }


}