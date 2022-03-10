package com.shangma.cn.controller;

import com.github.pagehelper.PageHelper;
import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.page.PageBean;
import com.shangma.cn.common.valid.group.*;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.dto.MenuDTO;
import com.shangma.cn.entity.Menu;
import com.shangma.cn.query.MenuQuery;
import com.shangma.cn.service.MenuService;
import com.shangma.cn.transfer.MenuTransfer;
import com.shangma.cn.utils.FormValidUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 11:00
 * 文件说明：
 */
@RestController
@RequestMapping("menu")
@Api(tags = "菜单管理", description = "菜单管理")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;


    @Autowired
    private MenuTransfer menuTransfer;


    @ApiOperation(value = "分页条件查询")
    @GetMapping("searchPage")
    public AxiosResult<PageBean<MenuDTO>> searchPage(MenuQuery MenuQuery) {
        //开启分页
        PageHelper.startPage(MenuQuery.getCurrentPage(), MenuQuery.getPageSize());
        PageBean<MenuDTO> pageBean = menuService.searchPage(MenuQuery);
        return AxiosResult.success(pageBean);
    }


    @GetMapping("{id}")
    @ApiOperation(value = "id查询")
    public AxiosResult<MenuDTO> findById(@PathVariable Long id) {
        return AxiosResult.success(menuTransfer.toDTO(menuService.findById(id)));
    }

    @PostMapping
    @ApiOperation(value = "添加菜单")
    public AxiosResult add(@Validated(AddGroup.class) @RequestBody Menu Menu) {
        Integer menuType = Menu.getMenuType();
        if (menuType == 1) {
            //判断目录相关的内容
            FormValidUtils.valid(Menu, DirectoryGroup.class);
        }
        if (menuType == 2) {
            //判断的是菜单
            FormValidUtils.valid(Menu, MenuGroup.class);
        }
        if (menuType == 3) {
            //判断按钮
            FormValidUtils.valid(Menu,BtnGroup.class);

        }

        return toAxios(menuService.add(Menu));
    }

    @PutMapping
    @ApiOperation(value = "修改菜单")
    public AxiosResult<Void> update(@RequestBody @Validated(UpdateGroup.class) Menu Menu) {
        //判断表单校验有没有成功
        Integer menuType = Menu.getMenuType();
        if (menuType == 1) {
            //判断目录相关的内容
            FormValidUtils.valid(Menu, DirectoryGroup.class);
        }
        if (menuType == 2) {
            //判断的是菜单
            FormValidUtils.valid(Menu, MenuGroup.class);
        }
        if (menuType == 3) {
            //判断按钮
            FormValidUtils.valid(Menu,BtnGroup.class);

        }

        return toAxios(menuService.update(Menu));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "通过id删除")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        int row = menuService.cascadeDeleteChildren(id);
        return toAxios(row);
    }


    /**
     * 获得select框中选择的内容
     */
    @GetMapping("getTreeData")
    @ApiOperation(value = "获取选择框中的数据")
    public AxiosResult<List<MenuDTO>> getSelectTreeData() {
        List<MenuDTO> list = menuService.getTreeData();
        return AxiosResult.success(list);
    }

}
