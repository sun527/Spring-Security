package com.shangma.cn.query;

import com.shangma.cn.query.base.BaseQuery;
import lombok.Data;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/23 14:51
 * 文件说明：
 */
//@Data
public class BrandQuery extends BaseQuery {

    private String brandName;

    private String brandDesc;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
    }

    @Override
    public String toString() {
        return "BrandQuery{" +
                "brandName='" + brandName + '\'' +
                ", brandDesc='" + brandDesc + '\'' +
                '}';
    }
}
