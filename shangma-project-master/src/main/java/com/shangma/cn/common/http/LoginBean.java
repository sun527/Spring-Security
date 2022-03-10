package com.shangma.cn.common.http;

import lombok.Data;

@Data
public class LoginBean {

    private String username;
    private String password;
    private String code;
    private String uuid;

}
