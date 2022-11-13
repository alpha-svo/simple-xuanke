package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Course;
import com.example.entity.Student;
import com.example.service.CourseService;
import com.example.service.StudentService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    public R findStudentById(@PathVariable int id) {
        return R.ok("ok", studentService.getById(id));
    }

    @GetMapping("/name/{stuname}")
    public R findStudentByName(@PathVariable String stuname) {
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.like(Student::getStuname, stuname);
        List<Student> stus = studentService.list(lqw);
        return stus.size() > 0 ? R.ok("ok", stus) : R.error("student not found!");
    }

    @GetMapping("/number/{stunumber}")
    public R findStudentByNumber(@PathVariable String stunumber) {
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.like(Student::getNumber, stunumber);
        Student stu = studentService.getOne(lqw);
        return stu != null ? R.ok("ok", stu) : R.error("student not found!");
    }

    @GetMapping("/{dep}/{klass}")
    public R listStudentsByClass(@PathVariable int dep, @PathVariable int klass) {
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Student::getStudep, dep).eq(Student::getStuclass, klass);
        return R.ok("ok", studentService.list(lqw));
    }

    @GetMapping("/my-courses/{id}")
    public R getMySelectedCourses(@PathVariable int id) {
        List<Course> courses = studentService.selectCoursesByStuId(id);
        return R.ok("ok", courses);
    }

    @GetMapping("/my-table/{id}")
    public R getMyTable(@PathVariable int id) {
        List<Course> courses = studentService.selectCoursesByStuId(id);
        //创建课程表二维数组，静态的仅作展示，用字符串不用课程对象
        //为了显示第几节课，所以将数组反向赋值
        String[][] table = new String[4][8];
        //首先添加节次
        for (int i = 0; i < 4; i++) {
            table[i][0] = "第" + (i + 1) + "单元";
        }
        //定义sb对象优化字符串拼接
        StringBuilder sb = new StringBuilder();
        //遍历已选课程
        for (Course c : courses) {
            //查看每个课程的duration
            String duration = c.getDuration();
            //分割数组
            String[] split = duration.split(",");
            //赋值给二维数组
            table[Integer.parseInt(split[1]) - 1][Integer.parseInt(split[0])] =
                    sb.append(c.getCourse()).append(" ").append(c.getTeacher()).toString();
            //清空sb为下一次使用
            sb.delete(0, sb.length());
        }
        return R.ok("ok", table);
    }

    @GetMapping("/my-courses/choose")
    public R chooseMyCourses(int id, long page, long limit, Course course) {
        //获得我可以选的课，过滤掉冲突课程，和已选课程，逐页过滤结果
        //首先获取全部课程，分页的
        Page<Course> coursePage = courseService.pageCourseWithSearch(course, page, limit);
        List<Course> allCourses = coursePage.getRecords();
        //获取我已选课
        List<Course> selectedCourses = studentService.selectCoursesByStuId(id);
        //过滤冲突课程(已选的肯定就冲突) 哈哈
        for (Course sc : selectedCourses) {
            allCourses = allCourses.stream()
                    .filter(c -> !c.getDuration().equals(sc.getDuration())).collect(Collectors.toList());
        }
        return R.ok("ok", coursePage.getTotal(), allCourses);
    }

    @PutMapping
    public R updateStudentInfo(@RequestBody Student stu) {
        studentService.updateById(stu);
        return R.ok("修改成功");
    }
}
