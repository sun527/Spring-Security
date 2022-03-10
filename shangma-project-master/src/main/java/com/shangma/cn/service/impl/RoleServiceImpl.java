package com.shangma.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.page.PageBean;
import com.shangma.cn.dto.MenuDTO;
import com.shangma.cn.dto.RoleDTO;
import com.shangma.cn.entity.AdminRole;
import com.shangma.cn.entity.Menu;
import com.shangma.cn.entity.Role;
import com.shangma.cn.entity.RoleMenu;
import com.shangma.cn.mapper.AdminRoleMapper;
import com.shangma.cn.mapper.MenuMapper;
import com.shangma.cn.mapper.RoleMapper;
import com.shangma.cn.mapper.RoleMenuMapper;
import com.shangma.cn.query.RoleQuery;
import com.shangma.cn.service.RoleService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import com.shangma.cn.transfer.MenuTransfer;
import com.shangma.cn.transfer.RoleTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {


    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleTransfer roleTransfer;

    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuTransfer menuTransfer;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public PageBean<RoleDTO> searchPage(RoleQuery RoleQuery) {
        LambdaQueryWrapper<Role> lambda = new QueryWrapper<Role>().lambda();

        if (!StringUtils.isEmpty(RoleQuery.getRoleName()))
            lambda.like(Role::getRoleName, RoleQuery.getRoleName());

        if (Objects.nonNull(RoleQuery.getStartTime()) && Objects.nonNull(RoleQuery.getEndTime()))
            lambda.between(Role::getCreateTime, RoleQuery.getStartTime(), RoleQuery.getEndTime());
        List<Role> Roles = roleMapper.selectList(lambda);
        PageInfo<Role> pageInfo = new PageInfo<>(Roles);
        List<RoleDTO> RoleDTOS = roleTransfer.toDTO(Roles);
        return PageBean.initData(pageInfo.getTotal(), RoleDTOS);
    }

    @Override
    public int setRoleMenu(Long roleId, List<Long> menuIds) {
        //删除已有权限
        roleMenuMapper.delete(new UpdateWrapper<RoleMenu>().lambda().eq(RoleMenu::getRoleId, roleId));
        menuIds.forEach(menuId -> roleMenuMapper.insert(new RoleMenu(roleId, menuId)));
        return 1;
    }

    @Override
    public List<Long> getMenusByRoleId(Long roleId) {
        List<Long> collect = roleMenuMapper.selectList(new QueryWrapper<RoleMenu>().lambda().eq(RoleMenu::getRoleId, roleId))
                .stream()
                .map(RoleMenu::getMenuId)
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(collect)) {
            //返回在权限列表中没有孩子的
            List<Menu> menus = menuMapper.selectBatchIds(collect);
            collect.clear();
            menus.forEach(menu -> {
                if (!menus.stream().anyMatch(item -> item.getParentId().equals(menu.getId()))) {
                    collect.add(menu.getId());
                }
            });
        }
        return collect;
    }

    @Override
    public int deleteCascade(Long id) {
        //删除员工和角色中间表 和本角色相关的数据
        adminRoleMapper.delete(new UpdateWrapper<AdminRole>().lambda().eq(AdminRole::getRoleId, id));
        //删除角色和权限中间表 和本角色相关的数据
        roleMenuMapper.delete(new UpdateWrapper<RoleMenu>().lambda().eq(RoleMenu::getRoleId, id));
        return this.deleteById(id);
    }

    @Override
    public int batchCascadeDelete(List<Long> ids) {
        ids.forEach(id -> {
            //删除员工和角色中间表 和本角色相关的数据
            adminRoleMapper.delete(new UpdateWrapper<AdminRole>().lambda().eq(AdminRole::getRoleId, id));
            //删除角色和权限中间表 和本角色相关的数据
            roleMenuMapper.delete(new UpdateWrapper<RoleMenu>().lambda().eq(RoleMenu::getRoleId, id));
        });
        return this.batchDeleteByIds(ids);
    }
    


}
