package com.shangma.cn.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.shangma.cn.dto.base.BaseDTO;
import lombok.Data;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/23 15:17
 * 文件说明：
 */
@Data
public class ScheduleBeanDTO {

    private Long cronId;
    private String cronExpress;

    private String cronDesc;

}
