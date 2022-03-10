package com.shangma.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.page.PageBean;
import com.shangma.cn.dto.MenuDTO;
import com.shangma.cn.entity.Menu;
import com.shangma.cn.mapper.MenuMapper;
import com.shangma.cn.query.MenuQuery;
import com.shangma.cn.service.MenuService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import com.shangma.cn.transfer.MenuTransfer;
import com.shangma.cn.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 10:58
 * 文件说明：
 * 2个实体类的属性值互换
 * BeanUtils jar包
 * spring提供了BeanUtils
 */
@Service
@Transactional
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {


    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuTransfer menuTransfer;


    /*
     * 第一种写法： 层级固定
     *
     * 第二种写法： 层级不固定
     *
     *
     * */
    @Override
    public PageBean<MenuDTO> searchPage(MenuQuery MenuQuery) {
        if (MenuQuery.isQuery()) {
            //是查询
            LambdaQueryWrapper<Menu> lambda = new QueryWrapper<Menu>().lambda();
            if (!StringUtils.isEmpty(MenuQuery.getMenuTitle()))
                lambda.like(Menu::getMenuTitle, MenuQuery.getMenuTitle());
            if (Objects.nonNull(MenuQuery.getMenuType()))
                lambda.eq(Menu::getMenuType, MenuQuery.getMenuType());
            if (Objects.nonNull(MenuQuery.getStartTime()) && Objects.nonNull(MenuQuery.getEndTime()))
                lambda.between(Menu::getCreateTime, MenuQuery.getStartTime(), MenuQuery.getEndTime());

            List<Menu> menus = menuMapper.selectList(lambda);
            PageInfo<Menu> pageInfo = new PageInfo<>(menus);
            List<MenuDTO> menuDTOS = menuTransfer.toDTO(menus);
            return PageBean.initData(pageInfo.getTotal(), menuDTOS);
        } else {
            //不是查询
            List<Menu> rootMenus = menuMapper.selectList(new QueryWrapper<Menu>().lambda().eq(Menu::getParentId, 0L));
            PageInfo<Menu> pageInfo = new PageInfo<>(rootMenus);
            List<MenuDTO> rootDtos = menuTransfer.toDTO(rootMenus);
            List<Menu> otherMenus = menuMapper.selectList(new QueryWrapper<Menu>().lambda().ne(Menu::getParentId, 0L));
            List<MenuDTO> otherDTOS = menuTransfer.toDTO(otherMenus);
            TreeUtils.buildTreeData(rootDtos, otherDTOS);
            return PageBean.initData(pageInfo.getTotal(), rootDtos);
        }


    }

    @Override
    public List<MenuDTO> getTreeData() {
        List<Menu> rootMenus = menuMapper.selectList(new QueryWrapper<Menu>().lambda().eq(Menu::getParentId, 0L));
        List<MenuDTO> rootDtos = menuTransfer.toDTO(rootMenus);
        List<Menu> otherMenus = menuMapper.selectList(new QueryWrapper<Menu>().lambda().ne(Menu::getParentId, 0L));
        List<MenuDTO> otherDTOS = menuTransfer.toDTO(otherMenus);
        TreeUtils.buildTreeData(rootDtos, otherDTOS);
        return rootDtos;
    }

    @Override
    public int cascadeDeleteChildren(Long id) {
        List<Long> ids = new ArrayList<>();
        getCascadeChildrenIds(id, ids);
        if(ids.size()>0){
            menuMapper.deleteBatchIds(ids);
        }
        return this.deleteById(id);
    }




}
