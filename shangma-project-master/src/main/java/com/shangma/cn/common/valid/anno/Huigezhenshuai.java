package com.shangma.cn.common.valid.anno;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/24 17:18
 * 文件说明： 从多个取值中 取出一个
 */
@Target(FIELD)
@Retention(RUNTIME)
//元注解 ：  修饰注解的注解  @Target指定注解的作用范围 @Retention 指定注解的生效时机\
//指定使用哪个处理器 取处理是否满足规则
@Constraint(validatedBy = {HuigezhenshuaiHandler.class})
public @interface Huigezhenshuai {

    String message() default "{javax.validation.constraints.NotNull.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] values() default {};
}
