package com.shangma.cn.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shangma.cn.dto.base.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/29 9:57
 * 文件说明：
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDTO  extends BaseDTO {

    private String menuTitle;
    private Long parentId;
    private Integer menuType;
    private Integer sort;
    private String menuRouter;
    private String menuIcon;
    private String componentPath;
    private String componentName;
    private String permSign;
    private List<MenuDTO> children;

}
