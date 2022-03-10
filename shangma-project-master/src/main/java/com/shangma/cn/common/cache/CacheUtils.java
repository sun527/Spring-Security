package com.shangma.cn.common.cache;

import com.mysql.cj.util.TimeUtil;
import com.shangma.cn.utils.SpringBeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
* redis缓存的基础操作
*/
public class CacheUtils {

    private static StringRedisTemplate stringRedisTemplate = SpringBeanUtils.getBean(StringRedisTemplate.class);

    /**
     * 存数据
     */
    public static void setCache(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value);
    }

    /**
     * 存入指定时间的数据
     */
    public static void setCacheWithExpireTime(String key,String value,long minute){
        stringRedisTemplate.opsForValue().set(key,value,minute, TimeUnit.MINUTES);
    }

    /**
     * 判断数据是否存在
     */
    public static boolean hasCode(String key){
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 取数据
     */
    public static String getCache(String key){
        if (hasCode(key)){
            return stringRedisTemplate.opsForValue().get(key);
        }
        return "";
    }

    /**
     * 删除数据
     */
    public static void removeCache(String key){
        stringRedisTemplate.delete(key);
    }
}
