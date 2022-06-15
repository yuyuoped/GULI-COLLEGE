package com.yuyuoped.guli.service.oss.service;

import com.yuyuoped.guli.service.base.result.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yuyuoped
 * @since 2022/6/14
 */
public interface OssService {
    String upload(MultipartFile file);
    void delete(String path);
}
