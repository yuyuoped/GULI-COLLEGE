package com.yuyuoped.guli.service.edu.feign;

import com.yuyuoped.guli.service.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yuyuoped
 * @since 2022/6/16
 */
@FeignClient("service-oss")
public interface OssClient {

    @DeleteMapping("/admin/oss/delete")
    R delete(@RequestBody String path);

}
