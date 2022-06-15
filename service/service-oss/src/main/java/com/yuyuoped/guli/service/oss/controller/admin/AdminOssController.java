package com.yuyuoped.guli.service.oss.controller.admin;

import com.yuyuoped.guli.service.base.result.R;
import com.yuyuoped.guli.service.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yuyuoped
 * @since 2022/6/14
 */
@RestController
@RequestMapping("/admin/oss")
@Api(tags = "讲师头像上传模块")
@CrossOrigin
public class AdminOssController {

    @Autowired
    OssService ossService;

    @ApiOperation("上传讲师头像")
    @PostMapping("upload")
    public R upload(@ApiParam(name = "上传的头像文件") @RequestBody MultipartFile file) {
        String path = ossService.upload(file);
        return R.ok().data("path", path);
    }

    @ApiOperation("删除讲师头像")
    @DeleteMapping("delete")
    public R delete(@ApiParam(name = "需要删除的头像文件地址") @RequestBody String path) {
        ossService.delete(path);
        return R.ok();
    }

}
