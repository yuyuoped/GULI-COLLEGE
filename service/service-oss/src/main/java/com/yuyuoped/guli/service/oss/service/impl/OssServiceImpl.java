package com.yuyuoped.guli.service.oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.yuyuoped.guli.service.base.result.R;
import com.yuyuoped.guli.service.oss.config.OssConfig;
import com.yuyuoped.guli.service.oss.service.OssService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author yuyuoped
 * @since 2022/6/14
 */
@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OssConfig ossConfig;

    @Override
    public String upload(MultipartFile file) {
        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());

        String objectName = "avatar/" + new DateTime().toString("yyyy/MM/dd/") + "/" + UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename();

        try {
            InputStream inputStream = file.getInputStream();
            // 创建PutObject请求。
            ossClient.putObject(ossConfig.getBucketName(), objectName, inputStream);
        } catch (IOException e) {
            e.printStackTrace();//将编译时的异常转为运行时的异常抛出
            throw new RuntimeException("图片上传失败");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return ossConfig.getSchema()+ossConfig.getBucketName()+"."+ossConfig.getEndpoint()+"/"+objectName;
    }

    @Override
    public void delete(String path) {
        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());

        try {
            // 删除文件或目录。如果要删除目录，目录必须为空。
            ossClient.deleteObject(ossConfig.getBucketName(), path.substring(path.lastIndexOf("avatar")));
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
