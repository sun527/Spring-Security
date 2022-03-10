package com.shangma.cn.dto;

import com.shangma.cn.dto.base.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/23 15:17
 * 文件说明：
 */
@Data
public class AdminDTO extends BaseDTO {
    private String adminAccount;
    private String adminName;
    private Integer gender;
    private String adminPhone;
    private String adminEmail;
    private String adminAvatar;
    private Boolean isActive;
    private Boolean isAdmin;
    private Double adminSalary;
    private String adminAddress;
    private String adminCode;
    private List<Long> roleIds;
}
