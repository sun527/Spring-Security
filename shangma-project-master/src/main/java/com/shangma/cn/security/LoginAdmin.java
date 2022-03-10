package com.shangma.cn.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shangma.cn.entity.Admin;
import com.shangma.cn.entity.Menu;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class LoginAdmin implements UserDetails {

    private Admin admin;

    private String uuid;

    private List<Menu> menus;

    public LoginAdmin() {
    }

    public LoginAdmin(Admin admin) {
        this.admin = admin;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menus)){
            menus.forEach(menu -> list.add(new SimpleGrantedAuthority(menu.getPermSign())));
        }
        return list;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.admin.getAdminPassword();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.admin.getAdminName();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.admin.getIsActive();
    }
}
