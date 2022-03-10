package com.shangma.cn.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.shangma.cn.common.cache.CacheUtils;
import com.shangma.cn.utils.JsonUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Component
public class TokenService {

    private static final String SECRET = "1DFC4AF289BEFF68BEEB367B24BAB126";

    private static final String PREFIX = "login_admin:";

    /**
     * 生成token
     */
    public String createToken(String uuid){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        String token = JWT.create()
                .withIssuer("sunjing")
                .withClaim("uuid",uuid)
                .sign(algorithm);
        return token;
    }

    /**
     * 解析token
     */
    public DecodedJWT verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("sunjing")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 创建token并缓存登录用户
     */
    public String createTokenAndCacheLoginAdmin(LoginAdmin loginAdmin){
        String uuid = UUID.randomUUID().toString();
        loginAdmin.setUuid(uuid);
        setLoginAdminCache(loginAdmin);
        return createToken(uuid);
    }

    /**
     * 缓存用户
     */
    public void setLoginAdminCache(LoginAdmin loginAdmin){
        CacheUtils.setCache(PREFIX + loginAdmin.getUuid(), JsonUtils.obj2Str(loginAdmin));
    }

    /**
     * 获取请求头中的token
     */
    public String getToken(HttpServletRequest request){
        if (request.getHeader("Authorization").contains(" ")){
            String[] split = request.getHeader("Authorization").split(" ");
            if (split.length == 2){
                return split[1];
            }
        }
        return "";
    }

    /**
     * 获取uuid
     */
    public String getUuid(HttpServletRequest request){
        String token = getToken(request);
        DecodedJWT decodedJWT = verifyToken(token);
        if (decodedJWT != null){
            return decodedJWT.getClaim("uuid").asString();
        }
        return "";
    }

    /**
     * 获取缓存用户信息
     */
    public LoginAdmin getCacheLoginAdmin(HttpServletRequest request){
        String uuid = getUuid(request);
        String cache = CacheUtils.getCache(PREFIX + uuid);
        if (!StringUtils.isEmpty(cache)){
            return JsonUtils.str2Obj(cache,LoginAdmin.class);
        }
        return null;
    }
}
