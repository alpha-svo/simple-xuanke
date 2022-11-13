package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Course;
import com.example.mapper.CourseMapper;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public Page<Course> pageCourseWithSearch(Course course, long page, long limit) {
        LambdaQueryWrapper<Course> lqw = new LambdaQueryWrapper<>();
        lqw.like(course.getCourse() != null, Course::getCourse, course.getCourse())
                .eq(course.getFromdep() != null, Course::getFromdep, course.getFromdep());
        Page<Course> coursePage = courseMapper.selectPage(new Page<>(page, limit), lqw);
        return coursePage;
    }
}
