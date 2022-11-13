package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Course;
import com.example.entity.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface StudentService extends IService<Student> {
    List<Course> selectCoursesByStuId(int id);
}
