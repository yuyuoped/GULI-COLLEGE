package com.yuyuoped.guli.service.base.handler;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.yuyuoped.guli.service.base.result.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yuyuoped
 * @since 2022/6/4
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return R.error();
    }

    @ExceptionHandler(OSSException.class)
    @ResponseBody
    public R ossError(OSSException oe) {
        log.error("Caught an OSSException, which means your request made it to OSS, "
                + "but was rejected with an error response for some reason.");
        log.error("Error Message:" + oe.getErrorMessage());
        log.error("Error Code:" + oe.getErrorCode());
        log.error("Request ID:" + oe.getRequestId());
        log.error("Host ID:" + oe.getHostId());
        return R.error();
    }

    @ExceptionHandler(ClientException.class)
    @ResponseBody
    public R ossClientError(ClientException ce) {
        log.error("Caught an ClientException, which means the client encountered "
                + "a serious internal problem while trying to communicate with OSS, "
                + "such as not being able to access the network.");
        log.error("Error Message:" + ce.getMessage());
        return R.error();
    }

}
