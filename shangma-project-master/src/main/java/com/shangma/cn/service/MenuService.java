package com.shangma.cn.service;

import com.shangma.cn.common.page.PageBean;
import com.shangma.cn.dto.BrandDTO;
import com.shangma.cn.dto.MenuDTO;
import com.shangma.cn.entity.Brand;
import com.shangma.cn.entity.Menu;
import com.shangma.cn.query.BrandQuery;
import com.shangma.cn.query.MenuQuery;
import com.shangma.cn.service.base.BaseService;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 10:55
 * 文件说明：
 */
public interface MenuService extends BaseService<Menu> {


    /**
     * 条件查询
     * @param brandQuery
     * @return
     */
    PageBean<MenuDTO> searchPage(MenuQuery menuQuery);

    List<MenuDTO> getTreeData();

    int cascadeDeleteChildren(Long id);


}
