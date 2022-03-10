package com.shangma.cn.utils;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext1;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext1 = applicationContext;
    }

    /**
     * 从容器中获取对象
     */
    public static <T> T getBean(Class<T> tClass){
        return applicationContext1.getBean(tClass);
    }
}
