package com.shangma.cn.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.shangma.cn.common.exception.ApiException;
import com.shangma.cn.common.http.AxiosStatus;
import com.shangma.cn.common.props.UploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/15 11:55
 * 文件说明：
 */
@Component
public class UploadService {

    @Autowired
    private UploadProperties uploadProperties;

    public String upload(Part part) throws IOException {

        try {
            //文件是不是属于图片
            BufferedImage read = ImageIO.read(part.getInputStream());
            if (read == null) throw new ApiException(AxiosStatus.NOT_IMG);
            //如果是图片 要判断是不是jpg 或者 png
            if (!uploadProperties.getUploadExt().contains(StringUtils.getFilenameExtension(part.getSubmittedFileName())))
                throw new ApiException(AxiosStatus.IMG_TYPE_ERROR);
            //大小
            long size = part.getSize() / 1024;
            if (size > uploadProperties.getUploadSize())
                throw new ApiException(AxiosStatus.IMG_TOO_LARGE);
            //宽高
            int width = read.getWidth();
            int height = read.getHeight();
            if (width != uploadProperties.getImgWidth() || height != uploadProperties.getImgHeight()) {
                throw new ApiException(AxiosStatus.IMG_SIZE_ERROR);
            }
            String fileName = UUID.randomUUID().toString() + "." + StringUtils.getFilenameExtension(part.getSubmittedFileName());
            OSS ossClient = new OSSClientBuilder().build(uploadProperties.getEndPoint(), uploadProperties.getAccessKeyId(), uploadProperties.getAccessKeySecret());
            ossClient.putObject(uploadProperties.getBucket(), fileName, part.getInputStream());
            ossClient.shutdown();
            String url = uploadProperties.getBaseUrl() + fileName;
            return url;
        } catch (IOException e) {
            throw new ApiException(AxiosStatus.UPLOAD_ERROR);
        }

    }


}
