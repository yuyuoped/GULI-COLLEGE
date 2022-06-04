package com.yuyuoped.guli.service.base.handler;

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
//        e.printStackTrace();
        log.error(ExceptionUtils.getStackTrace(e));
        return R.error();
    }

}
