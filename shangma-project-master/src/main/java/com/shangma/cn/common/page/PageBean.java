package com.shangma.cn.common.page;

import lombok.Data;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 11:35
 * 文件说明：
 */
@Data
public class PageBean<T> {
    private long total;
    private List<T> data;

    private PageBean(long total, List<T> data) {
        this.data = data;
        this.total = total;
    }

    public static <T> PageBean<T> initData(long total, List<T> data) {
        return new PageBean<T>(total, data);
    }
}


