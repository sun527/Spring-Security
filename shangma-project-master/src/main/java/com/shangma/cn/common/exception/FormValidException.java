package com.shangma.cn.common.exception;

import com.shangma.cn.common.http.AxiosStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/29 17:11
 * 文件说明：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormValidException extends RuntimeException {

    private AxiosStatus axiosStatus;

    private Map<String, String> map;
}
