package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.Menu;
import cn.edu.whut.gumorming.model.ResponseResult;
import cn.edu.whut.gumorming.model.query.MenuQuery;
import cn.edu.whut.gumorming.model.request.MenuRequest;
import cn.edu.whut.gumorming.model.response.MenuOptionResponse;
import cn.edu.whut.gumorming.model.response.MenuResponse;
import cn.edu.whut.gumorming.model.response.MenuTreeResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * (Menu)表服务接口
 *
 * @author GuMorming
 * @since 2024-02-18 22:49:53
 */
public interface MenuService extends IService<Menu> {

    List<MenuResponse> listMenuVO(MenuQuery menuQuery);

    ResponseResult<?> addMenu(MenuRequest menu);

    ResponseResult<?> deleteMenu(Integer menuId);

    ResponseResult<?> updateMenu(MenuRequest menu);

    List<MenuTreeResponse> listMenuTree();

    List<MenuOptionResponse> listMenuOption();

    MenuRequest editMenu(Integer menuId);
}