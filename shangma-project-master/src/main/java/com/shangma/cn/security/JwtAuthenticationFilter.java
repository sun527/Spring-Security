package com.shangma.cn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        LoginAdmin loginAdmin = tokenService.getCacheLoginAdmin(httpServletRequest);
        if (loginAdmin != null){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginAdmin.getAdmin().getAdminAccount(),"******",loginAdmin.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
