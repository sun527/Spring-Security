package com.shangma.cn.security;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.http.AxiosStatus;
import com.shangma.cn.utils.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        AxiosResult error = AxiosResult.error(AxiosStatus.NO_LOGIN);
        String str = JsonUtils.obj2Str(error);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(str);
    }
}
