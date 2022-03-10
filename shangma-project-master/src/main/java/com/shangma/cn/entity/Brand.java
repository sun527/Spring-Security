package com.shangma.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shangma.cn.common.valid.group.AddGroup;
import com.shangma.cn.common.valid.group.UpdateGroup;
import com.shangma.cn.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 10:51
 * 文件说明：
 * 把实体类分层
 * 1： 负责查询时的参数接收相关实体类 =BaseQuery
 * 2： 负载添加修改参数接收的实体类()Brand和数据库对应
 * 3： 返回前端需要的实体类  VO  DTO
 */
@TableName(value = "t_brand")
@Data
@ApiModel(value = "品牌实体")
public class Brand extends BaseEntity {

    @ApiModelProperty(value = "品牌名称")
    @NotBlank(message = "品牌名称不能为空",groups = {AddGroup.class, UpdateGroup.class})
    private String brandName;

    @NotBlank(message = "品牌站点不能为空" ,groups = {AddGroup.class, UpdateGroup.class})
    @URL(message = "传递的必须得是一个链接" ,groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "品牌站点")
    private String brandSite;
    @ApiModelProperty(value = "品牌LOGO")
    private String brandLogo;
    @ApiModelProperty(value = "品牌描述")
    private String brandDesc;


}
