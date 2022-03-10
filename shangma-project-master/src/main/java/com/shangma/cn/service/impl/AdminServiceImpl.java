package com.shangma.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.shangma.cn.common.page.PageBean;
import com.shangma.cn.dto.AdminDTO;
import com.shangma.cn.entity.Admin;
import com.shangma.cn.entity.AdminRole;
import com.shangma.cn.entity.Menu;
import com.shangma.cn.entity.RoleMenu;
import com.shangma.cn.mapper.AdminMapper;
import com.shangma.cn.mapper.AdminRoleMapper;
import com.shangma.cn.mapper.MenuMapper;
import com.shangma.cn.mapper.RoleMenuMapper;
import com.shangma.cn.query.AdminQuery;
import com.shangma.cn.service.AdminService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import com.shangma.cn.transfer.AdminTransfer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
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
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {


    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminTransfer adminTransfer;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public PageBean<AdminDTO> searchPage(AdminQuery adminQuery) {
        LambdaQueryWrapper<Admin> lambda = new QueryWrapper<Admin>().lambda();
        if (!StringUtils.isEmpty(adminQuery.getAdminName()))
            lambda.like(Admin::getAdminName, adminQuery.getAdminName());
        if (!StringUtils.isEmpty(adminQuery.getAdminPhone()))
            lambda.eq(Admin::getAdminPhone, adminQuery.getAdminPhone());

        if (Objects.nonNull(adminQuery.getGender()))
            lambda.eq(Admin::getGender, adminQuery.getGender());

        if (Objects.nonNull(adminQuery.getStartTime()) && Objects.nonNull(adminQuery.getEndTime()))
            lambda.between(Admin::getCreateTime, adminQuery.getStartTime(), adminQuery.getEndTime());

        List<Admin> admins = adminMapper.selectList(lambda);
        PageInfo<Admin> pageInfo = new PageInfo<>(admins);

        List<AdminDTO> adminDTOS = adminTransfer.toDTO(admins);
        return PageBean.initData(pageInfo.getTotal(), adminDTOS);
    }


    @Override
    public boolean hasAdminWithAccountPhoneEmail(Admin admin) {
        Integer integer = adminMapper.selectCount(new QueryWrapper<Admin>().lambda().or().eq(Admin::getAdminAccount, admin.getAdminAccount()).or().eq(Admin::getAdminPhone, admin.getAdminPhone()).or().eq(Admin::getAdminEmail, admin.getAdminEmail()));
        return integer > 0;
    }

    @Override
    public int addAdminAndAdminRoles(Admin admin) {
        this.add(admin);
        if (!CollectionUtils.isEmpty(admin.getRoleIds())) {
            admin.getRoleIds().forEach(roleId -> adminRoleMapper.insert(new AdminRole(roleId, admin.getId())));
        }
        return 1;
    }


    @Override
    public AdminDTO getAdminAndRolesByAdminId(Long id) {
        Admin admin = this.findById(id);
        AdminDTO adminDTO = adminTransfer.toDTO(admin);
        List<AdminRole> adminRoles = adminRoleMapper.selectList(new QueryWrapper<AdminRole>().lambda().eq(AdminRole::getAdminId, id));
        List<Long> roleIds = adminRoles.stream().map(AdminRole::getRoleId).collect(Collectors.toList());
        adminDTO.setRoleIds(roleIds);
        return adminDTO;
    }


    @Override
    public int updateAdminAndRole(Admin admin) {
        //删除员工的所有的角色
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().lambda().eq(AdminRole::getAdminId, admin.getId()));
        if (!CollectionUtils.isEmpty(admin.getRoleIds())) {
            admin.getRoleIds().forEach(roleId -> adminRoleMapper.insert(new AdminRole(roleId, admin.getId())));
        }
        return this.update(admin);
    }

    @Override
    public int deleteAdminAndRoles(Long id) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().lambda().eq(AdminRole::getAdminId, id));
        return this.deleteById(id);
    }


    @Override
    public int batchDeleteAdminAndRole(List<Long> ids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().lambda().in(AdminRole::getAdminId, ids));
        return this.batchDeleteByIds(ids);
    }

    @Override
    public List<Menu> getMenusByAdminId(Long adminId) {
        //通过admin-role中间表获取用户所有的role的id
        List<Long> roleId = adminRoleMapper.selectList(new QueryWrapper<AdminRole>().lambda().eq(AdminRole::getAdminId, adminId)).stream().map(AdminRole::getRoleId).collect(Collectors.toList());
        //通过roleId拿到用户所有的权限Id
        List<Long> menuId = roleMenuMapper.selectList(new QueryWrapper<RoleMenu>().lambda().in(RoleMenu::getRoleId, roleId)).stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        //通过用户拥有的权限id获取用户的权限
        List<Menu> menuList = menuMapper.selectBatchIds(menuId);
        return menuList;
    }

}
