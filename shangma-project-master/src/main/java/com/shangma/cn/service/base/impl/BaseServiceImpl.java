package com.shangma.cn.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shangma.cn.entity.Menu;
import com.shangma.cn.entity.base.BaseEntity;
import com.shangma.cn.mapper.base.MyMapper;
import com.shangma.cn.service.base.BaseService;
import com.shangma.cn.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 11:18
 * 文件说明：
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private MyMapper<T> myMapper;


    @Override
    public List<T> list() {
        return myMapper.selectList(null);
    }


    @Override
    public T findById(Long id) {
        return myMapper.selectById(id);
    }

    @Override
    public int add(T t) {
        ReflectionUtils.invokeMethod(t, "setData", null, null);
        return myMapper.insert(t);
    }

    @Override
    public int update(T t) {
        ReflectionUtils.invokeMethod(t, "setData", null, null);
        return myMapper.updateById(t);
    }

    @Override
    public int deleteById(Long id) {
        return myMapper.deleteById(id);
    }

    @Transactional
    @Override
    public int batchDeleteByIds(List<Long> ids) {
        return myMapper.deleteBatchIds(ids);
    }
    @Override
    public void getCascadeChildrenIds(Long id, List<Long> ids) {
        List<T> ts = myMapper.selectList(new QueryWrapper<T>().eq("parent_id", id));
        if (!CollectionUtils.isEmpty(ts)) {
            ts.forEach(t -> {
                ids.add((Long) ReflectionUtils.getFieldValue(t, "id"));
                getCascadeChildrenIds((Long) ReflectionUtils.getFieldValue(t, "id"), ids);
            });
        }
    }
}
