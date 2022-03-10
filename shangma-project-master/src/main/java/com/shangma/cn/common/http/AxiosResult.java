package com.shangma.cn.common.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 11:28
 * 文件说明： 统一返回值
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AxiosResult<T> {
    private int status;
    private String message;
    private T data;

    private AxiosResult(AxiosStatus axiosStatus, T t) {
        this.status = axiosStatus.getStatus();
        this.message = axiosStatus.getMessage();
        this.data = t;
    }

    /**
     * 成功的方法
     */

    public static <T> AxiosResult<T> success() {
        return getInstance(AxiosStatus.OK, null);
    }


    /**
     * 成功的方法
     * 携带数据
     */
    public static <T> AxiosResult<T> success(T t) {
        return getInstance(AxiosStatus.OK, t);
    }


    /**
     * 失败的方法
     */
    public static <T> AxiosResult<T> error() {
        return getInstance(AxiosStatus.ERROE, null);
    }

    /*
     自定义失败状态码
     */
    public static <T> AxiosResult<T> error(AxiosStatus axiosStatus) {
        return getInstance(axiosStatus, null);
    }

    /**
     * 自定义状态码和数据
     *
     * @param axiosStatus
     * @param t
     * @param <T>
     * @return
     */
    public static <T> AxiosResult<T> error(AxiosStatus axiosStatus, T t) {
        return getInstance(axiosStatus, t);
    }

    /**
     * 失败的方法
     */
    public static <T> AxiosResult<T> error(T t) {
        return getInstance(AxiosStatus.ERROE, t);
    }


    private static <T> AxiosResult<T> getInstance(AxiosStatus axiosStatus, T t) {
        return new AxiosResult<T>(axiosStatus, t);
    }


}
