package com.yuyuoped.guli.service.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuyuoped
 * @since 2022/6/14
 */
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
@Data
public class OssConfig {
    String schema;
    String endpoint;
    String accessKeyId;
    String accessKeySecret;
    String bucketName;
}
