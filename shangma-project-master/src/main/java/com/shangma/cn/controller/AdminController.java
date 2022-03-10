package com.shangma.cn.controller;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.shangma.cn.common.exception.ApiException;
import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.http.AxiosStatus;
import com.shangma.cn.common.page.PageBean;
import com.shangma.cn.common.valid.group.AddGroup;
import com.shangma.cn.common.valid.group.UpdateGroup;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.dto.AdminDTO;
import com.shangma.cn.entity.Admin;
import com.shangma.cn.query.AdminQuery;
import com.shangma.cn.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 11:00

 * 文件说明：
 */
@RestController
@RequestMapping("admin")
@Api(tags = "管理员管理", description = "管理员管理")
public class AdminController extends BaseController {


    @Autowired
    private AdminService adminService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ApiOperation(value = "分页条件查询")
    @GetMapping("searchPage")
    public AxiosResult<PageBean<AdminDTO>> searchPage(AdminQuery AdminQuery) {
        //开启分页
        PageHelper.startPage(AdminQuery.getCurrentPage(), AdminQuery.getPageSize());
        PageBean<AdminDTO> pageBean = adminService.searchPage(AdminQuery);
        return AxiosResult.success(pageBean);
    }


    @GetMapping("{id}")
    @ApiOperation(value = "id查询")
    public AxiosResult<AdminDTO> findById(@PathVariable Long id) {
        AdminDTO adminDTO = adminService.getAdminAndRolesByAdminId(id);
        return AxiosResult.success(adminDTO);
    }

    @PostMapping
    @ApiOperation(value = "添加管理员")
    public AxiosResult<Void> add(@Validated(AddGroup.class) @RequestBody Admin Admin) {
        boolean b = adminService.hasAdminWithAccountPhoneEmail(Admin);
        if (b) {
            //存在
            throw new ApiException(AxiosStatus.ADMIN_EXIST);
        }
        Admin.setAdminPassword(bCryptPasswordEncoder.encode("123456"));
        int row = adminService.addAdminAndAdminRoles(Admin);
        return toAxios(row);
    }

    @PutMapping
    @ApiOperation(value = "修改管理员")
    public AxiosResult<Void> update(@RequestBody @Validated(UpdateGroup.class) Admin Admin) {
        //判断表单校验有没有成功
        int row = adminService.updateAdminAndRole(Admin);
        return toAxios(row);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "通过id删除")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return toAxios(adminService.deleteAdminAndRoles(id));

    }

    @DeleteMapping("batch/{ids}")
    @ApiOperation(value = "通过id批量删除")
    public AxiosResult<Void> batchDeleteByIds(@PathVariable List<Long> ids) {
        return toAxios(adminService.batchDeleteAdminAndRole(ids));
    }


    @GetMapping("exportExcel")
    @ApiOperation(value = "员工数据导出")
    public ResponseEntity<byte[]> exportExcel() throws Exception {
        List<Admin> list = adminService.list();
        list.forEach(admin -> {
            admin.setSex(admin.getGender() == 0 ? "男" : admin.getGender() == 1 ? "女" : "太监");
            try {
                admin.setUrl(new URL(admin.getAdminAvatar()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
        ByteArrayOutputStream out  = new ByteArrayOutputStream();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", URLEncoder.encode("员工信息表.xlsx", "utf-8"));
        EasyExcel.write(out, Admin.class).sheet("员工信息").doWrite(list);
        return new ResponseEntity<byte[]>(out.toByteArray(), httpHeaders, HttpStatus.OK);

    }

}
