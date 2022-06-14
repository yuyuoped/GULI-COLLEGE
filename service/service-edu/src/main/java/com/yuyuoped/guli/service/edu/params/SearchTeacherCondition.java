package com.yuyuoped.guli.service.edu.params;

import lombok.Data;

/**
 * @author yuyuoped
 * @since 2022/6/13
 */
@Data
public class SearchTeacherCondition {
    private String name;//讲师姓名
    private Integer level;//头衔
    private String joinDateBegin;//入职起始时间
    private String joinDateEnd;//截止时间
}
