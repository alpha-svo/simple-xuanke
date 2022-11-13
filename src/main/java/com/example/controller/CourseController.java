package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Course;
import com.example.service.CourseService;
import com.example.service.DepartmentService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public R listCourse(@RequestParam long page, @RequestParam long limit, Course course) {
        Page<Course> coursePage = courseService.pageCourseWithSearch(course, page, limit);
        return R.ok("ok", coursePage.getTotal(), coursePage.getRecords());
    }

    @GetMapping("/department")
    public R listDep() {
        return R.ok("ok", departmentService.list());
    }

    @GetMapping("/{course}")
    public R findCourseByName(@PathVariable String course) {
        LambdaQueryWrapper<Course> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Course::getCourse, course);
        Course one = courseService.getOne(lqw);
        if (one != null) {
            return R.ok("ok",one);
        } else {
            return R.error("course not found!");
        }
    }

    @PostMapping("/auth")
    public void insertUser(@RequestBody Course course) {
        courseService.save(course);
    }

    @PutMapping("/auth")
    public void updateUser(@RequestBody Course course) {
        courseService.updateById(course);
    }

    @DeleteMapping("/auth/{id}")
    public void deleteUser(@PathVariable int id) {
        courseService.removeById(id);
    }
}
