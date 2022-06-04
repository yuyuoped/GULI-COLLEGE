package com.yuyuoped.guli.service.edu.controller.admin;


import com.yuyuoped.guli.service.base.result.R;
import com.yuyuoped.guli.service.base.result.ResultCodeEnum;
import com.yuyuoped.guli.service.edu.entity.Teacher;
import com.yuyuoped.guli.service.edu.service.TeacherService;
import com.yuyuoped.guli.service.edu.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author yuyuoped
 * @since 2022-06-01
 */
@RestController
@RequestMapping("/admin/edu/teacher")
public class AdminTeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("listAll")
    public R listAll() {
        return R.ok().data("lists", teacherService.list());
    }

    @GetMapping("queryPage/{pageNum}/{pageSize}")
    public R queryPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        return R.ok().data("page", teacherService.queryPage(pageNum, pageSize));
    }

    @GetMapping("queryById/{id}")
    public R queryById(@PathVariable String id) {
        return R.ok().data("teacher", teacherService.getById(id));
    }

    @DeleteMapping ("deleteById/{id}")
    public R deleteById(@PathVariable String id) {
        teacherService.removeById(id);
        return R.ok();
    }

    @PostMapping("save")
    public R save(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return R.ok();
    }

    @PutMapping("update")
    public R update(@RequestBody Teacher teacher) {
        teacherService.updateById(teacher);
        return R.ok();
    }
}

