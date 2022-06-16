package com.yuyuoped.guli.service.oss.springtask;

import com.yuyuoped.guli.service.oss.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

/**
 * @author yuyuoped
 * @since 2022/6/16
 */
@Component
@Slf4j
public class OssTask {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    OssService ossService;

    @Scheduled(cron = "0 0 */2 * * ?")
    public void delFilesTask() {

        BoundHashOperations<String, String, String> hashOps =
                redisTemplate.boundHashOps("del-fail-file");

        if (hashOps.size() == 0) {
            log.info("{} , 定时任务执行了，没有要删除的文件", new Date());
            return;
        }
        //3、如果文件列表不为空：获取删除所有的文件
        Set<String> paths = hashOps.keys();
        paths.forEach(path -> {
            try {
                ossService.delete(path);
            } catch (Exception e) {

                log.error("delFilesTask删除文件失败，module:{} , path:{} ,异常信息:{}", hashOps.get(path), path,
                        ExceptionUtils.getStackTrace(e));//将异常对象的堆栈信息转为字符串写入到日志文件中
            }
        });

        hashOps.delete(paths.toArray());
    }
}
