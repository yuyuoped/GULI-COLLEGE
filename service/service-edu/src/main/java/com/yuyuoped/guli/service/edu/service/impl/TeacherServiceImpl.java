package com.yuyuoped.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuyuoped.guli.service.edu.params.SearchTeacherCondition;
import com.yuyuoped.guli.service.edu.entity.Teacher;
import com.yuyuoped.guli.service.edu.mapper.TeacherMapper;
import com.yuyuoped.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public void batchDeleteByIds(List<String> ids) {
        super.removeByIds(ids);
    }
}
