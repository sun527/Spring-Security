package com.shangma.cn.controller;

import com.github.pagehelper.PageHelper;
import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.page.PageBean;
import com.shangma.cn.common.valid.group.AddGroup;
import com.shangma.cn.common.valid.group.UpdateGroup;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.dto.BrandDTO;
import com.shangma.cn.entity.Brand;
import com.shangma.cn.query.BrandQuery;
import com.shangma.cn.service.BrandService;
import com.shangma.cn.transfer.BrandTransfer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("brand")
@Api(tags = "品牌管理", description = "品牌管理")
public class BrandController extends BaseController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private BrandTransfer brandTransfer;

    @ApiOperation(value = "分页条件查询")
    @GetMapping("searchPage")
    @PreAuthorize("hasAuthority('good:brand:list')")
    public AxiosResult<PageBean<BrandDTO>> searchPage(BrandQuery brandQuery) {
        //开启分页
        PageHelper.startPage(brandQuery.getCurrentPage(), brandQuery.getPageSize());
        PageBean<BrandDTO> pageBean = brandService.searchPage(brandQuery);
        return AxiosResult.success(pageBean);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "id查询")
    public AxiosResult<BrandDTO> findById(@PathVariable Long id) {
        return AxiosResult.success(brandTransfer.toDTO(brandService.findById(id)));
    }

    @PostMapping
    @ApiOperation(value = "添加品牌")
    @PreAuthorize("hasAuthority('good:brand:add')")
    public AxiosResult add(@Validated(AddGroup.class) @RequestBody Brand brand) {
        return toAxios(brandService.add(brand));
    }

    @PutMapping
    @ApiOperation(value = "修改品牌")
    @PreAuthorize("hasAuthority('good:brand:edit')")
    public AxiosResult<Void> update(@RequestBody @Validated(UpdateGroup.class) Brand brand) {
        //判断表单校验有没有成功
        return toAxios(brandService.update(brand));
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "通过id删除")
    @PreAuthorize("hasAuthority('good:brand:delete')")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return toAxios(brandService.deleteById(id));
    }

    @DeleteMapping("batch/{ids}")
    @ApiOperation(value = "通过id批量删除")
    @PreAuthorize("hasAuthority('good:brand:batch')")
    public AxiosResult<Void> batchDeleteByIds(@PathVariable List<Long> ids) {
        return toAxios(brandService.batchDeleteByIds(ids));
    }

}
