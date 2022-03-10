package com.shangma.cn.common.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 11:29
 * 文件说明：
 */
@Getter
@AllArgsConstructor
public enum AxiosStatus {


    OK(20000, "操作成功"),
    ERROE(55555, "操作失败"),
    FORM_VALID_ERROR(33333, "表单校验失败"),
    ADMIN_EXIST(33334, "员工已存在"),
    NOT_IMG(33335, "不是图片"),
    IMG_TYPE_ERROR(33336, "图片格式不正确"),
    IMG_TOO_LARGE(33337, "图片太大"),
    IMG_SIZE_ERROR(33338, "图片尺寸不正确"),
    UPLOAD_ERROR(33339, "图片上传失败"),
    UNKOWN_ERROR(50000, "服务器错误"),
    NO_LOGIN(44444, "未登录"),
    NO_PERM(44555, "aaaaaaaaaaaaaaaaaa"),
    CODE_ERROR(23445, "验证码错误"),

    ;
    private int status;

    private String message;


    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
