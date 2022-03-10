package com.shangma.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/30 17:25
 * 文件说明：
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_schedule")
@ApiModel(value = "Schedule对象", description = "")
public class ScheduleBean implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long cronId;
    private String cronExpress;

    private String cronDesc;
}
