package com.yuyuoped.guli.service.edu.feign.fallback;

import com.yuyuoped.guli.service.base.result.R;
import com.yuyuoped.guli.service.edu.feign.OssClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yuyuoped
 * @since 2022/6/16
 */
@Slf4j
@Component
public class OssFallback implements OssClient {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public R delete(String path) {

        BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps("del-fail-file");
        hashOps.put(path, "avatar");

        return R.error();
    }
}
