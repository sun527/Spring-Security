package com.shangma.cn.utils;

import lombok.val;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/29 15:52
 * 文件说明： 数据封装成一个树型数据
 */
public class TreeUtils {


    /**
     * 参数一： 最顶级的集合
     * 参数二： 表示所有数据
     * 目的： 从所有的数据中 查询出顶级的孩子 递归查询
     *
     * @param root
     * @param allList
     * @param <T>
     */
    public static <T> void buildTreeData(List<T> root, List<T> allList) {
        List<T> sortList = root.stream().sorted((t1, t2) -> {
            Integer t1Sort = (Integer) ReflectionUtils.getFieldValue(t1, "sort");
            Integer t2Sort = (Integer) ReflectionUtils.getFieldValue(t2, "sort");
            return t1Sort - t2Sort;
        }).collect(Collectors.toList());
        root.clear();
        root.addAll(sortList);
        root.forEach(t -> getChildren(t, allList));
    }

    /**
     * 找孩子
     */
    private static <T> void getChildren(T t, List<T> allList) {
        List<T> children = allList.stream().filter(t1 -> ((Long) ReflectionUtils.getFieldValue(t, "id")).equals((Long) ReflectionUtils.getFieldValue(t1, "parentId"))).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(children)) {
            List<T> sortChildren = children.stream().sorted((t1, t2) -> {
                Integer t1Sort = (Integer) ReflectionUtils.getFieldValue(t1, "sort");
                Integer t2Sort = (Integer) ReflectionUtils.getFieldValue(t2, "sort");
                return t1Sort - t2Sort;
            }).collect(Collectors.toList());
            ReflectionUtils.setFieldValue(t, "children", sortChildren);
            sortChildren.forEach(t1 -> getChildren(t1, allList));
        }
    }


}
