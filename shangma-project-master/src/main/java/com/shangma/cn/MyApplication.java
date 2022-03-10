package com.shangma.cn;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/22 11:01
 * 文件说明：
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.shangma.cn.mapper"})
@EnableScheduling
@EnableAsync
public class MyApplication {


    /**security授权相关
     *      授权分为2种
     *          第一种：使用过滤器  Filter
     *                流程：1： 用户登录 获取用户的角色  把这个角色保存到Authentication信息中 角色字符串是ROLE_xxxx
     *                              用户->角色
     *
     *                     2： 手动查询权限（请求）所需要的角色  把请求和请求所需要的的角色放到FilterSecurityMetaDataSource中
     *                          表达式hasRole("xxxxx")
     *                             - 权限-角色
     *
     *                     3：当用户请求某个请求时  会经过过滤器  在过滤器中 执行决策器 实际上执行的是决策中的投票器 投票器判断是否有权限
     *                         判断方式 默认是表达式和字符串之间的判断
     *
     *
     *                      注意点： 权限实际上是一个路径
     *

     *
     *
     *          第二种：使用AOP的方法拦截器
     *                   流程：1 用户登录 获取用户的角色  然后获取到用户的权限  保存Authentication中
     *                          用户->角色->权限
     *
     *                        2： 当用户访问某个请求时 使用AOP方法拦截器 可以获取到调用的是哪个类上的哪个方法
     *                            进而获取到方法上的注解@PreAuthority() 最终获得注解中的值
     *
     *                        3：调用决策期的方法进行判断是否有权限 实际上调用的是 投票器中的方法
     *                           默认情况下 投票器中的方法 比较的也是 xxxx 和 hasAuthority("xxxx")
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * @param args
     */

    public static void main(String[] args){
        SpringApplication.run(MyApplication.class,args);
        //System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

}
