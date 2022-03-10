package com.shangma.cn.common.valid.anno;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/24 17:25
 * 文件说明：
 */
public class HuigezhenshuaiHandler implements ConstraintValidator<Huigezhenshuai, Integer> {

    private List<Integer> list;

    @Override
    public void initialize(Huigezhenshuai constraintAnnotation) {
        int[] values = constraintAnnotation.values();
        list = CollectionUtils.arrayToList(values);


    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return list.contains(value);
    }
}
