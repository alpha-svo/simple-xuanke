package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Course;

public interface CourseService extends IService<Course> {
    Page<Course> pageCourseWithSearch(Course course, long page, long limit);
}
