package com.shangma.cn.service;

import com.shangma.cn.common.page.PageBean;
import com.shangma.cn.dto.AdminDTO;
import com.shangma.cn.dto.MenuDTO;
import com.shangma.cn.dto.RoleDTO;
import com.shangma.cn.entity.Admin;
import com.shangma.cn.entity.Role;
import com.shangma.cn.query.AdminQuery;
import com.shangma.cn.query.RoleQuery;
import com.shangma.cn.service.base.BaseService;

import java.util.List;
import java.util.Set;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 10:55
 * 文件说明：
 */
public interface RoleService extends BaseService<Role> {


    PageBean<RoleDTO> searchPage(RoleQuery roleQuery);


    int setRoleMenu(Long roleId, List<Long> menuIds);

    List<Long> getMenusByRoleId(Long roleId);

    int deleteCascade(Long id);

    int batchCascadeDelete(List<Long> ids);

}
