package com.yuyuoped.guli.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuyuoped.guli.service.edu.params.SearchTeacherCondition;
import com.yuyuoped.guli.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author yuyuoped
 * @since 2022-06-01
 */
public interface TeacherService extends IService<Teacher> {

    Page<Teacher> queryPage(Integer pageNum, Integer pageSize);

    Page<Teacher> queryPageWithCondition(Integer pageNum, Integer pageSize, SearchTeacherCondition searchTeacherCondition);

    void batchDeleteByIds(List<String> ids);
}
