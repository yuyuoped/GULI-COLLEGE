package com.yuyuoped.guli.service.oss.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yuyuoped
 * @since 2022/6/14
 */
@Configuration
@PropertySource(value = "classpath:application-local.properties")
@Data
public class OssConfig {
    @Value("${aliyun.oss.schema}")
    String schema;
    @Value("${aliyun.oss.endpoint}")
    String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    String bucketName;
}
