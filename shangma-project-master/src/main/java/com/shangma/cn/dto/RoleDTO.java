package com.shangma.cn.dto;

import com.shangma.cn.dto.base.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/25 16:07
 * 文件说明：
 */
@Data
public class RoleDTO  extends BaseDTO {
    private String roleName;
    private String roleDesc;
}
