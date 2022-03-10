package com.shangma.cn.common.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/25 15:15
 * 文件说明：
 */
@Component
@Data
@ConfigurationProperties(prefix = "aliyun")
public class UploadProperties {

    private String endPoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucket;
    private String baseUrl;
    private List<String> uploadExt;
    private int uploadSize;
    private int imgWidth;
    private int imgHeight;
}
