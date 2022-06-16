package com.yuyuoped.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuyuoped.guli.service.edu.feign.OssClient;
import com.yuyuoped.guli.service.edu.params.SearchTeacherCondition;
import com.yuyuoped.guli.service.edu.entity.Teacher;
import com.yuyuoped.guli.service.edu.mapper.TeacherMapper;
import com.yuyuoped.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private OssClient ossClient;

    public Page<Teacher> queryPage(Integer pageNum, Integer pageSize) {
        return super.page(new Page<>(pageNum, pageSize));
    }

    @Override
    public Page<Teacher> queryPageWithCondition(Integer pageNum, Integer pageSize, SearchTeacherCondition searchTeacherCondition) {
        if (searchTeacherCondition != null) {
            String name = searchTeacherCondition.getName();
            Integer level = searchTeacherCondition.getLevel();
            String joinDateBegin = searchTeacherCondition.getJoinDateBegin();
            String joinDateEnd = searchTeacherCondition.getJoinDateEnd();
            LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();

            if (!StringUtils.isEmpty(name)) {
                queryWrapper.like(Teacher::getName, name);
            }
            if (level != null) {
                queryWrapper.eq(Teacher::getLevel, level);
            }
            if (!StringUtils.isEmpty(joinDateBegin)) {
                queryWrapper.ge(Teacher::getJoinDate, joinDateBegin);
            }
            if (!StringUtils.isEmpty(joinDateEnd)) {
                queryWrapper.le(Teacher::getJoinDate, joinDateEnd);
            }

            return super.page(new Page<>(pageNum, pageSize), queryWrapper);
        } else {
            return this.queryPage(pageNum, pageSize);
        }
    }

    @Override
    public boolean removeById(Serializable id) {
        Teacher teacher = super.getById(id);
        boolean remove = super.removeById(id);

        if (remove && teacher != null && teacher.getAvatar() != null) {
            ossClient.delete(teacher.getAvatar());
        }
        return true;
    }

    @Override
    public void batchDeleteByIds(List<String> ids) {
        List<Teacher> teachers = new ArrayList<>();
        for (String id : ids) {
            teachers.add(super.getById(id));
        }
        boolean remove = super.removeByIds(ids);
        if (remove && teachers.size() > 0) {
            teachers.forEach(teacher -> {
                if (teacher.getAvatar() != null) {
                    ossClient.delete(teacher.getAvatar());
                }
            });
        }
    }
}
