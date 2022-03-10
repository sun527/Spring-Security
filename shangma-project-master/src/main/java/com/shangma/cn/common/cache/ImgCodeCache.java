package com.shangma.cn.common.cache;

import sun.misc.Cache;

public class ImgCodeCache {

    //验证码过期时间，单位分钟
    private static final long EXPIRE_TIME = 2;

    //登录验证码的前缀
    private static final String KEY_PREFIX = "login_code:";

    /**
    * 存入验证码
    */
    public static void setImgCodeCache(String key,String value){
        CacheUtils.setCacheWithExpireTime(KEY_PREFIX + key,value,EXPIRE_TIME);
    }

    /**
     * 取出验证码
     */
    public static String getImgCodeCache(String key){
        return CacheUtils.getCache(KEY_PREFIX + key);
    }

    /**
     * 删除验证码
     */
    public static void deleteImgCode(String key){
        CacheUtils.removeCache(KEY_PREFIX + key);
    }
}
