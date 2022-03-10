package com.shangma.cn.dto;

import com.shangma.cn.dto.base.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/23 15:17
 * 文件说明：
 */
@Data
public class BrandDTO extends BaseDTO {
    private String brandName;
    private String brandSite;
    private String brandLogo;
    private String brandDesc;

}
