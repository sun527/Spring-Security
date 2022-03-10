package com.shangma.cn.query;

import com.shangma.cn.query.base.BaseQuery;
import lombok.Data;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/23 14:51
 * 文件说明：
 */
@Data
public class AdminQuery extends BaseQuery {

    private String adminName;

    private String adminPhone;

    private Integer gender;
}
