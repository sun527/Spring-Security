package com.shangma.cn.service;

import com.shangma.cn.common.page.PageBean;
import com.shangma.cn.dto.AdminDTO;
import com.shangma.cn.dto.BrandDTO;
import com.shangma.cn.entity.Admin;
import com.shangma.cn.entity.Brand;
import com.shangma.cn.entity.Menu;
import com.shangma.cn.query.AdminQuery;
import com.shangma.cn.query.BrandQuery;
import com.shangma.cn.service.base.BaseService;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 10:55
 * 文件说明：
 */
public interface AdminService extends BaseService<Admin> {


    PageBean<AdminDTO> searchPage(AdminQuery adminQuery);

    /**
     * 账户信息 手机号 邮箱 要唯一
     *
     * @param admin
     * @return
     */
    boolean hasAdminWithAccountPhoneEmail(Admin admin);

    /**
     * 添加员工和员工的角色
     *
     * @param admin
     * @return
     */

    int addAdminAndAdminRoles(Admin admin);

    /**
     * 查询用户和用户的角色
     *
     * @param id
     * @return
     */

    AdminDTO getAdminAndRolesByAdminId(Long id);

    /**
     * 修改员工
     *
     * @param admin
     * @return
     */
    int updateAdminAndRole(Admin admin);

    /**
     * 删除员工和角色
     *
     * @param id
     * @return
     */
    int deleteAdminAndRoles(Long id);

    /**
     * 批量删除员工和角色
     *
     * @param ids
     * @return
     */
    int batchDeleteAdminAndRole(List<Long> ids);

    /**
     * 获得用户所以权限
     * @param aminId
     * @return
     */
    List<Menu> getMenusByAdminId(Long aminId);


}


