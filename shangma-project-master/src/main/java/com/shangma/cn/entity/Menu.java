package com.shangma.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shangma.cn.common.valid.anno.Huigezhenshuai;
import com.shangma.cn.common.valid.group.*;
import com.shangma.cn.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 辉哥
 * @since 2021-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_menu")
@ApiModel(value = "Menu对象", description = "")
public class Menu extends BaseEntity {

    @NotBlank(message = "权限名称不能为空", groups = {MenuGroup.class, DirectoryGroup.class, BtnGroup.class})
    @ApiModelProperty(value = "权限名称")
    private String menuTitle;

    @NotNull(message = "父亲id不能为空", groups = {MenuGroup.class, DirectoryGroup.class, BtnGroup.class})
    @ApiModelProperty(value = "权限父亲id   如果是第一级 父id=0")
    private Long parentId;

    @NotNull(message = "权限类型不能为空", groups = {MenuGroup.class, DirectoryGroup.class})
    @Huigezhenshuai(message = "只能取值1 2 3 ", values = {1, 2, 3}, groups = {MenuGroup.class, DirectoryGroup.class, BtnGroup.class})
    @ApiModelProperty(value = "1：表示目录，2：表示菜单，3表示按钮")
    private Integer menuType;

    @NotNull(message = "排序不能为空", groups = {MenuGroup.class, DirectoryGroup.class, BtnGroup.class})
    @ApiModelProperty(value = "权限排序")
    private Integer sort;
    @NotBlank(message = "路由地址不能为空", groups = {MenuGroup.class, DirectoryGroup.class})
    @ApiModelProperty(value = "路由地址")
    private String menuRouter;

    @NotBlank(message = "菜单图标不能为空", groups = {MenuGroup.class, DirectoryGroup.class})
    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;

    @NotBlank(message = "组件地址不能为空", groups = {MenuGroup.class})
    @ApiModelProperty(value = "组件地址")
    private String componentPath;

    @NotBlank(message = "组件名称不能为空", groups = {MenuGroup.class})
    @ApiModelProperty(value = "组件名称")
    private String componentName;

    @NotBlank(message = "组件名称不能为空", groups = {MenuGroup.class, BtnGroup.class})
    @ApiModelProperty(value = "权限标识")
    private String permSign;




}
