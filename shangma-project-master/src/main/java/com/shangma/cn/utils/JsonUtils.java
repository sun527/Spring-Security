package com.shangma.cn.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 开发人员：辉哥真球帅
 * 联系邮箱：zhengzhoudaxuevip@163.com
 * 创建时间：2021/11/30 17:52
 * 文件描述:
 */
public class JsonUtils {


    static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转字符串
     */

    public static String obj2Str(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 字符串转对象
     */

    public static <T> T str2Obj(String jsonStr, Class<T> clazz) {
        T t = null;
        try {
            t = objectMapper.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return t;
    }
}
