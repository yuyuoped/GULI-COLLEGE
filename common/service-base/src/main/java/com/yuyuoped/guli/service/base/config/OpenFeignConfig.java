package com.yuyuoped.guli.service.base.config;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuyuoped
 * @since 2022/6/16
 */
@Configuration
@EnableFeignClients(basePackages = "com.yuyuoped.guli.service")
public class OpenFeignConfig {

    @Bean
    public Logger.Level getLoggerLevel() {
        return Logger.Level.FULL;
    }


}
