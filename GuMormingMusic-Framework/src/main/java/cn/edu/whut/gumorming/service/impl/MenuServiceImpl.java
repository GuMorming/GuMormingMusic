package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.CommonConstant;
import cn.edu.whut.gumorming.entity.Menu;
import cn.edu.whut.gumorming.entity.RoleMenu;
import cn.edu.whut.gumorming.mapper.MenuMapper;
import cn.edu.whut.gumorming.mapper.RoleMenuMapper;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.MenuQuery;
import cn.edu.whut.gumorming.model.request.MenuRequest;
import cn.edu.whut.gumorming.model.response.MenuOptionResponse;
import cn.edu.whut.gumorming.model.response.MenuResponse;
import cn.edu.whut.gumorming.model.response.MenuTreeResponse;
import cn.edu.whut.gumorming.service.MenuService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * (Menu)表服务实现类
 *
 * @author GuMorming
 * @since 2024-02-18 22:49:53
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<MenuResponse> listMenuVO(MenuQuery menuQuery) {
        // 构建条件
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<Menu>()
                // 按排序数升序
                .orderByAsc(Menu::getOrderNum)
                // 若有禁用查询参数
                .eq(Objects.nonNull(menuQuery.getIsDisable()), Menu::getIsDisable, menuQuery.getIsDisable())
                // 若有菜单名查询参数
                .like(StringUtils.hasText(menuQuery.getKeyword()), Menu::getMenuName, menuQuery.getKeyword());
        // 查询当前菜单列表
        List<Menu> menus = getBaseMapper().selectList(queryWrapper);
        List<MenuResponse> menuResponseList = BeanCopyUtils.copyBeanList(menus, MenuResponse.class);
        // 当前菜单id列表
        Set<Integer> menuIdList = menuResponseList.stream()
                .map(MenuResponse::getId)
                .collect(Collectors.toSet());
        return menuResponseList.stream()
                .map(menuVO -> {
                    Integer parentId = menuVO.getParentId();
                    // parentId不在当前菜单id列表，说明为父级菜单id，根据此id作为递归的开始条件节点
                    if (!menuIdList.contains(parentId)) {
                        menuIdList.add(parentId);
                        return recurMenuList(parentId, menuResponseList);
                    }
                    return new ArrayList<MenuResponse>();
                }).collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
    }

    @Override
    public ResponseResult<?> addMenu(MenuRequest menu) {
        // 名称是否存在
        Menu existMenu = getBaseMapper().selectOne(new LambdaQueryWrapper<Menu>()
                .select(Menu::getId)
                .eq(Menu::getMenuName, menu.getMenuName()));
        Assert.isNull(existMenu, menu.getMenuName() + "菜单已存在");
        Menu newMenu = BeanCopyUtils.copyBean(menu, Menu.class);
        baseMapper.insert(newMenu);

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> deleteMenu(Integer menuId) {
        // 菜单下是否存在子菜单
        Long menuCount = getBaseMapper().selectCount(new LambdaQueryWrapper<Menu>()
                .eq(Menu::getParentId, menuId));
        Assert.isFalse(menuCount > 0, "存在子菜单");
        // 菜单是否已分配
        Long roleCount = roleMenuMapper.selectCount(new LambdaQueryWrapper<RoleMenu>()
                .eq(RoleMenu::getMenuId, menuId));
        Assert.isFalse(roleCount > 0, "菜单已分配");
        // 删除菜单
        getBaseMapper().deleteById(menuId);

        return ResponseResult.ok();
    }

    @Override
    public ResponseResult<?> updateMenu(MenuRequest menu) {
        // 名称是否存在
        Menu existMenu = getBaseMapper().selectOne(
                new LambdaQueryWrapper<Menu>()
                        .select(Menu::getId)
                        .eq(Menu::getMenuName, menu.getMenuName()));
        Assert.isFalse(Objects.nonNull(existMenu) && !existMenu.getId().equals(menu.getId()),
                menu.getMenuName() + "菜单已存在");
        // 更新菜单
        Menu newMenu = BeanCopyUtils.copyBean(menu, Menu.class);
        baseMapper.updateById(newMenu);

        return ResponseResult.ok();
    }

    @Override
    public List<MenuTreeResponse> listMenuTree() {
        List<MenuTreeResponse> menuTreeRespList = baseMapper.selectMenuTree();
        return recurMenuTreeList(CommonConstant.PARENT_ID, menuTreeRespList);

    }

    @Override
    public List<MenuOptionResponse> listMenuOption() {
        List<MenuOptionResponse> menuOptionList = getBaseMapper().selectMenuOptions();
        return recurMenuOptionList(CommonConstant.PARENT_ID, menuOptionList);
    }

    @Override
    public MenuRequest editMenu(Integer menuId) {
        Menu menu = getBaseMapper().selectById(menuId);
        return BeanCopyUtils.copyBean(menu, MenuRequest.class);
    }

    /**
     * 递归生成菜单下拉树
     *
     * @param parentId         父菜单id
     * @param menuTreeRespList 菜单树列表
     * @return 菜单列表
     */
    private List<MenuTreeResponse> recurMenuTreeList(Integer parentId, List<MenuTreeResponse> menuTreeRespList) {
        return menuTreeRespList.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .peek(menu -> menu.setChildren(recurMenuTreeList(menu.getId(), menuTreeRespList)))
                .toList();
    }

    /**
     * 递归生成菜单列表
     *
     * @param parentId 父菜单id
     * @param menuList 菜单列表
     * @return 菜单列表
     */
    private List<MenuResponse> recurMenuList(Integer parentId, List<MenuResponse> menuList) {
        return menuList.stream()
                .filter(menuVO -> menuVO.getParentId().equals(parentId))
                .peek(menuVO -> menuVO.setChildren(recurMenuList(menuVO.getId(), menuList)))
                .toList();
    }

    /**
     * 递归生成菜单选项树
     *
     * @param parentId       父菜单id
     * @param menuOptionList 菜单树列表
     * @return 菜单列表
     */
    private List<MenuOptionResponse> recurMenuOptionList(Integer parentId, List<MenuOptionResponse> menuOptionList) {
        return menuOptionList.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .peek(menu -> menu.setChildren(recurMenuOptionList(menu.getValue(), menuOptionList)))
                .toList();
    }
}