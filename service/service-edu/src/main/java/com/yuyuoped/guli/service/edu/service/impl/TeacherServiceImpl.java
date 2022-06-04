package com.yuyuoped.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuyuoped.guli.service.edu.entity.Teacher;
import com.yuyuoped.guli.service.edu.mapper.TeacherMapper;
import com.yuyuoped.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author yuyuoped
 * @since 2022-06-01
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    public Page<Teacher> queryPage(Integer pageNum, Integer pageSize) {
        return super.page(new Page<>(pageNum, pageSize));
    }
}
