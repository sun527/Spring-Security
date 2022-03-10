package com.shangma.cn.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.shangma.cn.entity.Admin;
import com.shangma.cn.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().lambda().eq(Admin::getAdminAccount, username));
        if (admin == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println(admin);
        return new LoginAdmin(admin);
    }
}
