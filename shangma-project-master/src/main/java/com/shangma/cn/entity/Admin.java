package com.shangma.cn.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shangma.cn.common.valid.anno.Huigezhenshuai;
import com.shangma.cn.common.valid.group.AddGroup;
import com.shangma.cn.common.valid.group.UpdateGroup;
import com.shangma.cn.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author 辉哥
 * @since 2021-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_admin")
@ApiModel(value = "Admin对象", description = "")
@ContentRowHeight(100)
@HeadRowHeight(45)
@ColumnWidth(25)
// 头背景设置成红色 IndexedColors.RED.getIndex()
@HeadStyle(horizontalAlignment = HorizontalAlignment.CENTER, verticalAlignment = VerticalAlignment.CENTER)
// 头字体设置成20
@HeadFontStyle(fontHeightInPoints = 20, color = 10, bold = true)
// 内容的背景设置成绿色 IndexedColors.GREEN.getIndex()
@ContentStyle(horizontalAlignment = HorizontalAlignment.CENTER, verticalAlignment = VerticalAlignment.CENTER)
// 内容字体设置成20
@ContentFontStyle(fontHeightInPoints = 20, fontName = "华文行楷", color = 14, bold = true)
public class Admin extends BaseEntity {

    @ExcelProperty(value = "员工账号")
    @ApiModelProperty(value = "用户账户登录时的用户名")
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^[a-zA-Z]\\w{5,8}$", groups = {AddGroup.class, UpdateGroup.class})
    private String adminAccount;

    @ExcelProperty(value = "员工姓名")
    @NotBlank(message = "姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "管理员姓名")
    private String adminName;


    @ExcelIgnore
    @ApiModelProperty(value = "0：表示男    1：表示女     2：人妖")
    @Huigezhenshuai(values = {0, 1, 2})
    private Integer gender;

    @ExcelProperty(value = "员工性别")
    private transient String sex;

    @ExcelProperty(value = "员工手机")
    @NotBlank(message = "手机号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^1[3|4|5|6|7|8|9][0-9]\\d{8}$", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "手机号码")
    private String adminPhone;


    @ExcelProperty(value = "员工邮箱")
    @NotBlank(message = "邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message = "邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "管理员邮箱")
    private String adminEmail;

    @ExcelIgnore
    @ApiModelProperty(value = "管理员头像")
    private String adminAvatar;


    @ExcelProperty(value = "员工头像")
    private transient URL url;

    @ExcelIgnore
    @ApiModelProperty(value = "员工登录密码")
    @JsonIgnore
    private String adminPassword;
    @ExcelIgnore
    @ApiModelProperty(value = "是否激活")
    private Boolean isActive;
    @ExcelIgnore
    @ApiModelProperty(value = "是否是超级管理员")
    private Boolean isAdmin;

    @ExcelProperty(value = "员工薪资")
    @NotNull(message = "薪资不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "管理员薪资")
    private Double adminSalary;

    @ExcelProperty(value = "员工地址")
    @NotBlank(message = "地址不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "员工住址")
    @ColumnWidth(60)
    private String adminAddress;


    @ColumnWidth(50)
    @ExcelProperty(value = "身份证号")
    @NotBlank(message = "身份证号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "身份证号码")
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", groups = {AddGroup.class, UpdateGroup.class})
    private String adminCode;


    @ExcelIgnore
    private transient List<Long> roleIds;

}
