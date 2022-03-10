package com.shangma.cn.controller.base;

import com.shangma.cn.common.http.AxiosResult;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 11:44
 * 文件说明：
 */
public class BaseController {


    protected AxiosResult<Void> toAxios(int row){
        return  row>0? AxiosResult.success():AxiosResult.error();
    }
}
