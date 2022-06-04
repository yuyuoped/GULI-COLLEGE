package com.yuyuoped.guli.service.edu.controller.admin;


import com.yuyuoped.guli.service.base.result.R;
import com.yuyuoped.guli.service.base.result.ResultCodeEnum;
import com.yuyuoped.guli.service.edu.entity.Teacher;
import com.yuyuoped.guli.service.edu.service.TeacherService;
import com.yuyuoped.guli.service.edu.service.impl.TeacherServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class AdminTeacherController {

    @Autowired
    TeacherService teacherService;

    @ApiOperation("获取所有讲师信息")
    @GetMapping("listAll")
    public R listAll() {
        return R.ok().data("lists", teacherService.list());
    }

    @ApiOperation("分页获取讲师信息")
    @GetMapping("queryPage/{pageNum}/{pageSize}")
    public R queryPage(@ApiParam(value = "页码", required = true) @PathVariable Integer pageNum,
                       @ApiParam(value = "每页数据数目", required = true) @PathVariable Integer pageSize) {
        return R.ok().data("page", teacherService.queryPage(pageNum, pageSize));
    }

    @ApiOperation("通过id获取讲师信息")
    @GetMapping("queryById/{id}")
    public R queryById(@ApiParam(value = "讲师id", required = true) @PathVariable String id) {
        return R.ok().data("teacher", teacherService.getById(id));
    }

    @ApiOperation("通过id删除讲师信息")
    @DeleteMapping ("deleteById/{id}")
    public R deleteById(@ApiParam(value = "讲师id", required = true) @PathVariable String id) {
        teacherService.removeById(id);
        return R.ok();
    }

    @ApiOperation("新增讲师信息")
    @PostMapping("save")
    public R save(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return R.ok();
    }

    @ApiOperation("更新获取讲师信息")
    @PutMapping("update")
    public R update(@RequestBody Teacher teacher) {
        teacherService.updateById(teacher);
        return R.ok();
    }
}

