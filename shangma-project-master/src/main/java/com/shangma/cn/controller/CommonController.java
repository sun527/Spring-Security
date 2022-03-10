package com.shangma.cn.controller;

import com.shangma.cn.common.cache.ImgCodeCache;
import com.shangma.cn.common.exception.ApiException;
import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.http.AxiosStatus;
import com.shangma.cn.common.http.LoginBean;
import com.shangma.cn.entity.Admin;
import com.shangma.cn.entity.Menu;
import com.shangma.cn.security.LoginAdmin;
import com.shangma.cn.security.TokenService;
import com.shangma.cn.service.AdminService;
import com.shangma.cn.service.BrandService;
import com.shangma.cn.service.MenuService;
import com.shangma.cn.utils.JsonUtils;
import com.shangma.cn.utils.UploadService;
import com.wf.captcha.GifCaptcha;
import freemarker.cache.StringTemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/23 9:46
 * 文件说明：
 */
@RestController
@RequestMapping("common")
public class CommonController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AdminService adminService;

    /**
     * 文件上传
     */
    @PostMapping("upload")
    public AxiosResult<String> upload(@RequestPart Part avatar) throws IOException {
        return AxiosResult.success(uploadService.upload(avatar));
    }

    /**
    * 生成验证码
    */
    @GetMapping("getImgCode")
    public AxiosResult<String> getImgCode(String uuid){
        // gif类型
        GifCaptcha captcha = new GifCaptcha(130, 48,6);
        String text = captcha.text();
        ImgCodeCache.setImgCodeCache(uuid,text);
        return AxiosResult.success(captcha.toBase64());
    }

    /**
     * 登录功能
     */
    @PostMapping("doLogin")
    public AxiosResult<String> doLogin(@RequestBody LoginBean loginBean){
        if (!ImgCodeCache.getImgCodeCache(loginBean.getUuid()).equalsIgnoreCase(loginBean.getCode())){
            throw new ApiException(AxiosStatus.CODE_ERROR);
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginBean.getUsername(), loginBean.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authRequest);
        ImgCodeCache.deleteImgCode(loginBean.getUuid());
        LoginAdmin loginAdmin = (LoginAdmin) authenticate.getPrincipal();
        return AxiosResult.success(JsonUtils.obj2Str(tokenService.createTokenAndCacheLoginAdmin(loginAdmin)));
    }

    @GetMapping("getAdminInfo")
    public AxiosResult<LoginAdmin> getAdminInfo(HttpServletRequest request){
        LoginAdmin loginAdmin = tokenService.getCacheLoginAdmin(request);
        Admin admin = loginAdmin.getAdmin();
        List<Menu> menuList = null;
        //如果是超级管理员
        if (admin.getIsAdmin()){
            menuList = menuService.list();
        }else {
            //不是超级管理员
            menuList = adminService.getMenusByAdminId(admin.getId());
        }
        //过滤掉没有目录的内容
        List<Menu> list = menuList.stream().filter(menu -> menu.getMenuType().intValue() != 1).collect(Collectors.toList());
        loginAdmin.setMenus(list);
        tokenService.setLoginAdminCache(loginAdmin);
        return AxiosResult.success(loginAdmin);
    }
}
