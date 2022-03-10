package com.shangma.cn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/23 10:32
 * 文件说明：
 */
@Configuration
@EnableSwagger2 //开启swagger2文档配置
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.shangma.cn.controller"))
                .paths(PathSelectors.any()).build();
    }

    /**
     * @Description: 构建 api文档的信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置页面标题
                .title("尚马项目后台管理API接口")
                // 设置联系人
                .contact(new Contact("事了拂衣去", "http://huige.icu", "zhengzhoudaxuevip@163.com"))
                // 描述
                .description("打工人 打工魂 打工都是人上人")
                // 定义版本号
                .version("1.0").build();
    }


}
