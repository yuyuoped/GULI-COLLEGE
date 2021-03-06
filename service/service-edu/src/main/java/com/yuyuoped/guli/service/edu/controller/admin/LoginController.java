package com.yuyuoped.guli.service.edu.controller.admin;

import com.yuyuoped.guli.service.base.result.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuyuoped
 * @since 2022/6/6
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {

    @PostMapping("login")
    public R login() {
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info() {
        return R.ok().data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
    }

    @PostMapping("logout")
    public R logout() {
        return R.ok();
    }
}
