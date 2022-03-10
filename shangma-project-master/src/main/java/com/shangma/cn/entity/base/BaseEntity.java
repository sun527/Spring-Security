package com.shangma.cn.entity.base;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shangma.cn.common.valid.group.AddGroup;
import com.shangma.cn.common.valid.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 11:25
 * 文件说明：
 */
@Data
public class BaseEntity implements Serializable {

    @ExcelProperty(value = "员工id",index = 0)
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    @Null(message = "添加时id必须为空",groups ={AddGroup.class}) //当添加时 必须得是null
    @NotNull(message = "修改时id不能位空",groups = {UpdateGroup.class})// 当修改时 又不能为null
    private Long id;



    @ApiModelProperty(value = "创建者")
    @ExcelIgnore
    @JsonIgnore
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @ExcelIgnore
    @JsonIgnore
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改者")
    @ExcelIgnore
    @JsonIgnore
    private Long updateBy;

    @ApiModelProperty(value = "修改时间")
    @ExcelIgnore
    @JsonIgnore
    private LocalDateTime updateTime;

    /**
     * 添加数据
     */
    public void setData() {
        if (id == null) {
            this.createBy = 1L;
            this.createTime = LocalDateTime.now();
        } else {
            this.updateBy = 2L;
            this.updateTime = LocalDateTime.now();
        }
    }
}
