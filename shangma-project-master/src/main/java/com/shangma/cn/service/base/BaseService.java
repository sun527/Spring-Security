package com.shangma.cn.service.base;

import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shangma.cn.entity.Brand;
import com.shangma.cn.entity.Menu;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 11:16
 * 文件说明：
 */
public interface BaseService<T> {

    /**
     * 查询所有
     */
    public List<T> list();

    /**
     * 通过id查询
     */
    T findById(Long id);

    /**
     * 添加
     */
    int add(T t);

    /**
     * 修改
     */
    int update(T t);

    /**
     * 删除
     */
    int deleteById(Long id);

    /**
     * 批量删除
     */
    int batchDeleteByIds(List<Long> ids);

    void getCascadeChildrenIds(Long id, List<Long> ids);



}
