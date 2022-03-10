package com.shangma.cn.controller;

import com.github.pagehelper.PageHelper;
import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.page.PageBean;
import com.shangma.cn.common.valid.group.AddGroup;
import com.shangma.cn.common.valid.group.UpdateGroup;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.dto.RoleDTO;
import com.shangma.cn.entity.Role;
import com.shangma.cn.query.RoleQuery;
import com.shangma.cn.service.RoleService;
import com.shangma.cn.transfer.RoleTransfer;
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
@RequestMapping("role")
@Api(tags = "角色管理", description = "角色管理")
public class RoleController extends BaseController {


    @Autowired
    private RoleService roleService;


    @Autowired
    private RoleTransfer roleTransfer;


    @ApiOperation(value = "分页条件查询")
    @GetMapping("searchPage")
    public AxiosResult<PageBean<RoleDTO>> searchPage(RoleQuery RoleQuery) {
        //开启分页
        PageHelper.startPage(RoleQuery.getCurrentPage(), RoleQuery.getPageSize());
        PageBean<RoleDTO> pageBean = roleService.searchPage(RoleQuery);
        return AxiosResult.success(pageBean);
    }

    @ApiOperation(value = "查询所有")
    @GetMapping
    public AxiosResult<List<RoleDTO>> list() {
        return AxiosResult.success(roleTransfer.toDTO(roleService.list()));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "id查询")
    public AxiosResult<RoleDTO> findById(@PathVariable Long id) {
        return AxiosResult.success(roleTransfer.toDTO(roleService.findById(id)));
    }

    @PostMapping
    @ApiOperation(value = "添加角色")
    public AxiosResult add(@Validated(AddGroup.class) @RequestBody Role Role) {
        return toAxios(roleService.add(Role));
    }

    @PutMapping
    @ApiOperation(value = "修改角色")
    public AxiosResult<Void> update(@RequestBody @Validated(UpdateGroup.class) Role Role) {
        //判断表单校验有没有成功
        return toAxios(roleService.update(Role));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "通过id删除")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {


        return toAxios(roleService.deleteCascade(id));
    }

    @DeleteMapping("batch/{ids}")
    @ApiOperation(value = "通过id批量删除")
    public AxiosResult<Void> batchDeleteByIds(@PathVariable List<Long> ids) {
        return toAxios(roleService.batchCascadeDelete(ids));
    }


    /**
     * 给角色赋予权限
     */
    @PostMapping("{roleId}/menu/{menuIds}")
    @ApiOperation(value = "给角色授权")
    public AxiosResult<Void> setRoleMenu(@PathVariable Long roleId, @PathVariable List<Long> menuIds) {
        int row = roleService.setRoleMenu(roleId, menuIds);
        return toAxios(row);

    }
    /**
     * 获得角色的权限
     */
    @GetMapping("{roleId}/menus")
    @ApiOperation(value = "角色权限")
    public AxiosResult<List<Long>> getMenusByRoleId(@PathVariable Long roleId) {
        List<Long> menuIds = roleService.getMenusByRoleId(roleId);
        return AxiosResult.success(menuIds);
    }

}
