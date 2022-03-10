package com.shangma.cn.common.exception;

import com.shangma.cn.common.http.AxiosStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/25 14:46
 * 文件说明：
 */
@Data
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private AxiosStatus axiosStatus;
}
